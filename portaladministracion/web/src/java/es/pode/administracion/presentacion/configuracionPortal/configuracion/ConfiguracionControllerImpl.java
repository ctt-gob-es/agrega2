// license-header java merge-point
package es.pode.administracion.presentacion.configuracionPortal.configuracion;


import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.internet.InternetHeaders;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimePartDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;

import es.pode.agregador.negocio.agregador.servicio.FeedVO;
import es.pode.configuracionPlataforma.negocio.servicios.SrvPropiedadService;
import es.pode.contenidos.negocio.configuracionPortal.servicio.ConfiguracionPortalVO;
import es.pode.contenidos.negocio.configuracionPortal.servicio.ImagenVO;
import es.pode.soporte.constantes.ConstantesAgrega;



/**
 * @see es.pode.administracion.presentacion.configuracionPortal.configuracion.ConfiguracionController
 */
public class ConfiguracionControllerImpl extends ConfiguracionController
{
//    private SrvPropiedadService prop = null;
	private static final Integer SELEC_SUBIR_IMAGEN=Integer.valueOf(5); 
	private static final Integer SELEC_IMAGEN_ACTUAL=Integer.valueOf(4);
	private static final String NUM_ODES_GALERIA_DEFAULT = "10";
	private static final String ID_RSS_GALERIA_DEFAULT = "0.0";
	
	private java.util.Properties pSpringProperties = null;
	private static Logger logger = Logger.getLogger(ConfiguracionControllerImpl.class);
	private SrvPropiedadService prop = null;



