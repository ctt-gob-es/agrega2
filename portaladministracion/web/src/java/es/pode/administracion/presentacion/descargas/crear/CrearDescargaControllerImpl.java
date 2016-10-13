// license-header java merge-point
package es.pode.administracion.presentacion.descargas.crear;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.ValidatorException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMapping;

import es.pode.administracion.presentacion.descargas.ConfigurarDescargaSession;
import es.pode.contenidos.negocio.descargas.servicio.CategoriaDescargaVO;
import es.pode.contenidos.negocio.descargas.servicio.DescDescargaVO;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.i18n.LocalizacionIdiomaVO;



/**
 * @see es.pode.administracion.presentacion.descargas.crear.CrearDescargaController
 */
public class CrearDescargaControllerImpl extends CrearDescargaController
{
	private static final long serialVersionUID = -62452977525040076L;
	private static final Logger logger = Logger.getLogger(CrearDescargaControllerImpl.class);
	private static String applicationResources="application-resources";
	private static String VACIA="";
	private static Properties props = new Properties();
	
	private static String descripcionPorDefecto = "crearDescarga.descarga.descripcion.tooltip";

    /**
     * @see es.pode.administracion.presentacion.descargas.crear.CrearDescargaController#obtenerDatos(org.apache.struts.action.ActionMapping, es.pode.administracion.presentacion.descargas.crear.ObtenerDatosForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public final void obtenerDatos(ActionMapping mapping, es.pode.administracion.presentacion.descargas.crear.ObtenerDatosForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
		ConfigurarDescargaSession sesion = getConfigurarDescargaSession(request);
		java.io.InputStream is = this.getClass().getResourceAsStream(
		"/portaladministracion.properties");
		try {
			if (is != null)
				props.load(is);
		} catch (IOException e) {
			logger.error("No se pudo cargar el fichero portaladministracion.properties");
		}
		
		if (sesion!=null) {
				List<DescDescargaVO> descripciones = sesion.getDescripciones();
				logger.debug("Lista de descripciones construida");
				logger.debug("Vamos a obtener la id");
				
				List<CrearDescargaInfo> lista=new ArrayList<CrearDescargaInfo>();
				LocalizacionIdiomaVO[] idiomas=I18n.getInstance().obtenerIdiomasPlataformaLocalizados(idiomaActual(request));
				logger.debug("El número de idiomas que tenemos es de: "+idiomas.length );
				for (int i=0;i<idiomas.length;i++) {
					CrearDescargaInfo info = new CrearDescargaInfo();
					logger.debug("La id del idioma es: " +idiomas[i].getIdLocalizacion());
					DescDescargaVO descripcion = descripcionIdioma(descripciones, idiomas[i].getIdLocalizacion());
					if (descripcion!=null) {
						logger.debug("La descripción correspondiente al idioma actual no es null");
						info.setTitulo(descripcion.getTitulo());
						info.setDescripcion(descripcion.getDescripcion());
					} else {
						logger.debug("La descripción correspondiente al idioma actual es null");
						info.setTitulo(VACIA);
						info.setDescripcion(VACIA);
					}
					info.setIdioma(idiomas[i].getIdLocalizacion());
					info.setIdiomadesc(idiomas[i].getNombre());
					lista.add(info);
				}
				logger.debug("El número de descripciones que tenemos es de: "+lista.size());
				form.setDescripciones(lista);
				form.setPath(sesion.getPath()!=null?sesion.getPath():VACIA);
				
				logger.debug("Primero suponemos que no es Herramienta Offline");
				form.setEsHerramientaOffline(false);
				
				logger.debug("Comprobamos si es Herramienta Offline");
				if (sesion.getIdentificador()!=null) {
					logger.debug("El identificador que utilizaremos es: "
							+ sesion.getIdentificador());
					CategoriaDescargaVO id = this
							.getSrvDescargas()
							.obtenerCategoriaDescarga(sesion.getIdentificador());
					String ide = id.getId() != null ? id.getId()
								.toString() : VACIA;
					logger.debug("La id obtenida es " + ide);
					logger.debug("El valor de instalador es: " + props.getProperty("instalador"));
					if (ide.equals(props.getProperty("instalador"))) {
						logger.debug("Es Herramienta Offline");
						form.setEsHerramientaOffline(true);
					}
				}
		}
	}
    
    public void guardarDescarga(ActionMapping mapping,
			GuardarDescargaForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {	
    	ResourceBundle resources = ResourceBundle.getBundle(applicationResources, (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
    	logger.debug("Accion es "+form.getAction());
		if(resources.getString("comun.aceptar").equals(form.getAction())) {
			logger.debug("Se pulso Aceptar");
			ConfigurarDescargaSession sesion=getConfigurarDescargaSession(request);
			
			java.io.InputStream is = this.getClass().getResourceAsStream(
			"/portaladministracion.properties");
			try {
				if (is != null)
					props.load(is);
			} catch (IOException e) {
				logger.error("No se pudo cargar el fichero portaladministracion.properties");
			}
			
			List<DescDescargaVO> descripciones = new ArrayList<DescDescargaVO>();
			boolean alMenosUna=false;

			String desc;
			String titulo;
			String idioma;
			
			desc=compruebaDescripcion(form.getDescripcion_es(),request);
			titulo=compruebaTitulo(form.getTitulo_es(), request);
			idioma="es";
			alMenosUna = addDescripcion(descripciones, alMenosUna, desc,
					titulo, idioma);
			
			desc=compruebaDescripcion(form.getDescripcion_ca(),request);
			titulo=compruebaTitulo(form.getTitulo_ca(), request);
			idioma="ca";
			alMenosUna = addDescripcion(descripciones, alMenosUna, desc,
					titulo, idioma);
			
			desc=compruebaDescripcion(form.getDescripcion_en(),request);
			titulo=compruebaTitulo(form.getTitulo_en(), request);
			idioma="en";
			alMenosUna = addDescripcion(descripciones, alMenosUna, desc,
					titulo, idioma);
			
			desc=compruebaDescripcion(form.getDescripcion_eu(),request);
			titulo=compruebaTitulo(form.getTitulo_eu(), request);
			idioma="eu";
			alMenosUna = addDescripcion(descripciones, alMenosUna, desc,
					titulo, idioma);
			
			desc=compruebaDescripcion(form.getDescripcion_gl(),request);
			titulo=compruebaTitulo(form.getTitulo_gl(), request);
			idioma="gl";
			alMenosUna = addDescripcion(descripciones, alMenosUna, desc,
					titulo, idioma);
			
			desc=compruebaDescripcion(form.getDescripcion_va(),request);
			titulo=compruebaTitulo(form.getTitulo_va(), request);
			idioma="va";
			alMenosUna = addDescripcion(descripciones, alMenosUna, desc,
					titulo, idioma);
			
			if(!alMenosUna) {
				throw new ValidatorException("{crearDescarga.noDescripcion.exception}");
			}
			
			String path=VACIA;
			if(sesion.getPath()==null||!sesion.getPath().equals(form.getPath())) {
				path = form.getPath();
			}
			
			Long identificador=sesion.getIdentificador()!=null?sesion.getIdentificador():null;
			
	    	identificador=getSrvDescargas().modificarDescarga(identificador, path, sesion.getActiva(),descripciones.toArray(new DescDescargaVO[]{} ));
	    	if(identificador==null) {
	    		throw new ValidatorException("{crearDescarga.noFichero.exception}");
	    	}
	    	logger.debug("El identificador es: "+identificador);
	    	logger.debug("Obtenemos si es herramienta online y en este caso la respuesta es: "+form.isEsHerramientaOffline());
			boolean esHerramientaOffline = form.isEsHerramientaOffline();
			Long ide = null; 
			
			if (esHerramientaOffline){
				
				String iden = props.getProperty("instalador");
				ide = Long.parseLong(iden);
				logger.debug("Es herramienta offline y le asignamos la categoría con ide: "+ide);
				getSrvDescargas().insertarCategoriaDescarga(identificador, ide);
			}else{
				
				String iden = props.getProperty("noInstalador");
				ide = Long.parseLong(iden);
				logger.debug("No es herramienta offline y le asignamos la categoría con ide: "+ide);
				getSrvDescargas().insertarCategoriaDescarga(identificador, ide);
			}
			
	    	form.setIdModificada(identificador);
		}
    	removeConfigurarDescargaSession(request);
		
	}

	private boolean addDescripcion(List<DescDescargaVO> descripciones, boolean alMenosUna,
			String desc, String titulo, String idioma) {
		DescDescargaVO descripcion = new DescDescargaVO(idioma,
				desc, titulo);
		descripciones.add(descripcion);
		if (desc!=null&&!desc.equals(VACIA)&&titulo!=null&&!titulo.equals(VACIA)) {
			alMenosUna=true;
		}
		return alMenosUna;
	}

	private DescDescargaVO descripcionIdioma(List<DescDescargaVO> descripciones, String idioma) {
    	DescDescargaVO descripcion=null;
    	if(descripciones!=null){
    		for (Iterator<DescDescargaVO> iterator = descripciones.iterator(); iterator
					.hasNext();) {
    			DescDescargaVO desc = iterator.next();
				if (desc.getIdioma().equals(idioma)) {
					descripcion=desc;
				}
			}
		}
    	return descripcion;
    }
    
	private String idiomaActual(HttpServletRequest request) {
		return ((Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE)).getLanguage();
	}
	
	private String compruebaDescripcion(String descripcion, HttpServletRequest request) {
		ResourceBundle resources = ResourceBundle.getBundle("application-resources", (Locale)request.getSession().getAttribute(ConstantesAgrega.DEFAULT_LOCALE));
		if(descripcion==null||descripcion.equals(resources.getString(descripcionPorDefecto))) {
			descripcion=VACIA;
		}
		return descripcion;
	}
	
	private String compruebaTitulo(String titulo, HttpServletRequest request) {
		if(titulo==null) {
			titulo=VACIA;
		}
		return titulo;
	}
	
	public static class CrearDescargaInfo {
		private String titulo;
		private String descripcion;
		private String idioma;
		private String idiomadesc;
		
		/**
		 * @return the titulo
		 */
		public String getTitulo() {
			return titulo;
		}
		/**
		 * @param titulo the titulo to set
		 */
		public void setTitulo(String titulo) {
			this.titulo = titulo;
		}
		/**
		 * @return the descripcion
		 */
		public String getDescripcion() {
			return descripcion;
		}
		/**
		 * @param descripcion the descripcion to set
		 */
		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}
		/**
		 * @return the idioma
		 */
		public String getIdioma() {
			return idioma;
		}
		/**
		 * @param idioma the idioma to set
		 */
		public void setIdioma(String idioma) {
			this.idioma = idioma;
		}
		/**
		 * @return the idiomaDesc
		 */
		public String getIdiomadesc() {
			return idiomadesc;
		}
		/**
		 * @param idiomaDesc the idiomaDesc to set
		 */
		public void setIdiomadesc(String idiomaDesc) {
			this.idiomadesc = idiomaDesc;
		}
	}
}