	/**
	 * Método que obtiene la configuración del portal.Si no encuentra ninguna configuración activa crea una por defecto
	 * @param  
	 * @return 
	 * @throws Exception
	 */
    public final void obtenerConfiguracion(ActionMapping mapping, es.pode.administracion.presentacion.configuracionPortal.configuracion.ObtenerConfiguracionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
    	try{
    		if (logger.isDebugEnabled()) logger.debug("Obtenemos los datos de la última configuración");
    		ConfiguracionPortalVO config = this.getSrvConfigPortal().obtenerConfiguracion();
    		
    		String idiomaNavegacion = ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
    		FeedVO[] feed = this.getSrvAgregadorRssService().devuelveFeedsGaleriaPortada(idiomaNavegacion);
    		Collection<FeedVO> aFeed = Arrays.asList(feed);
    		form.setIdRssGaleriaBackingList(aFeed, "id","titulo");
 
    		String[] aValoresNumOdesGaleria = getPropertyValue("comboNumOdesGaleria").split("-");
    		form.setNumOdesGaleriaLabelList(aValoresNumOdesGaleria);
    		form.setNumOdesGaleriaValueList(aValoresNumOdesGaleria);
    		
    		String imagenActual = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_IMAGEN_DEFECTO);
    		//Esto de añadir un parametro con un numero aleatorio a la url es para que los navegadores no cacheen la imagen
    		imagenActual=imagenActual.substring(imagenActual.indexOf("/"))+"?"+new GregorianCalendar().getTimeInMillis();
    		logger.debug("Imagen por defecto actual es "+imagenActual);
    		form.setImagenPorDefecto(imagenActual);
    		
    		if(config==null){
    			Integer ini=Integer.valueOf(1);
    			if (logger.isDebugEnabled()) logger.debug("Si el servicio ha fallado y nos devuelve un nulo lo rellenamos por defecto");
    			form.setAgregaWeb(ini);
    			form.setDescargas(ini);
    			form.setEstadisticas(ini);
    			form.setEtiquetas(ini);
    			form.setImagen("Sin Imagen");
    			form.setEnlacePassword(ini);
    			form.setInformes(ini);
    			form.setNoticias(ini);
    			form.setOpenID(ini);
    			form.setPlugginBusqueda(ini);
    			form.setRegistrese(ini);
    			form.setRss(ini);
    			form.setTagging(ini);
    			form.setActivarImagen(SELEC_IMAGEN_ACTUAL);
    			form.setGoogleAnalytic(Integer.valueOf(0));
    			form.setCodGa("");
    			form.setIdRssGaleria(ID_RSS_GALERIA_DEFAULT);
    			form.setNumOdesGaleria(NUM_ODES_GALERIA_DEFAULT);
    			form.setItinerariosAprendizaje(ini);
 			
    			request.getSession().setAttribute("configNull",true);
    		}
    		else{
    			if (logger.isDebugEnabled()) logger.debug("Rellenamos nuestro VO");
    			form.setAgregaWeb(config.getAgregaWeb());
    			form.setDescargas(config.getDescargas());
    			form.setEstadisticas(config.getEstadisticas());
    			form.setEtiquetas(config.getEtiquetas());
    			form.setImagen(config.getImagen());
    			form.setEnlacePassword(config.getEnlacePassword());
    			form.setInformes(config.getInformes());
    			form.setNoticias(config.getNoticias());
    			form.setOpenID(config.getOpenId());
    			form.setPlugginBusqueda(config.getPlugginBusqueda());
    			form.setRegistrese(config.getRegistrese());
    			form.setRss(config.getRss());
    			form.setTagging(config.getTagging());
    			form.setActivarImagen(SELEC_IMAGEN_ACTUAL);
    			form.setGoogleAnalytic(config.getGoogleAnalytic());
    			form.setCodGa(config.getCodGa());
    			form.setIdRssGaleria(config.getIdRssGaleriaPortada());
    			form.setNumOdesGaleria(config.getNumOdesGaleria().toString());
    			form.setItinerariosAprendizaje(config.getItinerariosAprendizaje());
      
    			if (logger.isDebugEnabled()) logger.debug("Metemos estos datos en sesion");
    			request.getSession().setAttribute("imagen", config.getImagen());
    		}
    	}catch (Exception e){
    		logger.error(e);
    		throw new ValidatorException("{error.obteniendo.configuracion.ver}");	 
    	}
     }


    /**
	 * Guarda la configuracion creada y tratamos las imagenes
	 * @param  mapping,es.pode.administracion.presentacion.configuracionPortal.configuracion.GuardarConfiguracionForm,request,response
	 * @throws Exception
	 */
    public final void guardarConfiguracion(ActionMapping mapping, es.pode.administracion.presentacion.configuracionPortal.configuracion.GuardarConfiguracionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
    	Boolean configNull=(Boolean) request.getSession().getAttribute("configNull");
    	
    	//Imagen no cambia
    	if (logger.isDebugEnabled()) logger.debug("Imagen igual que la anterior");
    	if(form.getActivarImagen().intValue()==SELEC_IMAGEN_ACTUAL.intValue()){
    		if(configNull!=null){
    			throw new ValidatorException("{errors.config.imagenoblig}");
    		}
    		request.getSession().setAttribute("imagenAnterior",true);
    	}
    	if (logger.isDebugEnabled()) logger.debug("Capturamos Imagen");
    	
    	//Cogemos una imagen nueva
    	if(form.getActivarImagen().intValue()==SELEC_SUBIR_IMAGEN.intValue()){
    		if(form.getImagenFile()!=null){
    			if(form.getImagenFile().getFileName()== null || form.getImagenFile().getFileName().equals("")){
    				logger.error("Error en la imagen: Debe seleccionar un archivo de imagen");
    				throw new ValidatorException("{error.config.examinar.vacio}");
    			}
    			if(!form.getImagenFile().getContentType().equalsIgnoreCase("image/jpeg")&&!form.getImagenFile().getContentType().equalsIgnoreCase("image/pjpeg")){
    				logger.error("Error en la imagen: Sólo se admiten archivos .jpg");
    				throw new ValidatorException("{errors.config.noImagen}");
    			}
    		}
    	}
    	
      	if(form.getGoogleAnalytic()==1) {
    		if(form.getCodGa().equalsIgnoreCase("")) {
    			logger.error("El código google Analytic no puede estar vacio");
				throw new ValidatorException("{errors.config.noCodGa}");
    		}
    	}

      	if(form.getImagenPorDefectoFile()!=null && 
      	   form.getImagenPorDefectoFile().getFileName()!= null && 
      	   !form.getImagenPorDefectoFile().getFileName().equals("")){
/*			if(form.getImagenPorDefectoFile().getFileName()== null || form.getImagenPorDefectoFile().getFileName().equals("")){
				logger.error("Error en la imagen: Debe seleccionar un archivo de imagen");
				throw new ValidatorException("{error.config.examinar.vacio.img.defecto}");
			}
*/			if(!form.getImagenPorDefectoFile().getContentType().equalsIgnoreCase("image/jpeg")&&!form.getImagenPorDefectoFile().getContentType().equalsIgnoreCase("image/png")){
				logger.error("Error en la imagen: Sólo se admiten archivos .jpg o .png");
				throw new ValidatorException("{errors.config.noImagenPorDefecto}");
			}
		}
    	
    	try{
	    	boolean tratarImagen=false;
	    	
	    	if (logger.isDebugEnabled()) logger.debug("Inicializamos el VO y lo rellenamos");
	    	ConfiguracionPortalVO newConfiguracion=new ConfiguracionPortalVO();
	    	Boolean imagenAnterior=(Boolean) request.getSession().getAttribute("imagenAnterior");
	    	
	    	if(imagenAnterior!=null&&(form.getImagenFile()==null||form.getImagenFile().getFileName().equalsIgnoreCase(""))){
	    		newConfiguracion.setImagen(request.getSession().getAttribute("imagen").toString());	
	    	} else {
	    		newConfiguracion.setImagen(form.getImagenFile().getFileName());
	    		tratarImagen=true;
	    	}
	  
	    	if(form.getImagenPorDefectoFile()!=null&&!form.getImagenPorDefectoFile().getFileName().equalsIgnoreCase("")){
	    		logger.debug("Hay imagen por defecto");
				InternetHeaders ih = new InternetHeaders();
				DataHandler dImagen=null;
				MimeBodyPart mbp = new MimeBodyPart(ih,form.getImagenPorDefectoFile().getFileData());
				DataSource source = new MimePartDataSource(mbp);
				dImagen = 	new DataHandler(source);
				
				getSrvPublicacionService().guardarImagenPorDefecto(dImagen);
				logger.debug("Guardamos imagen por defecto.");
	    	}
	    	
			newConfiguracion.setRss(form.getRss());
	    	newConfiguracion.setPlugginBusqueda(form.getPlugginBusqueda());
	    	newConfiguracion.setDescargas(form.getDescargas());
	    	newConfiguracion.setOpenId(form.getOpenID());
	    	newConfiguracion.setRegistrese(form.getRegistrese());
	    	newConfiguracion.setAgregaWeb(form.getAgregaWeb());
	    	newConfiguracion.setNoticias(form.getNoticias());
	    	newConfiguracion.setTagging(form.getTagging());
	    	newConfiguracion.setEstadisticas(form.getEstadisticas());
	    	newConfiguracion.setInformes(form.getInformes());
	    	newConfiguracion.setEtiquetas(form.getEtiquetas());
	    	newConfiguracion.setEnlacePassword(form.getEnlacePassword());
	    	newConfiguracion.setFecha(Calendar.getInstance());
	    	newConfiguracion.setActiva(1);
	    	newConfiguracion.setGoogleAnalytic(form.getGoogleAnalytic());
	    	newConfiguracion.setCodGa(form.getCodGa());
	    	newConfiguracion.setIdRssGaleriaPortada(form.getIdRssGaleria());
	    	
	    	if(form.getIdRssGaleria().equalsIgnoreCase("0.0"))
	    		newConfiguracion.setNumOdesGaleria(0);
	    	else
	    		newConfiguracion.setNumOdesGaleria(Integer.valueOf(form.getNumOdesGaleria()));
	    	
	    	newConfiguracion.setItinerariosAprendizaje(form.getItinerariosAprendizaje());
	
	    	//Si cualquiera de los 2 es  nulo quiere decir que hemos capturado una imagen nueva
	    	if (logger.isDebugEnabled()) logger.debug("Si cualquiera de los 2 es  nulo quiere decir que hemos capturado una imagen nueva y no tratamos la imagen");
	    	if(tratarImagen){
	    		//tratamientoImagen(form.getImagenFile(),form.getImagenPortadaFile());
	    		tratamientoImagen(form.getImagenFile());
	    	}
	    	
	     	if (logger.isDebugEnabled()) logger.debug("Insertamos en la tabla");
	    	this.getSrvConfigPortal().crearConfiguracion(newConfiguracion);
	
	    	if (logger.isDebugEnabled()) logger.debug("Eliminamos imagen de session");

	    	request.getSession().removeAttribute("imagen");
	    	request.getSession().removeAttribute("configNull");
    	}
    	catch(Exception e){
    		logger.error("Excepcion en guardarConfiguracion",e);
    		throw new ValidatorException("{error.config.guardar}");
    	}
     }
    
    
    /**
	 * Método que recoge el FormFile y rellena el objeto ImagenVO para pasarselo al servicio de contenidosportal que va a tratar dichas imagenes
	 * @param  FormFile:Ficheros de imagen
	 * @return 
	 * @throws Exception
	 */
	//public void tratamientoImagen (FormFile imagenFile,FormFile imagenPortadaFile) throws Exception
	public void tratamientoImagen (FormFile imagenFile) throws Exception {
		try{
			if (logger.isDebugEnabled()) logger.debug("Tratamos la imagen");
		    
			ImagenVO imagen = new ImagenVO();
			InternetHeaders ih = new InternetHeaders();
			DataHandler dImagen=null;
			//Si hemos cogido una imagen nueva
			if(imagenFile!=null){
				MimeBodyPart mbp = new MimeBodyPart(ih,imagenFile.getFileData());
			
				DataSource source = new MimePartDataSource(mbp);
				dImagen = 	new DataHandler(source);
				
				imagen.setNombre(imagenFile.getFileName());
				imagen.setMimeType(imagenFile.getContentType());
				imagen.setDatos(dImagen);
			}
	
			//En datos le metemos el DataHandler de la imagenPortada para que no exista un null a la hora de llamar al servicio
			//Posteriormente esto no va a tener ninguna importancia puesto que no vamos a crear el fichero
			if(imagenFile==null){
				imagen.setDatos(dImagen);
				imagen.setNombre("noFile");
				imagen.setMimeType("noFile");	
			}
			
			if (logger.isDebugEnabled()) logger.debug("Llamamos al servicio->SrvConfigPortal().almacenarImagen");  	
			
			this.getSrvConfigPortal().almacenarImagen(imagen);
			
		} catch(Exception e) {
			logger.error("Excepcion en tratamientoImagen",e);
			throw new ValidatorException("{errors.config.noFolder}");
		}
	}	
	
    private String getPropertyValue(String sKey) throws IOException {
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/portaladministracion.properties");
		
		if (this.pSpringProperties == null) {
			pSpringProperties = new java.util.Properties();
			pSpringProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();
		
		// devolvemos la propiedad
		return pSpringProperties.getProperty(sKey);
	}

    private SrvPropiedadService ObtieneSrvPropiedad() throws Exception {
    	if (this.prop==null)
    	{
    		prop = this.getSrvPropiedadService();
    	}
    	return prop;
    }
}