//license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.publicacion.negocio.servicios;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.jms.core.JmsTemplate;

import de.schlichtherle.io.FileInputStream;
import de.schlichtherle.io.FileWriter;
import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.agrega.soporte.xslt.TransformadorSaxonImpl;
import es.pode.adminusuarios.negocio.servicios.UsuarioVO;
import es.pode.auditoria.negocio.servicios.TareaEjecutadaPlanVO;
import es.pode.buscar.negocio.administrar.servicio.NodoVO;
import es.pode.buscar.negocio.administrar.servicio.SrvNodoService;
import es.pode.catalogacion.licencias.servicio.ValidarLicenciasVO;
import es.pode.configuracionPlataforma.negocio.servicios.SrvPropiedadService;
import es.pode.empaquetador.negocio.servicio.AnalizaArchivoVO;
import es.pode.entregar.negocio.servicios.SrvEntregarService;
import es.pode.fuentestaxonomicas.negocio.servicio.SrvEstructurasEducativasService;
import es.pode.fuentestaxonomicas.negocio.servicio.SrvTaxonomiaService;
import es.pode.fuentestaxonomicas.negocio.servicio.SrvVocabulariosControladosService;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonPathVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TaxonVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoVO;
import es.pode.fuentestaxonomicas.negocio.servicio.TerminoYPadreVO;
import es.pode.fuentestaxonomicas.negocio.servicio.VocabularioVO;
import es.pode.indexador.negocio.servicios.busqueda.DocVO30;
import es.pode.indexador.negocio.servicios.indexado.IdODEVO;
import es.pode.indexador.negocio.servicios.indexado.IndexadorVO;
import es.pode.indexador.negocio.servicios.indexado.LomESPrimarioVO;
import es.pode.localizador.negocio.servicios.LocalizadorVO;
import es.pode.localizador.negocio.servicios.SrvLocalizadorService;
import es.pode.parseadorXML.castor.Educational;
import es.pode.parseadorXML.castor.General;
import es.pode.parseadorXML.castor.GroupTitleTitle;
import es.pode.parseadorXML.castor.Grp_any;
import es.pode.parseadorXML.castor.Item;
import es.pode.parseadorXML.castor.Location;
import es.pode.parseadorXML.castor.Lom;
import es.pode.parseadorXML.castor.Manifest;
import es.pode.parseadorXML.castor.MetaMetadata;
import es.pode.parseadorXML.castor.Metadata;
import es.pode.parseadorXML.castor.Organization;
import es.pode.parseadorXML.castor.Relation;
import es.pode.parseadorXML.castor.Resource;
import es.pode.parseadorXML.castor.Rights;
import es.pode.parseadorXML.castor.Title;
import es.pode.parseadorXML.lomes.lomesAgrega.AccesoAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.AnnotationAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.ClassificationAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.ContribucionAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.DuracionAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.EducationalAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.EntidadAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.FechaAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.GeneralAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.IdentificadorAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.LangStringAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.LifeCycleAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.LomAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.MetaMetadataAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.RecursoAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.RelacionAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.RelationAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.RequisitoAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.RightsAgrega;
import es.pode.parseadorXML.lomes.lomesAgrega.TechnicalAgrega;
import es.pode.parseadorXML.scorm2004.agrega.ManifestAgrega;
import es.pode.publicacion.negocio.dominio.EditoresOdesExternos;
import es.pode.publicacion.negocio.dominio.EditoresOdesExternosDao;
import es.pode.publicacion.negocio.dominio.EditoresOdesExternosIdOdeCriteria;
import es.pode.publicacion.negocio.dominio.EditoresOdesExternosIdOdeEsPublicadorOdeCriteria;
import es.pode.publicacion.negocio.dominio.EditoresOdesExternosIdUsuarioEditorCriteria;
import es.pode.publicacion.negocio.dominio.Estado;
import es.pode.publicacion.negocio.dominio.EstadoCompartidoCriteria;
import es.pode.publicacion.negocio.dominio.EstadoCompartidoTituloCriteria;
import es.pode.publicacion.negocio.dominio.EstadoCompartidoUsuariosCriteria;
import es.pode.publicacion.negocio.dominio.EstadoCompartidoUsuariosTituloCriteria;
import es.pode.publicacion.negocio.dominio.EstadoCriteria;
import es.pode.publicacion.negocio.dominio.EstadoDao;
import es.pode.publicacion.negocio.dominio.EstadoDesdeHastaCriteria;
import es.pode.publicacion.negocio.dominio.EstadoTituloCriteria;
import es.pode.publicacion.negocio.dominio.EstadoTituloDesdeHastaCriteria;
import es.pode.publicacion.negocio.dominio.EstadoUsuarioDespublicadorDesdeHastaCriteria;
import es.pode.publicacion.negocio.dominio.EstadoUsuarioDespublicadorTituloDesdeHastaCriteria;
import es.pode.publicacion.negocio.dominio.EstadoUsuarioTituloCriteria;
import es.pode.publicacion.negocio.dominio.EstadoUsuariosCreacionDesdeHastaCriteria;
import es.pode.publicacion.negocio.dominio.EstadoUsuariosCreacionTituloDesdeHastaCriteria;
import es.pode.publicacion.negocio.dominio.EstadoUsuariosCreacionUsuarioDespublicadorDesdeHastaCriteria;
import es.pode.publicacion.negocio.dominio.EstadoUsuariosCreacionUsuarioDespublicadorTituloDesdeHastaCriteria;
import es.pode.publicacion.negocio.dominio.EstadoUsuariosTituloCriteria;
import es.pode.publicacion.negocio.dominio.IdODECriteria;
import es.pode.publicacion.negocio.dominio.IdODEFechaCriteria;
import es.pode.publicacion.negocio.dominio.IdODEPublicadoCriteria;
import es.pode.publicacion.negocio.dominio.IdODEsCriteria;
import es.pode.publicacion.negocio.dominio.IdODEsEstadoCriteria;
import es.pode.publicacion.negocio.dominio.IdiomaCriteria;
import es.pode.publicacion.negocio.dominio.MD5Criteria;
import es.pode.publicacion.negocio.dominio.OdeFederadoDespublicadoFecha;
import es.pode.publicacion.negocio.dominio.OdePublicado;
import es.pode.publicacion.negocio.dominio.OdePublicadoDao;
import es.pode.publicacion.negocio.dominio.OdesFederadosDespublicadosDao;
import es.pode.publicacion.negocio.dominio.OdesFederadosDespublicadosFechaCriteria;
import es.pode.publicacion.negocio.dominio.Transicion;
import es.pode.publicacion.negocio.dominio.TransicionDao;
import es.pode.publicacion.negocio.dominio.TransicionImpl;
import es.pode.publicacion.negocio.dominio.UsuarioCreacionEstadoFechaCriteria;
import es.pode.publicacion.negocio.dominio.UsuarioEstadoCriteria;
import es.pode.publicacion.negocio.dominio.UsuarioEstadoDesdeHastaCriteria;
import es.pode.publicacion.negocio.dominio.UsuariosCreacionEstadoFechaCriteria;
import es.pode.publicacion.negocio.soporte.I18nModuloPublicacion;
import es.pode.publicacion.negocio.soporte.TratamientoCuota;
import es.pode.publicacion.negocio.soporte.TratamientoImagenes;
import es.pode.publicacion.negocio.soporte.TratamientoODE;
import es.pode.soporte.constantes.ConstantesAgrega;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.utiles.date.DateManager;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;
import es.pode.soporte.utiles.imagenes.UtilesImagenes;
import es.pode.soporte.utiles.string.UtilesString;
import es.pode.validador.negocio.servicio.SrvValidadorService;
import es.pode.validador.negocio.servicio.ValidaVO;

/**
 * @author fjespino
 *
 */
public class SrvPublicacionServiceImpl extends es.pode.publicacion.negocio.servicios.SrvPublicacionServiceBase {
	
	private SrvPropiedadService prop = null;
	private JmsTemplate jmsTemplate;
	public static final String FILE_NAME_PROPERTIES = "/publicacion.properties";
	public static final String FILE_NAME_I18N = "publicacion";
	private static Properties props = null;
	private final static String MANIFEST_NAME = "imsmanifest.xml";
	private final static String LICENCIA_NAME = "licencia.txt";
	private final static String TAMANIO_PAGINA = "pagina";
	public final static String SIN_ERRORES = "0.0";
	public final static String VOCAB_BORRADOS = "0.1";
	public final static String ODE_ELIMINADO = "0.2";
	public final static String FALLO_ESCRITURA_DISCO_YA_EXISTE = "10.1";
	public final static String MEC_YA_EXISTE = "10.2";
	public final static String ERROR_INDEXACION = "10.3";
	public final static String FICHERO_ORIGINAL_ODE_NO_EXISTE = "10.4";
	public final static String IDENTIFICADOR_ODE_LOCALIZACION_INVALIDA = "10.5";
	public final static String ODE_YA_CREADO_EN_PLATAFORMA = "10.6";
	public final static String TRANSICION_ESTADO_INVALIDA = "10.7";
	public final static String FICHERO_YA_EXISTE = "10.8";
	public final static String EXCEPCION_INESPERADA = "10.9";
	public final static String CONSULTA_VALORACION_ERRONEA = "10.10";
	public final static String ERROR_PARSEO_MANIFEST = "10.11";
	public final static String ERROR_MAPEO_INDEXADO = "10.12";
	public final static String ERROR_FORMATO_ZIP = "10.13";
	public final static String ERROR_DESCOMPRIMIENDO_FICHERO_ZIP = "10.14";
	public final static String ERROR_COPIANDO_ESQUEMAS = "10.15";
	public final static String ERROR_DE_VALIDACION = "10.16";
	public final static String ERROR_EXCEDER = "10.17";
	public final static String NO_ESTADO_VALIDO = "10.18";
//	Nuevo código de error para la validación del id_mec
	protected final static String MEC_ERRONEO = "10.19";
	public final static String ERROR_EN_TRANSFORMACION= "10.20";
	public final static String ODE_NO_EXISTE = "10.21";
	public final static String ERROR_DESINDEXACION = "10.22";
	public final static String ERROR_FICHEROS_TEMPORALES= "10.23";
	public final static String ERROR_ACTUALIZAR_VERSION_INCORRECTA = "10.24";
	public final static String ERROR_GENERICO = "10.25";
	public final static String ERROR_ID_ODE_OBLIGATORIO = "10.26";
	public final static String ERROR_USUARIO_DESPUB_OBLIGATORIO = "10.27";
	public final static String ERROR_USUARIO_NO_PERMISO_ODE = "10.28";
	public final static String LINEA_ERROR = "LINEA";
	public final static String COLUMNA_ERROR = "COLUMNA";
	public final static String TEXTO_ERROR = "TEXTO_ERROR";
	protected final static String FILE_UNDERSCORE = "_";
	protected final static String FILE_ICON_SIZE = "_icon_63_100";
	public final static String DESPUBLICACION_MASIVA="DESPUBLICACION MASIVA";
	private final static String DATE_FOMAT_ID_MEC="yyyyMMdd";
	
	private final static String UNIVERSAL = "universal";
	// constantes para la generacion de la url de la ficha de los ODEs publicados
//	private final String HTTP_PROTOCOL 			= "http://";
//	private final String URL_FICHA 				= "url.recurso.ficha";
//	private final String IDENTIFICADOR_FICHA 	= "identificadorODE";
//	private final String IDIOMA_FICHA			= "idioma";
//	private static final String IDENTIFICADOR_NODO = "server.id";	

	// constantes para la gestion de las similitudes.
	private final static String SIMILITUD_MD5 = "SIMILITUD_MD5";
	private final static String SIMILITUD_SE_BASA_EN = "SIMILITUD_SE_BASA_EN";
	
	/*
	 * constantes en la base de datos: CREACION PROPUESTO PUBLICADO RECHAZADO
	 * NO_DISPONIBLE PROPUESTO_CATALOGACION ELIMINADO PUBLICADO_AUTONOMO
	 */
	public final static String CREACION = "CREACION";
	public final static String PROPUESTO = "PROPUESTO";
	public final static String PUBLICADO = "PUBLICADO";
	public final static String RECHAZADO = "RECHAZADO";
	public final static String NO_DISPONIBLE = "NO_DISPONIBLE";
	public final static String PROPUESTO_CATALOGACION = "CATALOGACION";
	public final static String ELIMINADO = "ELIMINADO";
	public final static String PUBLICADO_AUTONOMO = "PUBLICADO_AUTONOMO";
	public final static String PUBLICADO_VERSIONANDOSE = "PUBLICADO_VERSIONANDOSE";
	public final static String SOBRESCRIBIR_PUBLICADOS_SI = "s";
	public final static String SOBRESCRIBIR_PUBLICADOS_NO = "n";
	public final static int NUM_ESTADOS = 9;
	public final static String[] SECUENCIA_ESTADOS = new String[NUM_ESTADOS];
	
	public final static String ODE_COMPLETO = "ODE_COMPLETO";
	public final static String ODE_CATALOGACION_MINIMA = "ODE_CATALOGACION_MINIMA";
	public final static String ZIP_FICHEROS = "ZIP_FICHEROS";
	
	public final static String TIPO_RECURSO_DEFECTO ="integrated media";
	
	public final static String TIPO_ACCESO_UNIVERSAL = "universal";
	public final static String DESC_TIPO_ACCESO = "MEC";
	public final static String DERECHOS_AUTOR_DEFECTO = "creative commons: attribution - non commercial - share alike";
	public final static String MARCA_ODE_PUBLICADO_POR_WEB_SEMANTICA = "ODE publicado a traves de la web semantica";
	
	public SrvPublicacionServiceImpl() {
		super();
		// Inicializamos la secuencia de estados que hay en el ciclo de vida de un ODE.
		SECUENCIA_ESTADOS[0] = CREACION;
		SECUENCIA_ESTADOS[1] = PROPUESTO;
		SECUENCIA_ESTADOS[2] = PROPUESTO_CATALOGACION;
		SECUENCIA_ESTADOS[3] = PUBLICADO;
		SECUENCIA_ESTADOS[4] = RECHAZADO;
		SECUENCIA_ESTADOS[5] = NO_DISPONIBLE;
		SECUENCIA_ESTADOS[6] = ELIMINADO;
		SECUENCIA_ESTADOS[7] = PUBLICADO_AUTONOMO;
		SECUENCIA_ESTADOS[8] = PUBLICADO_VERSIONANDOSE;
	}

	/**
	 * Consulta el estado actual del ODE que le pasan. Obtenemos el estado del
	 * ODE traducido al idioma que le pasamos.
	 * 
	 * @param idODE
	 *            El identificador correspondiente al ODE.
	 * @param idioma
	 *            El idioma al que traducir el texto descriptivo del estado.
	 * @return se retorna el estado del ODE.
	 * @throws Exception
	 * 
	 */
	protected EstadoVO handleObtenEstadoPorIdODE(java.lang.String idODE, java.lang.String idioma)throws java.lang.Exception {
		if(logger.isDebugEnabled())	logger.debug("idODE[" + idODE +"]");
		TransicionDao transicion = this.getTransicionDao();
		IdODECriteria criterio = new IdODECriteria(idODE,null, null);
		List<TransicionVO> list = transicion.buscarEstadoPorCriterioIdODE(TransicionDao.TRANSFORM_TRANSICIONVO, criterio);
		if (list == null || list.size() == 0) {
			logger.warn("No hay estado para el IdODE [" + idODE + "]");
			return null;
		}
		EstadoVO estado = list.get(0).getEstadoActual();
		if(logger.isDebugEnabled())	logger.debug("idODE [" + idODE + "] esta en estado [" + estado.getClave() + "]");
		return estado;
	}

	/**
	 * Obtiene un historial de las transiciones de estados por los que ha ido
	 * pasando en su historia un ODE.
	 * 
	 * @param idODE
	 *            El identificador correspondiente al ODE.
	 * @param idioma
	 *            El idioma al que traducir el texto descriptivo del estado.
	 * @return se retorna el array con las transiciones ordenadas por fecha de
	 *         los estados del ODE
	 * @throws Exception
	 * 
	 */
	protected es.pode.publicacion.negocio.servicios.TransicionVO[] handleObtenHistorialPorIdODE(java.lang.String idODE,
			java.lang.String idioma) throws java.lang.Exception {
		return (TransicionVO[]) obtenHistorialPorIdODE(idODE, TransicionDao.TRANSFORM_TRANSICIONVO);
	}

	/**
	 * Obtiene la ultima transicion del ode al que corresponde el identificador.
	 *  
	 * @param idODE
	 *            El identificador correspondiente al ODE.
	 * @return Transicion  VO con la transición del ODE
	 * @throws Exception
	 * 
	 */
	protected Transicion handleObtenerUltimaTransicion(String idODE) {
		TransicionDao transicionDao = this.getTransicionDao();
		List<Transicion> historial = this.getTransicionDao().buscarHistorialPorIdODEOrdenado(idODE);
		if (logger.isDebugEnabled()) logger.debug("Historial de idODE["+idODE+"]["+ ((historial!=null)?historial.size():0)+"]transiciones.");
		if (historial != null && historial.size() > 0)
			return historial.get(0);
		return transicionDao.fromTransicionVO(new TransicionVO());
	}
	

	/**
	 * Obtiene la primera transicion del ode al que corresponde el identificador.
	 *  
	 * @param idODE
	 *            El identificador correspondiente al ODE.
	 * @return Transicion  VO con la transición del ODE
	 * @throws Exception
	 * 
	 */
	private Transicion obtenerPrimeraTransicion(String idODE) {
		TransicionDao transicionDao = this.getTransicionDao();
		List<Transicion> historial = this.getTransicionDao().buscarHistorialPorIdODEOrdenado(idODE);
		if (logger.isDebugEnabled()) logger.debug("Historial de idODE["+idODE+"]["+ ((historial!=null)?historial.size():0)+"]transiciones.");
		if (historial != null && historial.size() > 0)
			return historial.get(historial.size()-1);
		return transicionDao.fromTransicionVO(new TransicionVO());
	}

	
	/**
	 * Los ODEs que de publican como una nueva version de otros necesitan que su id MEC sea
	 * identico al de la version original a excepcion de la parte del identificador que 
	 * corresponde al nivel de agregacion y al idioma.
	 * Este metodo recibe un ID de MEC y reajusta la parte del mismo correspondiente al
	 * idioma y al nivel de agregacion del ODE.
	 * Este metodo esta hecho segun la implementacion de handleGeneraMEC.
	 * @return
	 */
	private String generaMECparaOdesVersionados(String localizador, String idMecAntiguo) throws Exception {
		
		StringBuffer nuevoIdMec = new StringBuffer();
		
		//Posicion en el string de entrada donde esta especificado el nivel de agregacion
		//y el idioma. A partir de esta posicion sera necesario modificar el id de mec antiguo
		int posicionAmodificar=0;
		
		String administracion = getPropertyValue("mec.prefix");	
		int longCampoAdministracion = administracion.length();
		
		//Actualizamos posiciones
		posicionAmodificar+=longCampoAdministracion;
		
		//Vemos si el id Mec antiguo tiene la comunidad
		if(idMecAntiguo.contains("-")) {
			posicionAmodificar+=3;	
		}
		
		String separador=FILE_UNDERSCORE;
		String formatoFecha=DATE_FOMAT_ID_MEC;

		//Actualizamos posiciones ya que ahora vendria un separador 
		//y la fecha en formato yyyyMMdd
		posicionAmodificar+=separador.length()+formatoFecha.length();
		
		// Obtenemos el nivel de agregacion del ODE. Hay que parsearlo
		// Extraemos el manifest
		File extraeSubmanifest = new File(localizador, MANIFEST_NAME);
		Manifest imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);
		ManifestAgrega manifest = new ManifestAgrega(imsmanifest);
		ArrayList<Lom> listLomes = new ArrayList<Lom>(manifest.recuperarLomes());		
		
		//Cargamos el nivel de Agregacion y el idioma con los valores por defecto
		int nivelDeAgregacion = 1; 
		String idioma = FILE_UNDERSCORE;
		
		if (listLomes != null && listLomes.size() > 0) {
			LomAgrega lomAg = new LomAgrega(listLomes.get(0));
			GeneralAgrega generalAgrega = lomAg.getGeneralAgrega();
			if (generalAgrega != null) {
				try {
					nivelDeAgregacion = Integer.parseInt(generalAgrega.getNivelDeAgregacion());
				} catch (Exception e) {
					logger.error("ERROR: el campo nivel agregacion["
							+ generalAgrega.getNivelDeAgregacion()+ "] no es un entero valido. Se utiliza valor por defecto [" + nivelDeAgregacion + "].",e);
				}
			}
			MetaMetadataAgrega metadata = lomAg.getMetaMetadataAgrega();
			if(metadata != null){
				try{
					String idiomaMetadata=metadata.getIdiomaAv();
					String codigo="codigo_"+idiomaMetadata;
					String codigoNumerico=getPropertyValue(codigo);
					if(codigoNumerico!=null && !codigoNumerico.equals("")){
						idioma=codigoNumerico;
					}
					logger.debug("El codigo del idioma es "+idioma);
				}catch (Exception e) {
					logger.error("ERROR: el campo idioma["
							+ metadata.getIdiomaAv()
							+ "] no es un idioma valido. Se utiliza valor por defecto [" +idioma +"].",e);
				}
			}
		}
		
		nuevoIdMec.append(idMecAntiguo);
		String idiomaYnivelAgregacion=idioma+nivelDeAgregacion;
		nuevoIdMec.replace(posicionAmodificar, posicionAmodificar+idiomaYnivelAgregacion.length(), idiomaYnivelAgregacion);

		posicionAmodificar+=idiomaYnivelAgregacion.length();
		
		String[] partesIdMec = idMecAntiguo.split(FILE_UNDERSCORE);
		String finIdMec=FILE_UNDERSCORE+partesIdMec[partesIdMec.length-1];
		nuevoIdMec.replace(posicionAmodificar, posicionAmodificar+finIdMec.length(), finIdMec);
		
		// si ya existe lanzamos una excepción
		IdODECriteria criteria = new IdODECriteria(nuevoIdMec.toString(), null, null);
		if (!this.getTransicionDao().buscarEstadoPorCriterioIdODE(criteria).isEmpty()
				&& !(nuevoIdMec.toString().equals(idMecAntiguo))
				) {
			// solo necesitamos comprobar la última transición, pq el mec no cambia nunca
			logger.warn("Se ha generado un mec nuevo ya existente");
			throw new Exception("Se ha generado un mec nuevo ya existente");
		}
		logger.info("Generacion de mec para el ODE versionado: " + localizador + " terminada, mec nuevo: " + nuevoIdMec.toString() + " mec antiguo: " + idMecAntiguo);
		return nuevoIdMec.toString();
	}
	
	
	/**
	 * Genera un identificador MEC valido para el ODE.
	 * 
	 * @param localizador
	 *            Ruta del fichero imsmanifest.
	 * @return se retorna el identificador del MEC.
	 * @throws Exception
	 */
	protected String handleGeneraMEC(String localizador) throws Exception {
		if (logger.isDebugEnabled()) logger.debug("Iniciamos generacion de mec para: " + localizador);
		//DependentServerPropertiesItf properties = DependentServerProperties.getInstance();
		String comunidad = ObtieneSrvPropiedad().getValorPropiedad(ConstantesAgrega.AMBITO_NODO).toLowerCase();
		String administracion = SrvPublicacionServiceImpl.getPropertyValue("mec.prefix");
		StringBuffer mecSB = new StringBuffer();
		mecSB.append(administracion);
		// Si la comunidad tiene dos caracteres es un nodo de Agrega distinto del MEC
		// Metemos si nombre corto en el identificador
		if (comunidad.length()==2) {
			mecSB.append("-");
			mecSB.append(comunidad);
		}
		mecSB.append(SrvPublicacionServiceImpl.FILE_UNDERSCORE);
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FOMAT_ID_MEC);
		mecSB.append(formatter.format(new Date()));

		// Obtenemos el nivel de agregacion del ODE. Hay que parsearlo
		// Extraemos el manifest
		File extraeSubmanifest = new File(localizador, MANIFEST_NAME);
		Manifest imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);
		ManifestAgrega manifest = new ManifestAgrega(imsmanifest);
		ArrayList<Lom> listLomes = new ArrayList<Lom>(manifest.recuperarLomes());
		int nivelDeAgregacion = 1; // utilizamos el nivel de agregacion 1 por defecto
		String idioma = SrvPublicacionServiceImpl.FILE_UNDERSCORE;
		if (listLomes != null && listLomes.size() > 0) {
			LomAgrega lomAg = new LomAgrega(listLomes.get(0));
			GeneralAgrega generalAgrega = lomAg.getGeneralAgrega();
			if (generalAgrega != null) {
				try {
					nivelDeAgregacion = Integer.parseInt(generalAgrega.getNivelDeAgregacion());
				} catch (Exception e) {
					logger.error("ERROR: handleGeneraMEC el campo nivel agregacion["
							+ generalAgrega.getNivelDeAgregacion()+ "] no es un entero valido. Se utiliza valor por defecto [" + nivelDeAgregacion + "].",e);
				}
			}
			MetaMetadataAgrega metadata = lomAg.getMetaMetadataAgrega();
			if(metadata != null){
				try{
					String idiomaMetadata=metadata.getIdiomaAv();
					String codigo="codigo_"+idiomaMetadata;
					String codigoNumerico=SrvPublicacionServiceImpl.getPropertyValue(codigo);
					if(codigoNumerico!=null && !codigoNumerico.equals("")){
						idioma=codigoNumerico;
					}
					logger.debug("El codigo del idioma es "+idioma);
				}catch (Exception e) {
					logger.error("ERROR: handleGeneraMEC el campo idioma["
							+ metadata.getIdiomaAv()
							+ "] no es un idioma valido. Se utiliza valor por defecto [" +idioma +"].",e);
				}
			}
		}
		mecSB.append(idioma);
		mecSB.append(nivelDeAgregacion);
		mecSB.append(SrvPublicacionServiceImpl.FILE_UNDERSCORE);
		mecSB.append(9);
		// horas minutos y segundos es un identificador único para
		// 1 millón de documentos diarios por comunidad, pero que
		// posibilidades hay que se publiquen dos elementos a la vez
		// justo en el mismo instante de tiempo?.
		formatter = new SimpleDateFormat("kkmmss");
		String date = formatter.format(new Date());
		mecSB.append(date);

		// si ya existe lanzamos una excepción
		IdODECriteria criteria = new IdODECriteria(mecSB.toString(), null, null);
		if (!this.getTransicionDao().buscarEstadoPorCriterioIdODE(criteria).isEmpty()) {
			// solo necesitamos comprobar la última transición, pq el mec no cambia nunca
			logger.warn("Se ha generado un mec nuevo ya existente");
			throw new Exception("Se ha generado un mec nuevo ya existente");
		}
		logger.info("Generacion de mec para: " + localizador + " terminada, mec: " + mecSB.toString());
		return mecSB.toString();
	}

	/**
	 * Busca identificadores de ODE's cuyo estado actual sea el mismo que el
	 * suministrado.
	 * @param estado
	 *            Estado en el que se encuentra el ODE.
	 * @return se retorna el array de identificadores de ODEs.
	 * @throws Exception
	 * 
	 */
	protected es.pode.publicacion.negocio.servicios.IdODEVO[] handleBuscarODEsPorEstado(String estado) throws Exception {
		try {
			if (logger.isDebugEnabled())
				logger.debug("buscando Odes por estado: " + estado);
			TransicionDao transicion = this.getTransicionDao();
			EstadoCriteria criterio = new EstadoCriteria();
			EstadoDao estadoDao = this.getEstadoDao();

			criterio.setEstadoActual(estadoDao.obtenEstadoPorNombre(estado));
			List<TransicionVO> transiciones = transicion.buscarODEsPorCriterioEstado(TransicionDao.TRANSFORM_TRANSICIONVO, criterio);
			List<es.pode.publicacion.negocio.servicios.IdODEVO> IdOdeVOs = new ArrayList<es.pode.publicacion.negocio.servicios.IdODEVO>();

			// aqui rellenar el ideodevo como en publicar pif
			for (int i = 0; i < transiciones.size(); i++) {
				// obtenemos el localizador, que es la llave para toda la info del ode
				String idOde = transiciones.get(i).getIdODE();
//				if (logger.isDebugEnabled()) logger.debug("Encontrado Ode" + idOde + " en estado: " + estado);
				String rutaManifest = this.getSrvLocalizadorService().consultaLocalizador(idOde).getPath();
				File extraeSubmanifest = new File(rutaManifest, MANIFEST_NAME);
				Manifest imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);

				// rellenamos y añadimos a la lista todos los idodevos
				es.pode.publicacion.negocio.servicios.IdODEVO IdeOdePub = new es.pode.publicacion.negocio.servicios.IdODEVO();
				// mapeo
				ManifestAgrega manifAgrega = new ManifestAgrega(imsmanifest);
//				Long tamanioFloat = tamanioDir(rutaManifest);
				// 11042013 
				// Si en algun momento al realizar esta operacion se pierde la imagen del ode, se debe obtener la imagen actual del indice
				// y pasarsela como último parametro al TratamientoODE.rellenarIdOdeVO para que la mantenga
				// En el método reindexarODE hay un ejemplo que funciona correctamente.				
				this.getBeanMapper().map(
						TratamientoODE.rellenaIdOdeVO(
								manifAgrega,
								rutaManifest,
								imsmanifest.getIdentifier(),
								"3",
								new Float(tamanioDir(rutaManifest).floatValue()),
								this.getSrvNodoService(),
								this.getSrvEstructurasEducativasService(),
								this.getSrvTaxonomiaService(),
								this,
								this.getSrvPropiedadService()
								), IdeOdePub);
				IdOdeVOs.add(IdeOdePub);
			}
			logger.info("Encontrados " + IdOdeVOs.size() + " odes con estado: " + estado);

			if (IdOdeVOs.size() > 0) {
				es.pode.publicacion.negocio.servicios.IdODEVO[] res = new es.pode.publicacion.negocio.servicios.IdODEVO[IdOdeVOs.size()];
				IdOdeVOs.toArray(res);
				return res;
			}
			// si no ha habido nada al menos no devolvemos un null, aunque sí claro un length 0.
			return (new es.pode.publicacion.negocio.servicios.IdODEVO[0]);
		} catch (Exception e) {
			BuscarODEsEstadoException exception = new BuscarODEsEstadoException(
					"ERROR: fallo inesperado al buscar por estado " + estado, e);
			logger.error("ERROR: fallo inesperado al buscar por estado: " + estado + "; ", exception);
			throw exception;
		}
	}

	/**
	 * Obtiene un historial de las transiciones de estados por los que ha ido
	 * pasando en su historia un ODE.
	 * 
	 * @param idODE
	 *            Identificador del ODE.
	 * @return se retorna el array con las transiciones ordenadas por fecha de
	 *         los estados del ODE.
	 * @throws Exception
	 * 
	 */
	protected TransicionVO[] handleObtenHistorialPorIdODE(String idODE) throws Exception {
		return (TransicionVO[]) obtenHistorialPorIdODE(idODE, TransicionDao.TRANSFORM_TRANSICIONVO);
	}

	/* Este método convierte un DataHandler a un archivo temporal, para luego descomprimirlo. */
	private String pifATemp  (LocalizadorVO localizadorNP, DataHandler pif) throws Exception{
		String pathtemp = "";
		pathtemp = localizadorNP.getPath() + SrvPublicacionServiceImpl.getPropertyValue("carpeta.temporal") + "/" + localizadorNP.getIdentificador() + ".zip";
//		String pathLocal = localizadorNP.getPath();
//		int position = pathLocal.lastIndexOf("/");
//		String pathPadre=pathLocal.substring(0, position);
		File ficheroZip = new File(pathtemp);
		(ficheroZip.getParentFile()).mkdirs();
		ficheroZip.createNewFile();
		ficheroZip.deleteOnExit();
		FileOutputStream fos = new FileOutputStream(ficheroZip);
		pif.writeTo(fos);
		String pathtempDestino = localizadorNP.getPath();
		File destinoTemporal = new File(pathtempDestino);
		destinoTemporal.mkdirs();
		destinoTemporal.deleteOnExit();//Vemos si quitando esto borra los temporales de los odes que no se han importado
		fos.close();
		if(logger.isDebugEnabled()) logger.debug("Publicando PIF (de carga):Comenzamos a descomprimir el PIF"+ficheroZip.getPath()+ "" + "  y guardar en:" + pathtemp+";");
		return pathtemp;
	}


	/**
	 * Errores del validador: 0.0 sin errores 1.1 imsmanifest es incorrecto 1.2
	 * error de parseo en el manifest 1.3 error, la etiqueta principal debe ser
	 * manifest o lom 1.4 error de chequeo de contenidos 2.1 el LOM-ES es
	 * obligatorio para metadata 2.2 el LOM-ES es incorrecto 2.3 el LOM-ES es
	 * incorrecto para la etiqueta de organizaciones 3.1 no existe el ODE 4.1
	 * los datos del formulario son obligatorios 4.2 el Titulo es obligatorio
	 * 4.3 el Idioma es obligatorio 4.4 la Descripcion es obligatoria 4.5 el
	 * Tipo es obligatorio 4.6 el Contexto es obligatorio 4.7 la Edad es
	 * obligatoria 4.8 el Idioma Destino es obligatorio 4.9 el Proceso Cognitivo
	 * es obligatorio Errores detectables en la publicacion de un ode via el
	 * metodo publica PIF 10.1 Fallo en escritura a disco o el fichero ya existe
	 * 10.2 El mec ya existía 10.3 No se ha podido publicar el ode, error en
	 * indexación 10.4 El fichero original del ODE no existe 10.5 El
	 * identificador del ODE tiene una lozalizacion invalida 10.6 El ODE ya esta
	 * creado en la plataforma
	 * 
	 */

	/**
	 * Publica objetos en formato PIF (ZIP).
	 * 
	 * @param pif
	 *            Objeto en formato PIF que se va a publicar.
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @param comentarios
	 *            Comentarios que puede llevar la publicación.
	 * @param sobrescribir
	 *            Indica si la carga se ha de realizar sobrescribiendo el ODE si
	 *            este ya existe. Valores s/n.
	 * @return se retorna un VO con el error que se ha producido en el caso de
	 *         detectarse algun problema.
	 * @throws Exception
	 * 
	 */
	protected ResultadoOperacionVO handlePublicarPIF(DataHandler pif, String idUsuario, String comentarios,
			String sobrescribir, String titulo) throws Exception {
		try {
			String idODE = String.valueOf(System.currentTimeMillis());
			// creacion
//			logger.info("Publicando PIF (de carga) utilizando identificador[" + idODE + "] idUsuario[" + idUsuario+ "] y comentarios[" + comentarios + "]");
			ResultadoOperacionVO res = handleCreacion(idODE, idUsuario, comentarios, titulo);
			if (!res.getIdResultado().equals(SIN_ERRORES)) {
				if (logger.isDebugEnabled())
					logger.warn("ERROR[" + res.getIdResultado() + "][" + res.getDescripcion()
							+ "]:publicando PIF con fichero[" + pif != null ? pif.getName() : "null" + "] idusuario["
									+ idUsuario + "] comentarios[" + comentarios + "] idODE["+ res.getIdODE());
				return res;
			}
			SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
			LocalizadorVO localizadorNP = localizadorService.consultaLocalizador(idODE);

			logger.debug("Publicando PIF (de carga):Encontramos localizador con identificador[" + localizadorNP.getIdentificador() + "] usuario["
					+ localizadorNP.getIdUsuario() + "] MEC[" + localizadorNP.getMec() + "] path["
					+ localizadorNP.getPath() + "] URL[" + localizadorNP.getUrl() + "]");

			// empezamos a descomprimir el pif y guardar lo que nos devuelve en un directorio temporal
			String pathtemp = pifATemp(localizadorNP, pif);
			File ficheroZip = new File(pathtemp);
			if (this.getZipDao().esZip(pathtemp)){
				try {
					this.getZipDao().descomprimir(ficheroZip.getPath(), localizadorNP.getPath());
				} catch (Exception e1) {
					logger.error("Publicando PIF (de carga):Error descomprimiendo fichero [" + pathtemp + "], con nombre[" + pif.getName() + "]",e1);
					this.handleEliminar(idODE, idUsuario);
					return new ResultadoOperacionVO(ERROR_DESCOMPRIMIENDO_FICHERO_ZIP,
							I18nModuloPublicacion.getPropertyValueI18n(ERROR_DESCOMPRIMIENDO_FICHERO_ZIP),idODE,0L);
				}
			}else {
				logger.warn("Error de formato descomprimiendo fichero [" + pathtemp + "], con nombre[" + pif.getName()+ "]");
				this.handleEliminar(idODE, idUsuario);
				return new ResultadoOperacionVO(ERROR_FORMATO_ZIP, I18nModuloPublicacion.getPropertyValueI18n(ERROR_FORMATO_ZIP),idODE,0L);
			}
			// copiar los esquemas por si acaso no los trajese
			try {
				TratamientoODE.copiarEsquemas(localizadorNP.getPath());
			} catch (Exception e1) {
				logger.error("Publicando PIF (de carga):No se pudieron copiar los esquemas al importar un pif: ", e1);
				this.handleEliminar(idODE, idUsuario);
				return new ResultadoOperacionVO(ERROR_COPIANDO_ESQUEMAS, I18nModuloPublicacion.getPropertyValueI18n(ERROR_COPIANDO_ESQUEMAS),idODE,0L);
			}

			// validador
			SrvValidadorService validadorService = this.getSrvValidadorService();

			ValidaVO valid = validadorService.validarCargaOde(localizadorNP.getPath());
//			logger.info("Publicando PIF (de carga):Validando el ODE [" + localizadorNP.getIdentificador() + "]");

			if (valid.getEsValidoManifest().booleanValue()) {
				// proponiendo catalogacion
				logger.debug("Publicando PIF (de carga):Proponemos para catalogacion el ODE con identificador[" + idODE + "], idUsuario["
						+ idUsuario + "] y comentarios[" + comentarios + "]");
//				Proponemos para catalogar, pero sin validar ya que ya lo hemos hecho
				this.proponerCatalogacion(idODE, idUsuario, comentarios, titulo, false,false);

				// proponer publicacion
//				logger.info("Publicando PIF (de carga):Proponemos para publicacion el ODE con identificador[" + idODE + "], idUsuario["
//						+ idUsuario + "] y comentarios[" + comentarios + "]");
//				Proponemos para publicar, pero sin validar ya que ya lo hemos hecho
				this.proponerPublicacion(idODE, idUsuario, comentarios, titulo, false);

				// publicar
				// llamada a un metodo "validadorService.validarMec" que devuelve null si el mec es malo o no existe
				// y devuelve el valor del mec, si éste, es bueno
				String mec = validadorService.validarMec(localizadorNP.getPath());
				if (mec == null) {
//					logger.warn("El ode no tiene un id_mec valido");
					this.handleEliminar(idODE, idUsuario);
					logger.warn("Publicando PIF (de carga): El ODE [" + localizadorNP.getPath() + "] tiene id_mec nulo y no es valido.");
					logger.debug("Borramos la carpeta "+ficheroZip); //+" si existe y tambien el zip que esta en el temporal "+fileTemporal);
					UtilesFicheros.eliminar(ficheroZip);
					return new ResultadoOperacionVO(MEC_ERRONEO,I18nModuloPublicacion.getPropertyValueI18n(MEC_ERRONEO),idODE,0L);
				}
				IdODECriteria criteria = new IdODECriteria(mec, null, null);
				if (!this.getTransicionDao().buscarEstadoPorCriterioIdODE(criteria).isEmpty()) {
					logger.debug("Publicando PIF (de carga):El ODE [" + localizadorNP.getPath() + "] con mec[" + mec+ "] ya existia en la plataforma.");
					if (SOBRESCRIBIR_PUBLICADOS_NO.equals(sobrescribir)) {
						// solo necesitamos comprobar la última transición, pq  el mec no cambia nunca
						// Eliminamos todo lo que ha producido el intento de insercion de este ODE 
						// que al final ha resultado repetido
						this.handleEliminar(idODE, idUsuario);
						logger.warn("Publicando PIF (de carga):El ODE [" + localizadorNP.getPath() + "] con mec[" + mec	+ "] ya existía y no lo sobrescribimos.");
						logger.debug("Borramos la carpeta "+ficheroZip); //+" si existe y tambien el zip que  esta en el temporal "+fileTemporal);
						UtilesFicheros.eliminar(ficheroZip);
						return new ResultadoOperacionVO(MEC_YA_EXISTE, I18nModuloPublicacion.getPropertyValueI18n(MEC_YA_EXISTE),idODE,0L);
					} else if (SOBRESCRIBIR_PUBLICADOS_SI.equals(sobrescribir)) {
						logger.info("Publicando PIF (de carga):El ODE [" + localizadorNP.getPath() + "] con mec[" + mec
								+ "] existe. Eliminamos rastro para sobrescribirlo.");
						eliminaODEPublicado(mec);
					} else {
						logger.warn("Publicando PIF (de carga):El mec ya existía y no hay criterio claro de sobrescritura[" + sobrescribir+ "]");
						this.handleEliminar(idODE, idUsuario);
						logger.debug("Borramos la carpeta "+ficheroZip); //+" si existe y tambien el zip que  esta en el temporal "+fileTemporal);
						UtilesFicheros.eliminar(ficheroZip);
						return new ResultadoOperacionVO(MEC_YA_EXISTE, I18nModuloPublicacion.getPropertyValueI18n(MEC_YA_EXISTE),idODE,0L);
					}
				}
				// vamos a comprobar que el mec no existe ya
				EstadoVO estado = this.obtenEstadoPorIdODE(idODE, LdapUserDetailsUtils.getIdioma());
				logger.debug("Publicando PIF (de carga):Publicando ODE con identificador [" + idODE + "] en estado[" + estado.getClave()
						+ "] con usuario[" + idUsuario + "] y comentarios[" + comentarios + "].");
				if (estado.getClave().equals(PROPUESTO)) {
					// los odes que se cargan masivamente (de momento) no tenemos que introducir información de licencias
					ResultadoOperacionVO retorno = publicar_aux(idODE, mec, idUsuario, comentarios, null, "", "", false);
					if(SIN_ERRORES.equals(retorno.getIdResultado())){
						logger.info("Publicando PIF (de carga):Publicado ODE con identificador[" + idODE + "] a mec[" + mec + "] via PIF.");
					}else{
						// Si la publicacion no ha ido bien, entendemos que ha tenido error y tenemos que borrar 
						// todo lo que el intento de publicacion ha creado en la plataforma
						logger.warn("Publicando PIF (de carga):Error intentando publicar un ODE via PIF[" + retorno.getIdResultado()+ "] con id[" + idODE + "].");
						logger.debug("Borramos la carpeta "+ficheroZip); //+" si existe y tambien el zip que  esta en el temporal "+fileTemporal);
						UtilesFicheros.eliminar(ficheroZip);
						this.handleEliminar(idODE, idUsuario);
					}
					boolean salida=getSrvCacheService().borrarHitCache();
					if(!salida) logger.debug("Error al borrar la cache.");
					return retorno;
				}
				logger.warn("Publicando PIF (de carga):error no se ha podido continuar ya que el ODE ya [" + mec + "], está creado");
				PublicarException e = new PublicarException("ERROR el ODE[" + mec+ "]ya esta creado en la plataforma");
				logger.warn(e);
				logger.debug("Borramos la carpeta "+ficheroZip ); //+" si existe y tambien el zip que  esta en el temporal "+fileTemporal);
				UtilesFicheros.eliminar(ficheroZip);
				return new ResultadoOperacionVO(ODE_YA_CREADO_EN_PLATAFORMA, I18nModuloPublicacion.getPropertyValueI18n(ODE_YA_CREADO_EN_PLATAFORMA),idODE,0L);
			}// if (valid.getEsValidoManifest().booleanValue())
			logger.warn("Publicando PIF (de carga):ERROR: no se ha validado el ODE [" + localizadorNP.getPath() + "] correctamente, codigo["
					+ valid.getResultadoValidacion() + "] ");
			this.handleEliminar(localizadorNP.getIdentificador(), idUsuario);	
			logger.debug("Borramos la carpeta "+ficheroZip); //+" si existe y tambien el zip que  esta en el temporal "+fileTemporal);
			UtilesFicheros.eliminar(ficheroZip);
			return new ResultadoOperacionVO(SrvPublicacionServiceImpl.ERROR_DE_VALIDACION, valid.getResultadoValidacion(),idODE,0L);
		}// try
		catch (PublicarException e) {
			logger.error("Publicando PIF (de carga):Se ha producido un error al publicarPIF del tipo PublicarException", e);
			throw e;
		} catch (Exception e) {
			logger.error("Publicando PIF (de carga):Se ha producido un error al publicarPIF del tipo Desconocido", e);
			throw e;
		}
	}

	/**
	 * Crea un PIF en estado catalogado formato PIF (ZIP).
	 * 
	 * @param pif
	 *            Objeto en formato PIF que se va a dejar en estado catalogado.
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @param comentarios
	 *            Comentarios que puede llevar cambiar el estado a catalogado.
	 * @param titulo
	 *            titulo del PIF
	 * @return se retorna un VO con el error que se ha producido en el caso de
	 *         detectarse algun problema.
	 * @throws Exception
	 * 
	 */
	protected ResultadoOperacionVO handleCrearPIFCatalogado(DataHandler pif, String idUsuario, String comentarios,String titulo) throws Exception {
		try {
			// creacion
			ResultadoOperacionVO res =this.handleCrearPIF(pif, idUsuario, comentarios, titulo, LdapUserDetailsUtils.getIdioma());//El idioma no lo necesitamos, pues luego lo vamos a modificar
			logger.info("Creando PIF en estado Catalogado utilizando identificador[" + res.getIdODE() + "] idUsuario[" + idUsuario
					+ "] y comentarios[" + comentarios + "]");
			if(!res.getIdResultado().equals(SIN_ERRORES)){
				logger.warn("Error creando PIF en estado Catalogado utilizando identificador["+ res.getIdODE()+"]");
				return new ResultadoOperacionVO(res.getIdResultado(), res.getDescripcion(),res.getIdODE(),0L);
			}
			Transicion transicionActual = handleObtenerUltimaTransicion(res.getIdODE());
			if (transicionActual.getIdODE() != null) {
				titulo=transicionActual.getTitulo();
			}
			ResultadoOperacionVO resultadoProponer=handleProponerCatalogacion(res.getIdODE(), idUsuario, comentarios, titulo);
			if(!resultadoProponer.getIdResultado().equals(SIN_ERRORES)){
				logger.warn("Error proponiendo PIF utilizando identificador["+ res.getIdODE()+"]");
				this.handleEliminar(res.getIdODE(), idUsuario);
				if(logger.isDebugEnabled())	logger.debug("Eliminando PIF de identificador["+ res.getIdODE()+"]");
				return new ResultadoOperacionVO(resultadoProponer.getIdResultado(), resultadoProponer.getDescripcion(),resultadoProponer.getIdODE(),0L);
			}
			return resultadoProponer;
		}// try
		catch (PublicarException e) {
			logger.error("Creando PIF en estado Catalogado:Se ha producido un error del tipo PublicarException", e);
			throw e;
		} catch (Exception e) {
			logger.error("Creando PIF en estado Catalogado:Se ha producido un error del tipo Desconocido", e);
			throw e;
		}
	}

	/**
	 * Calcula los identificadores de ODEs que se encuentren en estado
	 * publicado.
	 * 
	 * @return se retorna una lista de identificadores de ODEs.
	 * @throws Exception
	 * 
	 */
	protected es.pode.publicacion.negocio.servicios.IdODEVO[] handleObtenODEsPublicados() throws Exception {
		// buscar aquí en la bbdd los odes publicados a pelo, parsearlos y devolver idodevo
		String estado = SrvPublicacionServiceImpl.PUBLICADO;
		es.pode.publicacion.negocio.servicios.IdODEVO[] arrayIds = this.handleBuscarODEsPorEstado(estado);
		if (arrayIds != null && arrayIds.length > 0) {
			SrvLocalizadorService localizador = this.getSrvLocalizadorService();
			for (int i = 0; i < arrayIds.length; i++) {
				String identifier = arrayIds[i].getIdODE();
				LocalizadorVO localizacion = localizador.consultaLocalizador(identifier);
				arrayIds[i].setLocalizador(localizacion.getPath());
			}
		}
		logger.info("Obtenidos " + arrayIds!=null?arrayIds.length:0 + " odes publicados");
		return arrayIds;
	}
		
	/**
	 * Calcula los identificadores de ODEs que se encuentren en estado
	 * publicado_autonomo.
	 * 
	 * @return se retorna una lista de identificadores de ODEs.
	 * @throws Exception
	 * 
	 */
	protected es.pode.publicacion.negocio.servicios.TransicionVO[] handleObtenODEsPublicadosAutonomo() throws Exception {

		if(logger.isDebugEnabled())
			logger.debug("buscando Odes por estado: " + SrvPublicacionServiceImpl.PUBLICADO_AUTONOMO);
		TransicionDao transicion = this.getTransicionDao();
		EstadoCriteria criterio = new EstadoCriteria();
		EstadoDao estadoDao = this.getEstadoDao();
		criterio.setEstadoActual(estadoDao.obtenEstadoPorNombre(SrvPublicacionServiceImpl.PUBLICADO_AUTONOMO));
		List<TransicionVO> transiciones = transicion.buscarODEsPorCriterioEstado(TransicionDao.TRANSFORM_TRANSICIONVO, criterio);
		logger.info("Obtenidos " + transiciones.size() + " odes en estado " + SrvPublicacionServiceImpl.PROPUESTO_CATALOGACION);
		return transiciones.toArray(new TransicionVO[transiciones.size()]);
	}
	
	
	/**
	 * Este metodo busca los ODEs que hay en la plataforma en el estado que le
	 * pasan para el usuario que le pasan.
	 * 
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @param estado
	 *            Estado en el se encuentra el ODE.
	 * @return se retorna una lista de transiciones  de los ODEs.
	 * @throws Exception
	 * 
	 */
	protected TransicionVO[] handleBuscarODEsPorEstadoUsuario(String idUsuario, String estado) throws Exception {
		if(logger.isDebugEnabled())
			logger.debug("buscando Odes por estado: " + estado + " para el usuario: " + idUsuario);
		TransicionDao transicion = this.getTransicionDao();
		EstadoDao estadoDao = this.getEstadoDao();
		UsuarioEstadoCriteria uec = new UsuarioEstadoCriteria();

		uec.setIdUsuario(idUsuario);
		uec.setEstadoActual(estadoDao.obtenEstadoPorNombre(estado));
		uec.setEstadoTransitado(null);

		List transiciones = transicion.buscarODEsPorCriterioEstadoUsuario(uec);
		TransicionVO[] resultado = new TransicionVO[transiciones.size()];
		for (int i = 0; i < transiciones.size(); i++) {
			// mapaemos los resultados
			TransicionVO aux = new TransicionVO();
			this.getBeanMapper().map(transiciones.get(i), aux);
			resultado[i] = aux;
		}
		logger.info("Obtenidos " + resultado.length + "odes en estado " + estado + " para el usuario " + idUsuario);
		return resultado;
	}

	/**
	 * Recupera la lista de ODEs en estado CREADO asociados a un usuario.
	 * 
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @return se retorna una lista de transiciones de ODEs.
	 * @throws Exception
	 * 
	 */
	protected TransicionVO[] handleObtenODEsCreadosPorUsuario(String idUsuario) throws Exception {
		return SrvPublicacionServiceImpl.obtenerODEsPorTresEstadosUsuario(idUsuario, SrvPublicacionServiceImpl.CREACION,
				SrvPublicacionServiceImpl.RECHAZADO, SrvPublicacionServiceImpl.PUBLICADO_VERSIONANDOSE, this.getEstadoDao(), this.getTransicionDao());

	}

	/**
	 * Este metodo devuelve los ODEs en estado PROPUESTO asociados al usuario
	 * dado.
	 * 
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @return se retorna una lista de transiciones de los ODEs.
	 * @throws Exception
	 * 
	 */
	protected TransicionVO[] handleObtenODEsPropuestosPorUsuario(String idUsuario) throws Exception {
		return SrvPublicacionServiceImpl.obtenerODEsPorEstadoUsuario(idUsuario, SrvPublicacionServiceImpl.PROPUESTO, this.getEstadoDao(), this.getTransicionDao());
	}

	/**
	 * Este metodo selecciona los ODEs en estado PUBLICADO asociados al usuario
	 * dado.
	 * 
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @return se retorna una lista de identificadores de ODEs.
	 * @throws Exception
	 * 
	 */
	protected TransicionVO[] handleObtenODEsPublicadosPorUsuario(String idUsuario) throws Exception {
		return SrvPublicacionServiceImpl.obtenerODEsPorEstadoUsuario(idUsuario, SrvPublicacionServiceImpl.PUBLICADO, this.getEstadoDao(), this.getTransicionDao());
	}

	/**
	 * Este metodo selecciona los ODEs en estado PUBLICADO_AUTONOMO asociados al usuario
	 * dado.
	 * 
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @return se retorna una lista de identificadores de ODEs.
	 * @throws Exception
	 * 
	 */
	protected TransicionVO[] handleObtenODEsPublicadosAutonomoPorUsuario(String idUsuario) throws Exception {
		return SrvPublicacionServiceImpl.obtenerODEsPorEstadoUsuario(idUsuario, SrvPublicacionServiceImpl.PUBLICADO_AUTONOMO, this.getEstadoDao(), this.getTransicionDao());
	}
	
	/**
	 * Devuelve los odes del usuario que estan en estado rechazado.
	 * 
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @return se retorna una lista de transiciones de ODEs.
	 * @throws Exception
	 * 
	 */
	protected TransicionVO[] handleObtenODEsRechazadosPorUsuario(String idUsuario) throws Exception {
		return SrvPublicacionServiceImpl.obtenerODEsPorEstadoUsuario(idUsuario, SrvPublicacionServiceImpl.RECHAZADO, this.getEstadoDao(), this.getTransicionDao());

	}

	/**
	 * Implementa las operaciones de creacion de un nuevo ODE desde la nada
	 * hasta el estado de Creacion.
	 * 
	 * @param idODE
	 *            Identificador del ODE.
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @param comentarios
	 *            Comentarios que pueden ir asociados a la creación de un nuevo
	 *            ODE.
	 * @param titulo
	 *            Titulo del ODE.
	 * @return se retorna un VO con el error que se ha producido en el caso de
	 *         detectarse algun problema.
	 * @throws Exception
	 * 
	 */
	protected ResultadoOperacionVO handleCreacion(String idODE, String idUsuario, String comentarios, String titulo)
	throws Exception {
		try {
			EstadoVO estado = this.obtenEstadoPorIdODE(idODE, LdapUserDetailsUtils.getIdioma());
			if (estado == null || estado.getClave().equals(CREACION) || estado.getClave().equals(RECHAZADO)||estado.getClave().equals(PUBLICADO_AUTONOMO) /*|| estado.equals("")*/) {
				SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
				if(logger.isDebugEnabled())
					logger.debug("Creando ODE con idODE[" + idODE + "] idUsuario[" + idUsuario + "] comentarios["+ comentarios + "] titulo[" + titulo + "] y estado: ["+ estado+ "]");
				EstadoDao estadoDao = this.getEstadoDao();
				TransicionDao transicionDao = this.getTransicionDao();
				
				if((estado!=null) && (estado.getClave().equals(PUBLICADO_AUTONOMO))){
					Transicion transicionActual = handleObtenerUltimaTransicion(idODE);
					transicionActual.setEstadoTransitado(estadoDao.obtenEstadoPorNombre(CREACION));
					transicionDao.update(transicionActual);	
				}
				if((estado == null)||estado.getNombre().equals("")){
					localizadorService.crearLocalizadorNoPublicado(idUsuario, idODE);
				}
				
				Date fecha = new Date(System.currentTimeMillis());
				transicionDao.create(DateManager.dateToCalendar(fecha), comentarios, idODE, idUsuario, titulo, idUsuario,// el usuario de creacion es el mismo que me pasan
						fecha.getTime(), // los milisegundos de la fecha
						false,null, null, estadoDao.obtenEstadoPorNombre(CREACION));
				return new ResultadoOperacionVO(SIN_ERRORES, I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES),idODE,0L );

			}
			logger.warn("ERROR: creando ODE con identificador[" + idODE + "] idUsuario[" + idUsuario
					+ "] comentarios[" + comentarios + "]. ODE en estado[CREADO/RECHAZADO] no se puede pasar a estado CREADO.");
			return new ResultadoOperacionVO(TRANSICION_ESTADO_INVALIDA,
					I18nModuloPublicacion.getPropertyValueI18n(TRANSICION_ESTADO_INVALIDA),idODE,0L);
		} catch (CreacionException e) {
			logger.error("Excepcion creando ODE con idODE[" + idODE + "] idUsuario[" + idUsuario + "] comentarios["
					+ comentarios + "] y titulo[" + titulo + "]", e);
			throw new Exception("Excepcion creando ODE con idODE[" + idODE + "] idUsuario[" + idUsuario
					+ "] comentarios[" + comentarios + "] y titulo[" + titulo + "]", e);
		} catch (Exception e) {
			CreacionException creacionException = new CreacionException("Fallo en la creación de ODE con id " + idODE
					+ " y usuario " + idUsuario, e);
			logger.error("Fallo en la creación de ODE con id " + idODE + " y usuario " + idUsuario, creacionException);
			throw creacionException;
		}
	}

	/**
	 * Implementa el paso a no disponible del ODE que le indican.
	 * 
	 * @param idODE
	 *            Identificador del ODE.
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @param comentarios
	 *            Comentarios que pueden ir asociados a la acción realizada.
	 * @param titulo
	 *            Titulo del ODE.
	 * @return se retorna un VO con el error que se ha producido en el caso de
	 *         detectarse algun problema.
	 * @throws Exception
	 * 
	 */
	protected ResultadoOperacionVO handleNoDisponible(String idODE, String idUsuario, String comentarios, String titulo)
	throws Exception {
		try {
			if(logger.isDebugEnabled())
				logger.debug("pasando a no disponible ode [" + idODE + "] con titulo [" + titulo + "] de usuario ["	+ idUsuario + "] ");
			EstadoVO estado = this.obtenEstadoPorIdODE(idODE, "");
			if (estado.getClave().equals(NO_DISPONIBLE) || estado.getClave().equals(PUBLICADO)) {
				
				//desindexamos, pq ahora no debe aparecer cuando se busca
				IndexadorVO[] resultado = this.getSrvIndexadorService().eliminarODE(new String[] { idODE });
				if(resultado[0].getCode()!= 0){
					//Ha habido errores 'desindexando', y no sigo.
					return new ResultadoOperacionVO(ERROR_DESINDEXACION, I18nModuloPublicacion.getPropertyValueI18n(ERROR_DESINDEXACION), idODE, 0L);
				}
				//		La 'desindexacion' ha sido correcta, eliminamos de la tabla de odes publicados
						this.getOdePublicadoDao().eliminaODEsPublicadosPorId(idODE);

						// si todo ha salido bien creamos la transición a no disponible
						EstadoDao estadoDao = this.getEstadoDao();
						TransicionDao transicionDao = this.getTransicionDao();

						Transicion transicionActual = handleObtenerUltimaTransicion(idODE);
						transicionActual.setEstadoTransitado(estadoDao.obtenEstadoPorNombre(NO_DISPONIBLE));
						transicionDao.update(transicionActual);
						Date fecha = new Date(System.currentTimeMillis());
						transicionDao.create(DateManager.dateToCalendar(fecha), comentarios, idODE, idUsuario, titulo, transicionActual
								.getIdUsuarioCreacion(),// arrastro el id del usuario de creacion
								fecha.getTime(),false, null, null, estadoDao.obtenEstadoPorNombre(NO_DISPONIBLE));
						logger.info("Ode [" + idODE + "] con titulo [" + titulo + "] de usuario [" + idUsuario
								+ "] pasado a no disponible");
						
						//Tenemos que eliminar los ODEs del perfil público. Si la eliminación va mal, yo sigo.
						this.getSrvPerfilPublico().eliminarODEPerfilPublico(new String[] { idODE });
						
						//Optimizamos el indice, pues al realizar un borrados o reindexado se generan archivos .del que luego hacen que las operaciones
						//con los terminos indexados sean incorrectas o no reales
						if (logger.isDebugEnabled()) logger.debug("Vamos a optimizar el indice ...");
						this.getSrvIndexadorService().optimizarIndice(null);//con null optimizamos todos los subindices
						
						//Vamos a borrar la carpeta temporal que se genera al hacer una descarga si existe
						try{
							SrvEntregarService servicioEntregar=this.getSrvEntregarService();
							String[] arrayId=new String[1];
							arrayId[0]=idODE;
							servicioEntregar.eliminarTemporales(arrayId);
							logger.debug("Hemos eliminado el fichero temporal del ode "+ idODE);
							//reset en cache
							boolean salida=getSrvCacheService().borrarHitCache();
							if(!salida) logger.debug("Fallo al borrar la cache.");
//						Todo ha ido bien, llamo al servicio de Auditoria para que se actualice el estado de esos registros en las tablas de auditoría
							this.getSrvAuditoriaServicio().despublicarODEs(new String[] { idODE });
							
						}catch(Exception ex){
							logger.error("Error al borrar el temporal al despublicar el ode "+ idODE,ex);
							return new ResultadoOperacionVO(ERROR_FICHEROS_TEMPORALES, I18nModuloPublicacion.getPropertyValueI18n(ERROR_FICHEROS_TEMPORALES), idODE, 0L);
							//throw ex;
						}
						return new ResultadoOperacionVO(SIN_ERRORES, I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES),idODE,0L);
			}
			logger.warn("ERROR: haciendo no disponible ODE con identificador[" + idODE + "] idUsuario["
					+ idUsuario + "] comentarios[" + comentarios + "]y titulo[" + titulo
					+ "]ODE en estado[NO DISPONIBLE/PUBLICADO] no se puede pasar a estado NO DISPONIBLE.");
			return new ResultadoOperacionVO(TRANSICION_ESTADO_INVALIDA,
					I18nModuloPublicacion.getPropertyValueI18n(TRANSICION_ESTADO_INVALIDA),idODE,0L);
		} catch (Exception e) {
			logger.error("Fallo haciendo no disponible ODE con identificador[" + idODE + "] idUsuario[" + idUsuario
					+ "] comentarios[" + comentarios + "]y titulo[" + titulo + "]", e);
			throw e;
		}
	}

	/**
	 * Propone para publicacion el ODE que se le indica.
	 * 
	 * @param idODE
	 *            Identificador del ODE.
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @param comentarios
	 *            Comentarios que pueden ir asociados a la acción realizada.
	 * @param titulo
	 *            Titulo del ODE.
	 * @return se retorna un VO con el error que se ha producido en el caso de
	 *         detectarse algun problema.
	 * @throws Exception
	 * 
	 */
	protected ResultadoOperacionVO handleProponerPublicacion(String idODE, String idUsuario, String comentarios,
			String titulo) throws Exception {
		return proponerPublicacion(idODE, idUsuario, comentarios, titulo, true);
	}

	/**
	 * Este metodo realiza la propuesta de publicacion del ODE validandolo o no.
	 * @param idOde identificador del ODE
	 * @param idUsuario identificador de usuario
	 * @param comentarios Comentarios que pueden ir asociados a la acción realizada.
	 * @param titulo Titulo del ODE.
	 * @param validar boolean si hay que validar el ODE
	 * @return ResultadoOperacionVO
	 * @throws Exception
	 * */
	private ResultadoOperacionVO proponerPublicacion(String idODE, String idUsuario, String comentarios, String titulo, boolean validar) throws Exception {
		try {
			EstadoVO estado = this.obtenEstadoPorIdODE(idODE, LdapUserDetailsUtils.getIdioma());
			if(logger.isDebugEnabled())
				logger.debug("Proponiendo para publicacion ODE con identificador[" + idODE + "] usuario[" + idUsuario
						+ "] comentarios[" + comentarios + "] y en estado actual[" + estado + "]");
			
			if (estado != null && (estado.getClave().equals(SrvPublicacionServiceImpl.PROPUESTO_CATALOGACION))) {
				
				// validador
				SrvValidadorService validadorService = this.getSrvValidadorService();
				SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
				LocalizadorVO localizadorNP = localizadorService.consultaLocalizador(idODE);
//				Hacemos la validacion que nos devuelve el texto de error menos detallado.
				boolean ODEvalido = false;
				ValidaVO valid = null;
		    	ValidarLicenciasVO validacionLic=null;

				if (validar)  // si hay que validar el ODE, lo valido y miro a ver si continuo
				{
					logger.debug("Validando el ODE [" + localizadorNP.getPath() + "]");
					//validamos manifest
					valid = validadorService.obtenerValidacion(localizadorNP.getPath());
					//Validamos licencias
					validacionLic = this.getSrvGruposLicencias().validarLicencias(localizadorNP.getPath());
					ODEvalido = valid.getEsValidoManifest() && validacionLic.isResultado();
										
				} else // si no hay que validar el ODE, es porque ya viene valido.
					ODEvalido = true;
				
				if (ODEvalido) {
					EstadoDao estadoDao = this.getEstadoDao();
					TransicionDao transicionDao = this.getTransicionDao();
					Transicion transicionActual = handleObtenerUltimaTransicion(idODE);
					transicionActual.setEstadoTransitado(estadoDao.obtenEstadoPorNombre(PROPUESTO));
					transicionDao.update(transicionActual);
					logger.info("Actualizamos estado de ODE con identificador[" + idODE + "] a PROPUESTO.");
					Date fecha = new Date(System.currentTimeMillis());
					transicionDao.create(DateManager.dateToCalendar(fecha), comentarios, idODE, idUsuario, titulo, transicionActual
							.getIdUsuarioCreacion(),// arrastramos el usuario de creacion
							Long.valueOf(fecha.getTime()), false,null, null, estadoDao.obtenEstadoPorNombre(PROPUESTO));
					return new ResultadoOperacionVO(SIN_ERRORES, I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES),idODE,0L);
				}
				/**
				 * Esto es para evitar posible nullPointer en las líneas de log de más abajo
				 */
				if (logger.isDebugEnabled()&&valid==null) {
					valid=new ValidaVO();
					valid.setResultadoValidacion("");
				}

				String mensaje = "";
				
				// si no es valido el manifest
				if (!valid.getEsValidoManifest() && validacionLic.isResultado()) {
					mensaje = valid.getResultadoValidacion();	

				// si no es valida la licencia
				} else if (valid.getEsValidoManifest() && !validacionLic.isResultado()) {
					mensaje = validacionLic.getLicenciaAdicional();		

				// si no es valida la licencia ni el manifest
				} else {
					mensaje = valid.getResultadoValidacion() + validacionLic.getLicenciaAdicional();		
				}	

				logger.warn("ERROR: proponiendo para publicacion ODE con identificador.[" + idODE
						+ "] ODE en estado[" + estado.getClave() + "]. El ode no valida correctamente: " + mensaje);
				return new ResultadoOperacionVO(SrvPublicacionServiceImpl.ERROR_DE_VALIDACION, mensaje,idODE,0L);
			}
			
			if (estado!=null) {
				// Exception ex = new Exception("ERROR: proponiendo para publicacion ODE con identificador.
				// ODE en estado[CREADO] no se puede pasar a estado PROPUESTO.");
				logger.warn("ERROR: proponiendo para publicacion ODE con identificador. ODE en estado["
						+ estado.getClave() + "] no se puede pasar a estado PROPUESTO.");
			} else {
				logger.warn("ERROR: proponiendo para publicacion ODE con identificador."
						+ "No se puede pasar a estado PROPUESTO.");
			}
			return new ResultadoOperacionVO(TRANSICION_ESTADO_INVALIDA,
					I18nModuloPublicacion.getPropertyValueI18n(TRANSICION_ESTADO_INVALIDA),idODE,0L);
			// throw ex;
		} catch (Exception e) {
			Exception propuesto = new Exception("Excepción en la proposicion de publicacion del ODE con identificador",	e);
			logger.error("Excepción en la proposicion de publicacion del ODE con identificador[" + idODE
					+ "] idUsuario[" + idUsuario + "] comentarios[" + comentarios + "]y titulo[" + titulo + "]",propuesto);
			throw propuesto;
		}
	}

	/**
	 * Este metodo se encarga de coger un identificador de ODE y realizar todos
	 * los pasos para publicarlo, incluyendo la generacion del MEC.
	 * Envia el correo al usuario dueño del ODE
	 * 
	 * @param idODE
	 *            Identificador del ODE.
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @param comentarios
	 *            Comentarios que pueden ir asociados a la acción realizada.
	 * @param titulo
	 *            Titulo del ODE.
	 * @return se retorna un VO con el error que se ha producido en el caso de
	 *         detectarse algun problema.
	 * @throws Exception
	 * 
	 */
	protected ResultadoOperacionVO handlePublicar(String idODE, String idUsuario, String comentarios, String titulo,
			String comunidades,  String universal, String identificadorLicencia, String textoLicencia) throws Exception {
		ResultadoOperacionVO resultado = publicar(idODE, idUsuario, comentarios, titulo,
				comunidades,  universal, true, identificadorLicencia,textoLicencia);
		boolean salida=getSrvCacheService().borrarHitCache();
		if(!salida) logger.warn("Error al borrar la cache.");
		return resultado;
	}

			
	
	
	/**
	 * Este metodo publica el ODE validandolo o no.
	 * @param idOde identificador del ODE
	 * @param idUsuario identificador de usuario
	 * @param comentarios Comentarios que pueden ir asociados a la acción realizada.
	 * @param titulo Titulo del ODE.
	 * @param comunidades Los nodos para la publicacion
	 * @param validar boolean si hay que validar el ODE
	 * @param identificadorLicencia el identificador de la Licencia 
	 * @param textoLicencia el texto de la licencia que será publicada
	 * @return ResultadoOperacionVO
	 * @throws Exception
	 * */
	private ResultadoOperacionVO publicar(String idODE, String idUsuario, String comentarios, String titulo,
			String comunidades,  String universal, boolean validar, String identificadorLicencia, String textoLicencia) throws Exception {
		try {
			
			EstadoVO estado = this.obtenEstadoPorIdODE(idODE, LdapUserDetailsUtils.getIdioma());
			if(logger.isDebugEnabled())
				logger.debug("Publicando ODE (Propuesto) con identificador [" + idODE + "] en estado[" + estado.getClave()
						+ "] con usuario[" + idUsuario + "] y comentarios[" + comentarios + "].");
			
			if (!(estado.getClave().equals(PROPUESTO))) {
				logger.warn("ERROR: Intentando publicar ODE con identificador [" + idODE + "] en estado["
						+ estado.getClave() + "] con usuario[" + idUsuario + "] y comentarios[" + comentarios
						+ "]. El estado del ODE no es PROPUESTO.");
				return new ResultadoOperacionVO(TRANSICION_ESTADO_INVALIDA,	I18nModuloPublicacion.getPropertyValueI18n(TRANSICION_ESTADO_INVALIDA),idODE,0L);
			}
			
			SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
			LocalizadorVO localizadorP = localizadorService.consultaLocalizador(idODE);
//				Hacemos la validacion que nos devuelve el texto de error menos detallado.
			boolean ODEvalido = false;
			ValidaVO valid = null;
	    	ValidarLicenciasVO validacionLic=null;   	
	    	
			if (validar) // si hay que validar el ODE, lo valido y miro a ver si continuo
			{
				logger.debug("Validando el ODE <" + localizadorP.getPath() + "> desde el servicio de publicacion");
				//validamos manifest
				valid = this.getSrvValidadorService().obtenerValidacion(localizadorP.getPath());
				//Validamos la licencia que le asigna el publicador 
				validacionLic = this.getSrvGruposLicencias().predecirCompatibilidad(localizadorP.getPath(), identificadorLicencia);
				ODEvalido = valid.getEsValidoManifest() && validacionLic.isResultado();
				
			} else {
				// si no hay que validar el ODE, es porque ya viene valido.
				ODEvalido = true;
			}
			
			if (ODEvalido) {
	
				File extraeSubmanifest = new File(localizadorP.getPath(), MANIFEST_NAME);
				Manifest imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);
				ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
				String identifiadorManifest = manAgrega.getManifest().getIdentifier();
				Lom lom = manAgrega.obtenerLom(identifiadorManifest, null);

				LomAgrega lomAgrega = new LomAgrega(lom);		
				logger.info("El ode: [" + localizadorP.getPath() + "] es válido y se va a publicar");

				// 20130827 Evolutivo modificación ODEs con versión
				// Si el ODE a publicar tiene una relación esVersionDe con el texto igual al que se pone cuando se inserta automáticamente 
				// al proponer como una nueva versión de un ODE suyo previamente publicado.
				// En ese caso, se despublica y elimina la versión anterior del ODE y se publica la nueva versión con el mismo idMEC
				String catalogoMec=ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.CATALOGO_MEC);
				List<RelacionAgrega> relacionVersionDe = lomAgrega.getRelacionesEsVersionDeMEC(catalogoMec);

				if (lomAgrega.esOdeVersionado(catalogoMec))
				{
					if (logger.isDebugEnabled())
						logger.debug("Se va a publicar la nueva versión del ODE. Antes se despublica y elimina la versión anterior");

					String idODEVersion = relacionVersionDe.get(0).getEntradaId();
					ResultadoOperacionVO resultado = new ResultadoOperacionVO();
					
					EstadoVO estadoODEOriginal = handleObtenEstadoPorIdODE(idODEVersion, LdapUserDetailsUtils.getIdioma());
					
					// Si el ODE está publicado lo pasamos a no disponible y lo elimanos.
					if (estadoODEOriginal!=null && estadoODEOriginal.getClave()!=null && estadoODEOriginal.getClave().equals(PUBLICADO)) {									
						resultado=handleNoDisponible(idODEVersion, idUsuario, "Se despublica por nueva versión", titulo);
						if(!resultado.getIdResultado().equals(SIN_ERRORES)) {
							logger.error("Error al despublicar el ODE con id "+idODEVersion+" para poder publicar una nueva version del mismo. Accion que ha intentado ejecutar el usuario "+idUsuario+": "+resultado.getDescripcion());
							return resultado;
						}
						resultado=handleEliminar(idODEVersion, idUsuario);
						if(!resultado.getIdResultado().equals(SIN_ERRORES)) {
							logger.error("Error al eliminar el ODE con id "+idODEVersion+" para poder publicar una nueva version del mismo. Accion que ha intentado ejecutar el usuario "+idUsuario+": "+resultado.getDescripcion());
							return resultado;
						}
					}
					return publicar_aux(idODE, generaMECparaOdesVersionados(localizadorP.getPath(),idODEVersion), idUsuario, "Publicado nueva versión",
						comunidades,  universal, false, identificadorLicencia, textoLicencia);
				}
				return publicar_aux(idODE, this.handleGeneraMEC(localizadorP.getPath()), idUsuario, comentarios,
						comunidades,  universal, false, identificadorLicencia, textoLicencia);
			}
			
			String mensaje = "";
			
			// si no es valido el manifest
			if (!valid.getEsValidoManifest() && validacionLic.isResultado()) {
				mensaje = valid.getResultadoValidacion();		

			// si no es valida la licencia
			} else if (valid.getEsValidoManifest() && !validacionLic.isResultado()) {
				mensaje = validacionLic.getLicenciaAdicional();	

			// si no es valida la licencia ni el manifest
			} else {
				mensaje = valid.getResultadoValidacion() + validacionLic.getLicenciaAdicional();	
			}			

			logger.warn("Imposible validar el ODE[" + localizadorP.getPath() + "] con error[" + mensaje + "]. Imposible publicar");
			return new ResultadoOperacionVO(SrvPublicacionServiceImpl.ERROR_DE_VALIDACION, mensaje ,idODE,Long.valueOf(0));
			
		} catch (Exception e) {
			PublicarException exception = new PublicarException("Fallo en la publicación de ODE con id " + idODE + " y usuario " + idUsuario, e);
			logger.error("Fallo en la publicación de ODE con id " + idODE + " y usuario " + idUsuario, exception);
			throw exception;
		}
	}
	
	
	/**
	 * Implementa el rechazo del ODE que le indican.
	 * 
	 * @param idODE
	 *            Identificador del ODE.
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @param comentarios
	 *            Comentarios que pueden ir asociados a la acción realizada.
	 * @param titulo
	 *            Titulo del ODE.
	 * @return se retorna un VO con el error que se ha producido en el caso de
	 *         detectarse algun problema.
	 * @throws Exception
	 * 
	 */
	protected ResultadoOperacionVO handleRechazar(String idODE, String idUsuario, String comentarios, String titulo)
	throws Exception {
		try {
			if(logger.isDebugEnabled())
				logger.debug("Rechazando ODE con identificador [" + idODE + "] con usuario[" + idUsuario
						+ "] y comentarios[" + comentarios + "].");
			EstadoVO estado = this.obtenEstadoPorIdODE(idODE, LdapUserDetailsUtils.getIdioma());
			if (estado.getClave().equals(PROPUESTO)	|| estado.getClave().equals(SrvPublicacionServiceImpl.PROPUESTO_CATALOGACION)|| estado.getClave().equals(SrvPublicacionServiceImpl.PUBLICADO_AUTONOMO)) {
				EstadoDao estadoDao = this.getEstadoDao();
				TransicionDao transicionDao = this.getTransicionDao();
				Transicion transicionActual = handleObtenerUltimaTransicion(idODE);
				transicionActual.setEstadoTransitado(estadoDao.obtenEstadoPorNombre(RECHAZADO));
				transicionDao.update(transicionActual);
				Date fecha = new Date(System.currentTimeMillis());
				transicionDao.create(DateManager.dateToCalendar(fecha), comentarios, idODE, idUsuario, titulo, transicionActual
						.getIdUsuarioCreacion(),// arrastramos el identificador del usuario creador.
						Long.valueOf(fecha.getTime()),false, null, null, estadoDao.obtenEstadoPorNombre(RECHAZADO));
				logger.info("Rechazado ODE con identificador [" + idODE + "] en estado[" + estado + "] con usuario["
						+ idUsuario + "] y comentarios[" + comentarios + "].");
				//Envio el correo de rechazo, desde el gestor de flujo
				return new ResultadoOperacionVO(SIN_ERRORES, I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES),idODE,0L);
			}
			logger.warn("ERROR: rechazando ODE con identificador[" + idODE + "] idUsuario[" + idUsuario
					+ "] comentarios[" + comentarios + "]. ODE en estado[PROPUESTO] no se puede pasar a estado RECHAZADO.");
			return new ResultadoOperacionVO(TRANSICION_ESTADO_INVALIDA,
					I18nModuloPublicacion.getPropertyValueI18n(TRANSICION_ESTADO_INVALIDA),idODE,0L);
		} catch (Exception e) {
			logger.error("ERROR: rechazando ODE con identificador[" + idODE + "] idUsuario[" + idUsuario
					+ "] comentarios[" + comentarios + "].", e);
			throw e;
		}
	}

	/**
	 * Modifica los valores de titulo y comentarios de un ode en estado creado
	 * que le pasan. Si los valores de titulo o comentarios suministrados son
	 * alguno o los dos nulos, no se modificara el campo afectado. Solo se
	 * modificaran los campos con contenido.
	 * 
	 * @param idODE
	 *            Identificador del ODE.
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @param titulo
	 *            Titulo del ODE.
	 * @param comentarios
	 *            Comentarios que pueden ir asociados a la acción realizada.
	 * @return ResultadoOperacionVO con el resultado de la modificación.
	 * @throws Exception
	 * 
	 */

	protected ResultadoOperacionVO handleModificaODECreado(String idODE, String idUsuario, String titulo, String comentarios)
	throws Exception {
		logger.debug("Modificando ode["+idODE+"], con usuario["+idUsuario+"] con nuevo titulo["+titulo+"] y comentarios["+comentarios+"]");
		/*
		 * Tenemos que comprobar el espacio que ocupa en disco el ODE que se modifica.
		 * Tenemos que ver lo que ocupa lo que tiene en disco el tio ahora.
		 * Tenemos que ver si le cabe el ODE nuevo
		 * */
//		Vamos a cambiar el titulo del ODE en la ultima transición del ODE. Aqui solo se cambia el titulo en la transicion, no en el ODE.
//		Se supone que el que ha llamado a este metodo ya ha realizado ese cambio.
		Transicion transicionActual = handleObtenerUltimaTransicion(idODE);
		if (transicionActual.getIdODE() != null) {  // no estamos impidiendo que se realice la modificacion en nungun estado.
			SrvLocalizadorService localizador = this.getSrvLocalizadorService();
			try {
//				Primero vamos a comprobar que la modificacion del ODE no es ilegal VS la cuota de disco asignada al usuario.
				TransicionVO[] transicionCreados = this.handleObtenODEsCreadosPorUsuario(idUsuario);
				TransicionVO[] transicionAutonomos = this.handleObtenODEsPublicadosAutonomoPorUsuario(idUsuario);
//				Calculamos el estado en el que se encuentra el ODE
				EstadoVO estadoActual = this.obtenEstadoPorIdODE(idODE, LdapUserDetailsUtils.getIdioma());
				ArrayList<TransicionVO> transicionesConPeso = new ArrayList<TransicionVO>();
				transicionesConPeso.addAll(Arrays.asList(transicionCreados==null?new TransicionVO[0]:transicionCreados));
				transicionesConPeso.addAll(Arrays.asList(transicionAutonomos==null?new TransicionVO[0]:transicionAutonomos));
				if (TratamientoCuota.excedeCuotaUsuario(localizador, transicionActual, transicionesConPeso.toArray(new TransicionVO[0]), estadoActual))
				{
					logger.warn("Error modificando ODE["+idODE+"] del usuario["+transicionActual.getIdUsuarioCreacion()+"]. El cambio excede la cuota de usuario.");
					return new ResultadoOperacionVO(ERROR_EXCEDER,I18nModuloPublicacion.getPropertyValueI18n(ERROR_EXCEDER) ,idODE,this.getSrvLocalizadorService().calculaEspacioLocalizador(idODE));
				}
//				Si no, realizamos la actualizacion
				if (titulo != null)
					transicionActual.setTitulo(titulo);

				if (comentarios != null)
					transicionActual.setComentarios(comentarios);
				this.getTransicionDao().update(transicionActual);
				//Actualizamos el titulo en la tabla de contenido inapropiado
				ContenidoInapropiadoVO[] contsIn = this.getSrvContenidoInapropiadoService().obtenerContenidosInapropiados();
				if(contsIn != null && contsIn.length > 0){
					for(int i = 0; i < contsIn.length; i++){
						if(contsIn[i] != null && contsIn[i].getIdOde().equals(idODE)){
							boolean mod = false;
							mod = this.getSrvContenidoInapropiadoService().modificarTituloContenidoInapropiado(contsIn[i].getIdioma_cat(), contsIn[i].isEstado_ci(), contsIn[i].getEstado(), contsIn[i].getIdOde(), titulo);
							if(mod){
								logger.info("Se ha modificado el titulo en contenido inapropiado");
							}
						}
					}
				}
				
//				Actualizamos el tamaño del localizador de este ODE en la tabla de localizadores.
				this.getSrvLocalizadorService().actualizaEspacioLocalizador(idODE);
				return new ResultadoOperacionVO(SIN_ERRORES, I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES),idODE,this.getSrvLocalizadorService().consultaEspacioLocalizador(idODE));
			} catch (Exception ex) {
				String mensaje = "Error modificando ODE. Excepcion inesperada modificando ODE["+idODE+"] con usuario["+idUsuario+"] titulo["+titulo+"] y comentarios["+comentarios+"].";
				logger.error(mensaje,ex);
				throw new Exception(mensaje, ex);
			}
		}
		throw (new Exception("Error modificando ODE. Error buscando ultima transicion del ODE con id[" + idODE+ "]. No se ha encontrado transicion."));
	}

	/**
	 * Realiza las operaciones de creacion de un ODE en estado CREADO. Valida la
	 * informacion del ODE que se pasa en formato PIF(ZIP) y lo alberga en la
	 * plataforma bajo el usuario con el que se crea. Se descomprime en un
	 * temporal para validarlo, y si valida se crea un localizador y se copia en
	 * él.
	 * 
	 * @param ficheroPIF
	 *            Fichero en formato PIF.
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @param comentarios
	 *            Comentarios que pueden ir asociados a la acción realizada.
	 * @param titulo
	 *            Titulo del ODE.
	 * @return se retorna un VO con el error que se ha producido en el caso de
	 *         detectarse algun problema.
	 * @throws Exception
	 * 
	 */
	protected ResultadoOperacionVO handleCrearPIF(DataHandler ficheroPIF, String idUsuario, String comentarios,
			String titulo, String idioma) throws Exception {
		//Invocamos al metodo auxiliar indicandole que no queremos tener en cuenta la cuota de usuario
		return crearPIFAux(ficheroPIF, idUsuario, comentarios, titulo, idioma, false, null,null);
	}

	/**
	 * Elimina el ode que se pasa por parametro del usuario. Los odes
	 * eliminables por el usuario son los que estan en estado:creacion, no
	 * disponible, rechazado, propuesto, propuesto catalogacion.
	 * 
	 * @param idODE
	 *            Identificador del ODE.
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @return se retorna un VO con el error que se ha producido en el caso de
	 *         detectarse algun problema.
	 * @throws Exception
	 * 
	 */
	protected ResultadoOperacionVO handleEliminar(String idODE, String idUsuario) throws Exception {
		if(logger.isDebugEnabled())
			logger.debug("Intentando eliminar el ode con id: " + idODE + " del usuario: " + idUsuario);
		// recorremos todos los ids y borramos los que se puedan
		IdODECriteria criterio = new IdODECriteria();
		// primero lo buscamos con un criteria
		criterio.setIdODE(idODE);
		criterio.setEstadoTransitado(null);
		// esto nos devuelve el estado final de ese ode, si existe, si nos han colado un id feo, salta
		try {
			TransicionDao transicionDao = this.getTransicionDao();
			EstadoVO estadoActual=this.obtenEstadoPorIdODE(idODE, "");
			//Para eliminar los odes que se encuentren en estos estados
			//Se ha añadido el estado PROPUESTO_CATALOGACION para poder borrar los odes que se han rechazados por un catalogador cuando el usuario
			// que creo ese ode ya ha sido eliminado

			if (estadoActual.getClave().equals(SrvPublicacionServiceImpl.CREACION)
					|| estadoActual.getClave().equals(SrvPublicacionServiceImpl.PROPUESTO)
					|| estadoActual.getClave().equals(SrvPublicacionServiceImpl.PROPUESTO_CATALOGACION)
					|| estadoActual.getClave().equals(SrvPublicacionServiceImpl.RECHAZADO)
					|| estadoActual.getClave().equals(SrvPublicacionServiceImpl.NO_DISPONIBLE)
					|| estadoActual.getClave().equals(SrvPublicacionServiceImpl.PUBLICADO_VERSIONANDOSE))

			{
				//Desasociamos el ODE del posible albun al que este asociado. Si no esta en ningún album, no se hace nada.
				try{
					this.getSrvAlbumService().desasociarODEAlbum(idODE);		
				}catch(Exception ex){
					logger.error("ERROR: desasociando el ode[" + idODE+ "] del album al que esta asociado.",ex);
				}
//				LocalizadorVO localizador = new LocalizadorVO();
				LocalizadorVO localizador = this.getSrvLocalizadorService().consultaLocalizador(idODE);

				// Si el localizador existe borramos los datos de disco y eliminamos el localizador
				this.getSrvLocalizadorService().eliminarLocalizador(localizador.getIdentificador());
				if(logger.isDebugEnabled())	logger.debug("Eliminado ode: <" + localizador.getIdentificador() + "> usuario <" + localizador.getIdUsuario()+">");
				if(logger.isDebugEnabled())	logger.debug("Eliminamos ODE con identificador <" + idODE + "> usuario <" + idUsuario + ">");
				if (!(estadoActual.getClave().equals(SrvPublicacionServiceImpl.NO_DISPONIBLE))) {
//					Eliminamos todas las transiciones que hay en la BBDD de publicacion para este ODE
					logger.info("Eliminamos las transiciones asociadas al idODE[" + idODE + "]");
					this.getTransicionDao().remove(this.getTransicionDao().buscarHistorialPorIdODE(idODE));
				}else{
					// si es eliminar un no disponible (que ha estado publicado por tanto) tiene que quedar constancia de su 
					// existencia, no se borran las transiciones pasadas y se crea una nueva a eliminado

					EstadoDao estadoDao = this.getEstadoDao();
					Transicion transicionActual = handleObtenerUltimaTransicion(idODE);
					transicionActual.setEstadoTransitado(estadoDao.obtenEstadoPorNombre(SrvPublicacionServiceImpl.ELIMINADO));
					transicionDao.update(transicionActual);
					Date fecha = new Date(System.currentTimeMillis());
					transicionDao.create(DateManager.dateToCalendar(fecha), "Ode despublicado eliminado", idODE, idUsuario, transicionActual
							.getTitulo(), transicionActual.getIdUsuarioCreacion(),// arrastramos el usuario de creacion del ODE.
							fecha.getTime(),false, null, null, estadoDao.obtenEstadoPorNombre(SrvPublicacionServiceImpl.ELIMINADO));
				} 
//				Ahora hay que eliminar los comentarios (Antes lo haciamos en la despublicación)
				this.getSrvValoracionService().eliminarTodosComentarios(idODE);
//				Ahora hay que eliminar las valoraciones (Antes lo haciamos en la despublicación)
				this.getSrvValoracionService().eliminarTodasValoraciones(idODE);
//				Ahora hay que eliminar el tagging. 
				this.getSrvTaggingServer().eliminarTags(new String[] {idODE});
				return new ResultadoOperacionVO(SIN_ERRORES, I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES),idODE,0L);
			}
			logger.warn("Error Eliminado ode con idODE[" + idODE + "] idUsuario[" + idUsuario+ "] por estado invalido[" + estadoActual.getClave() + "]");
			return new ResultadoOperacionVO(TRANSICION_ESTADO_INVALIDA, I18nModuloPublicacion.getPropertyValueI18n(TRANSICION_ESTADO_INVALIDA),idODE,0L);
		} catch (Exception ex) {
			logger.error("El ode [" + idODE + "] del usuario[" + idUsuario + "] no se ha podido eliminar (probablemente no exista)");
			throw new Exception("El ode [" + idODE + "] del usuario[" + idUsuario + "] no se ha podido eliminar (probablemente no exista)", ex);
		}
	}

	/**
	 * Este metodo elimina los ODEs en estado No Disponible que cumplen las
	 * condiciones de los parametros que se le pasan: rango de fecha en el que
	 * paso a no disponible, usuario al que pertenece el ODE, etc. Si la lista
	 * de usuarios es vacia, se tendran en cuenta los ODEs no disponibles de
	 * todo el repositorio. Si la fecha de fin es vacia, se tendra en cuenta la
	 * fecha actual como limite temporal superior. Si la fecha de inicio es
	 * vacia, no se tendra en cuenta limite temporal inferior.
	 * 
	 * @param parametro
	 *            Parametros con las condiciones a cumplir por los ODEs a
	 *            eliminar.
	 * @return EliminarResultadoVO[]Devuelve un array de resultados de eliminar
	 *         los ODEs que cumplen las condiciones de eliminacion.
	 * @throws Exception
	 * 
	 */
	protected EliminarResultadoVO[] handleEliminarNoDisponibles(EliminarNoDisponiblesVO parametro) throws Exception {
		// Higiene en los parametros.
		if (parametro == null) {
			logger.warn("Error invocando eliminacion de odes no disponibles con parametro nulo.");
			throw new Exception("Error invocando eliminacion de odes no disponibles con parametro nulo.");
		}
		// Comprobamos las fechas
		Date fechaInicio = (parametro.getFechaInicio()!= null ?parametro.getFechaInicio().getTime():null);
		Date fechaFin = (parametro.getFechaFin()!= null?parametro.getFechaFin().getTime():null);
		if (fechaInicio == null)
			fechaInicio = new Date(0); // si la fecha de inicio es vacia, cogemos 1970 como inicio.
		if (fechaFin == null)
			fechaFin = new Date(); // si la fecha de fin es vacia, utilzamos hoy como tope.
		if (fechaInicio.after(fechaFin)) // hay una inconsistencia con las fechas, no seguimos
		{
			logger.warn("Error invocando la eliminacion de odes. Fechas desde[" + fechaInicio.toString()
					+ "] y hasta[" + fechaFin.toString() + "] inconsistentes.");
			throw new Exception("Error invocando la eliminacion de odes. Fechas desde[" + fechaInicio.toString()
					+ "] y hasta[" + fechaFin.toString() + "] inconsistentes.");
		}

		// Primero hay que recopilar todos los ODEs en estado no disponible por usuario o cogerlos todos.
		ArrayList<TransicionVO> odesNoDisponibles = new ArrayList<TransicionVO>();
		if (parametro.getIdUsuarios() == null || parametro.getIdUsuarios().length == 0) {
			// cogemos todos los ODEs no publicados independientemente del usuario
			try {
				EstadoDesdeHastaCriteria criterio = new EstadoDesdeHastaCriteria();
				criterio.setFechaDesde(DateManager.dateToCalendar(fechaInicio));
				criterio.setFechaHasta(DateManager.dateToCalendar(fechaFin));
				criterio.setEstadoActual(getEstadoDao().obtenEstadoPorNombre(NO_DISPONIBLE));
				criterio.setEstadoTransitado(null);
				if(logger.isDebugEnabled()) logger.debug("Buscamos ODEs no disponibles desde[" + criterio.getFechaDesde().toString() + "] hasta[" + criterio.getFechaHasta().toString() + "]");
				List<TransicionVO> odesNoDisponiblesArray = getTransicionDao().buscarODEsPorCriterioEstadoDesdeHasta(criterio);
				if (odesNoDisponiblesArray != null && odesNoDisponiblesArray.size() > 0)
					odesNoDisponibles.addAll(odesNoDisponiblesArray);
			} catch (Exception e) {
				logger.error("Error onteniendo la lista de ODEs No Disponibles para toda la plataforma desde["
						+ fechaInicio.toString() + "] hasta[" + fechaFin.toString() + "][" + e.getCause() + "]");
				throw new Exception("Error onteniendo la lista de ODEs No Disponibles para toda la plataforma desde["
						+ fechaInicio.toString() + "] hasta[" + fechaFin.toString() + "]", e);
			}
		} else {
			Estado estadoActual = getEstadoDao().obtenEstadoPorNombre(NO_DISPONIBLE);
			UsuarioEstadoDesdeHastaCriteria criterio = new UsuarioEstadoDesdeHastaCriteria();
			criterio.setEstadoActual(estadoActual);
			criterio.setEstadoTransitado(null);
			criterio.setFechaDesde(DateManager.dateToCalendar(fechaInicio));
			criterio.setFechaHasta(DateManager.dateToCalendar(fechaFin));
			for (int i = 0; i < parametro.getIdUsuarios().length; i++) {
				try {
					criterio.setIdUsuarioCreacion(parametro.getIdUsuarios()[i]);
					if(logger.isDebugEnabled())
						logger.debug("Buscamos ODEs no disponibles desde[" + criterio.getFechaDesde().toString()
								+ "] hasta[" + criterio.getFechaHasta().toString() + "] para usuario["+ criterio.getIdUsuarioCreacion() + "].");
					List<TransicionVO> odesNoDisponiblesArray = getTransicionDao().buscarODEsPorCriterioUsuarioEstadoDesdeHasta(
							criterio);
					if (odesNoDisponiblesArray != null && odesNoDisponiblesArray.size() > 0)
						odesNoDisponibles.addAll(odesNoDisponiblesArray);
					else{
						if(logger.isDebugEnabled())
							logger.debug("No hay ODEs en estado no disponible para el usuario["
									+ criterio.getIdUsuarioCreacion() + "] desde[" + criterio.getFechaDesde().toString()+ "] hasta[" + criterio.getFechaHasta().toString() + "]");
					}
				} catch (Exception e) {
					logger.error("Error obteniendo ODEs No Disponibles para usuario[" + parametro.getIdUsuarios()[i]
					                                                                                              + "]. Continuamos con el resto[" + e.getCause() + "]",e);
				}
			}
		}
		if (odesNoDisponibles.size() == 0) {// no hay nada que hacer, no hay ODEs no disponibles para los usuarios seleccionados.
			logger.warn("No hay ODEs en estado no disponible entre las fechas [" + fechaInicio.toString() + "]->["
					+ fechaFin.toString() + "] para los usuarios dados[" + (parametro.getIdUsuarios() != null ? parametro.getIdUsuarios().length : 0) + "]");
			return new EliminarResultadoVO[0];
		}
		Transicion[] odesEliminablesArray = odesNoDisponibles.toArray(new Transicion[0]);
		EliminarResultadoVO[] retorno = new EliminarResultadoVO[odesEliminablesArray.length];
		for (int i = 0; i < odesEliminablesArray.length; i++) {
			try {
				retorno[i] = new EliminarResultadoVO();
				retorno[i].setIdODE(odesEliminablesArray[i].getIdODE());
				retorno[i].setTitulo(odesEliminablesArray[i].getTitulo());
				ResultadoOperacionVO resultado = handleEliminar(odesEliminablesArray[i].getIdODE(),
						odesEliminablesArray[i].getIdUsuario());
				if (resultado.getIdResultado().equals(SIN_ERRORES)) {
					retorno[i].setCode(SIN_ERRORES);
					retorno[i].setMensaje(I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES));
				} else {
					retorno[i].setCode(TRANSICION_ESTADO_INVALIDA);
					retorno[i].setMensaje(I18nModuloPublicacion.getPropertyValueI18n(TRANSICION_ESTADO_INVALIDA));
				}

			} catch (Exception e) {
				logger.error("Excepcion eliminando el ODE[" + odesEliminablesArray[i].getIdODE() + "].[" + e.getCause()
						+ "]",e);
				retorno[i].setCode(EXCEPCION_INESPERADA);
				retorno[i].setMensaje(I18nModuloPublicacion.getPropertyValueI18n(SrvPublicacionServiceImpl.EXCEPCION_INESPERADA) + "[" + e.getCause() + "].");
			}
		}
		return retorno;
	}

	/**
	 * Errores detectables en la reindexacion de un ode via el metodo
	 * ReindexarODEPublicado 10.7 El ode no estaba publicado
	 * 
	 */
	/**
	 * Reindexa en el indice de busqueda el ODE que se le indica con la
	 * valoracion que le pasan.
	 * 
	 * @param idODE
	 *            Identificador del ODE.
	 * @param valoracion
	 *            Valoracion asignada al ODE
	 * @return se retorna un VO con el resultado de la operacion.
	 * @throws Exception
	 * 
	 */
	protected ReindexarODEResultadoVO handleReindexarODEPublicado(String idODE, Float valoracion) throws Exception {

		// Comprobar que el identificador del ODE que me pasan coincide con un
		// ODE que esta en estado PUBLICADO
		IdODECriteria criterio = new IdODECriteria();
		criterio.setIdODE(idODE);
		criterio.setEstadoTransitado(null);
		SrvNodoService nodo = this.getSrvNodoService();
		SrvEstructurasEducativasService estructuras = this.getSrvEstructurasEducativasService();
		SrvTaxonomiaService taxonomia = this.getSrvTaxonomiaService();

		List<Transicion> estadoActual = this.getTransicionDao().buscarEstadoPorCriterioIdODE(criterio);
		EstadoVO ultimoEstado=this.obtenEstadoPorIdODE(idODE, LdapUserDetailsUtils.getIdioma());
		if (estadoActual != null && estadoActual.get(0) != null
				&& ultimoEstado.getClave().equals(SrvPublicacionServiceImpl.PUBLICADO)) {
			if(logger.isDebugEnabled())
				logger.debug("El ode con identificador [" + idODE + "] se encuentra publicado");

			// Obtener la localizacion del fichero imsmanifest del ODE y parsearlo.
			String rutaManifest = this.getSrvLocalizadorService().consultaLocalizador(idODE).getPath();
			Manifest imsmanifest;
			try {
				File extraeSubmanifest = new File(rutaManifest, MANIFEST_NAME);
				imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);
			} catch (Exception e) {
				logger.error("Error realizando el parseo del ODE[" + idODE + "]. Imposible reindexar el ODE.["
						+ e.getCause() + "]");
				return new ReindexarODEResultadoVO(rutaManifest, ERROR_PARSEO_MANIFEST, 11);
			}

			// 11042013 Obtenemos la ruta original de la imagen del ODE en el índice para volver a dejar la misma
			// pasándosela a la clase TratamientoODE. Si no se pasa, trabaja con la vistaPreviaAgrega 
			String sRutaImagenIndice="";
			
			// Obtenemos los idiomas de la plataforma para buscar en los diferentes índices
			I18n i18n= I18n.getInstance();
			String[] idiomasPlataforma = i18n.obtenerIdiomasBuscables();
			boolean encontrado=false;
			
			String idiomaBuscar="";
			for (int i = 0; i < idiomasPlataforma.length; i++) {				
				
				DocVO30 odeBusqueda = getSrvBuscadorService().busquedaMEC(idODE, idiomasPlataforma[i]);				
				if (odeBusqueda!=null)
				{
					sRutaImagenIndice=odeBusqueda.getImagen();
					
					break;
				}
			}
			
			// Mapear la informacion de indexacion en el VO que pasamos al indexador, utilizando la valoracion
			// que nos pasan para indexarla.
			// rellenamos y añadimos a la lista todos los idodevos			
			es.pode.indexador.negocio.servicios.indexado.IdODEVO ODEaIndexar;
			try {
				ManifestAgrega manifAgrega = new ManifestAgrega(imsmanifest);
				ODEaIndexar = TratamientoODE.rellenaIdOdeVO(
						manifAgrega,
						rutaManifest,
						idODE,
						valoracion.toString(),
						new Float(tamanioDir(rutaManifest).floatValue()),
						nodo,
						estructuras,
						taxonomia,
						this,
						getSrvBuscadorService(),
						sRutaImagenIndice,
						this.getSrvPropiedadService()
						);
			} catch (Exception e) {
				logger.error("Error realizando el mapeo de indexacion del ODE[" + idODE
						+ "]. Imposible mapear el ODE.[" + e.getCause() + "]",e);
				return new ReindexarODEResultadoVO(rutaManifest, ERROR_MAPEO_INDEXADO, 12);
			}
			
			// Llamar al indexador reindexando el ODE concreto.
			es.pode.indexador.negocio.servicios.indexado.IdODEVO[] arrayIDODES = new IdODEVO[] { ODEaIndexar };
			IndexadorVO[] retornoIndexador = this.getSrvIndexadorService().reindexarODE(arrayIDODES);
			
			//Optimizamos el indice, pues al realizar un borrados o reindexado se generan archivos .del que luego hacen que las operaciones
			//con los terminos indexados sean incorrectas o no reales
			if (logger.isDebugEnabled()) logger.debug("Vamos a optimizar el indice ...");
			this.getSrvIndexadorService().optimizarIndice(null);//con null optimizamos todos los subindices
			
			ReindexarODEResultadoVO retornoPublicador = new ReindexarODEResultadoVO();
			if (retornoIndexador != null && retornoIndexador.length > 1)
				this.getBeanMapper().map(retornoIndexador, retornoPublicador);
			return retornoPublicador;

		}
		if(logger.isDebugEnabled())
			logger.debug("El ode con identificador [" + idODE + "] no estaba publicado");
		return new ReindexarODEResultadoVO("", TRANSICION_ESTADO_INVALIDA, 7);

	}

	/**
	 * Este metodo reindexa la lista de ODEs publicados que le pasan (lista de
	 * identificadores). Los elimina del indice y los vuelve a reindexar cada
	 * uno en su idioma.
	 * 
	 * @param idODEs
	 *            Lista de identificadores de ODEs.
	 * @return Se devuelve un VO con codigo de exito/fracaso para cada ODE.
	 * @throws Exception
	 */
	protected ReindexarODEResultadoVO[] handleReindexarODEsPublicados(String[] idODEs) throws Exception {

		if (idODEs == null || idODEs.length == 0) {
			// Si la lista de ODEs es null, no hacemos nada.
			logger.warn("Error reindexando ODEs publicados. La lista de ODEs es vacia");
			throw new Exception("Error reindexando ODEs publicados. La lista de ODEs es vacia");
		}
		IdODEsCriteria criterio = new IdODEsCriteria();
		criterio.setIdODE(idODEs);
		List<OdePublicado> resultados = this.getOdePublicadoDao().obtenODEsPublicadosDeConjunto(criterio);
		OdePublicado[] odesPublicados = resultados.toArray(new OdePublicado[0]);
		SrvNodoService nodo = this.getSrvNodoService();
		SrvEstructurasEducativasService estructuras = this.getSrvEstructurasEducativasService();
		SrvTaxonomiaService taxonomia = this.getSrvTaxonomiaService();

		logger.info("Reindexamos [" + odesPublicados.length + "] ODEs publicados de los [" + idODEs.length
				+ "] identificadores de ODEs que nos han pasado.");

		// Preparamos los objetos de salida, ya que pueden ocurrir errores con
		// los ODEs recuperados y habra que marcarlos como erroneos
		ArrayList<ReindexarODEResultadoVO> retornoPublicador = new ArrayList<ReindexarODEResultadoVO>();

		ArrayList<IdODEVO> listaAIndexar = new ArrayList<IdODEVO>();
		// Llegado este punto, ya tengo la lista de los ODEs que voy a utilizar para reindexarlos.
		for (int i = 0; odesPublicados != null && i < odesPublicados.length; i++) {

			// Parseamos el ODE extrallendo la informacion a indexar
			if(logger.isDebugEnabled())
				logger.debug("Localizamos el ODE[" + odesPublicados[i].getIdODE() + "] y lo parseamos.");
			// Obtener la localizacion del fichero imsmanifest del ODE y parsearlo.
			String rutaManifest = this.getSrvLocalizadorService().consultaLocalizador(odesPublicados[i].getIdODE()).getPath();
			// Extraemos la informacion de valoracion para este ODE.
			String valoracion = "";
			try {
				valoracion = (this.getSrvValoracionService().consultarValoracion(odesPublicados[i].getIdODE())).toString();
			} catch (Exception e) {
				logger.error("Error obteniendo la valoracion del ODE[" + odesPublicados[i].getIdODE()
						+ "]. Descartamos este ODE para reindexarlo y continuamos con el resto.[" + e.getCause() + "]",e);
				retornoPublicador.add(new ReindexarODEResultadoVO(rutaManifest, CONSULTA_VALORACION_ERRONEA, 10));
				continue;
			}

			Manifest imsmanifest;
			try {  // Parseamos
				File extraeSubmanifest = new File(rutaManifest, MANIFEST_NAME);
				imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);
			} catch (Exception e) {
				logger.error("Error realizando el parseo del ODE[" + odesPublicados[i].getIdODE()
						+ "]. Descartamos este ODE para reindexarlo y continuamos con el resto.[" + e.getCause() + "]",e);
				retornoPublicador.add(new ReindexarODEResultadoVO(rutaManifest, ERROR_PARSEO_MANIFEST, 11));
				continue;
			}

			try {
				// Mapear la informacion de indexacion en el VO que pasamos al indexador, utilizando la valoracion
				// que nos pasan para indexarla.
				// rellenamos y añadimos a la lista todos los idodevos
				if(logger.isDebugEnabled())
					logger.debug("Mapeamos la informacion del ODE[" + odesPublicados[i].getIdODE()
							+ "] al VO de indexacion.");
				ManifestAgrega manifAgrega = new ManifestAgrega(imsmanifest);
				// 11042013 
				// Si en algun momento al realizar esta operacion se pierde la imagen del ode, se debe obtener la imagen actual del indice
				// y pasarsela como último parametro al TratamientoODE.rellenarIdOdeVO para que la mantenga
				// En el método reindexarODE hay un ejemplo que funciona correctamente.
				es.pode.indexador.negocio.servicios.indexado.IdODEVO ODEaIndexar = TratamientoODE.rellenaIdOdeVO(
						manifAgrega,
						rutaManifest,
						odesPublicados[i].getIdODE(),
						valoracion,
						new Float(tamanioDir(rutaManifest).floatValue()),
						nodo,
						estructuras,
						taxonomia,
						this, 
						this.getSrvPropiedadService()); // Estamos mapeando la valoracion que tiene el ODE.
				listaAIndexar.add(ODEaIndexar);
			} catch (Exception e) {
				logger.error("Error realizando el mapeo del ODE[" + odesPublicados[i].getIdODE()
						+ "]. Descartamos este ODE para reindexarlo y continuamos con el resto.[" + e.getCause() + "]",e);
				retornoPublicador.add(new ReindexarODEResultadoVO(rutaManifest, ERROR_MAPEO_INDEXADO, 12));
				continue;
			}

		}

		es.pode.indexador.negocio.servicios.indexado.IdODEVO[] arrayIDODES = listaAIndexar
		.toArray(new es.pode.indexador.negocio.servicios.indexado.IdODEVO[0]);
		// Llamar al indexador reindexando el ODE concreto.
		// Tengo que reindexar la lista dependiendo del numero de idiomas
		IndexadorVO[] retornoIndexador;
		try {
			if(logger.isDebugEnabled())	logger.debug("Reindexando [" + arrayIDODES.length + "] odes publicados.");
			retornoIndexador = this.getSrvIndexadorService().reindexarODE(arrayIDODES);
			
			//Optimizamos el indice, pues al realizar un borrados o reindexado se generan archivos .del que luego hacen que las operaciones
			//con los terminos indexados sean incorrectas o no reales
			if (logger.isDebugEnabled()) logger.debug("Vamos a optimizar el indice ...");
			this.getSrvIndexadorService().optimizarIndice(null);//con null optimizamos todos los subindices
		} catch (Exception e) {
			logger.error("Error invocando servicio de reindexado selectivo. Imposible reindexar.[" + e.getCause()+ "].");
			throw new Exception("Error invocando servicio de reindexado selectivo. Imposible reindexar.["+ e.getCause() + "].");
		}
		
				
		// Mapeamos los mensajes de retorno del metodo del indexador a mensajes de retorno del publicador.
		ReindexarODEResultadoVO[] retornoPublicadorIndexados = new ReindexarODEResultadoVO[0];
		if (retornoIndexador != null && retornoIndexador.length > 1) {
			retornoPublicadorIndexados = new ReindexarODEResultadoVO[retornoIndexador.length];
			if(logger.isDebugEnabled())	logger.debug("Mapeando [" + retornoIndexador.length + "]resultados de reindexado de odes publicados.");
			this.getBeanMapper().map(retornoIndexador, retornoPublicadorIndexados);
		}
		retornoPublicador.addAll(Arrays.asList(retornoPublicadorIndexados));
		return retornoPublicador.toArray(new ReindexarODEResultadoVO[0]);
	}


	/**
	 * Se regeneran los indices de los idiomas que se pasan. Se borran todos los
	 * odes indexados en esos idiomas y se indexan los odes publicados en el
	 * momento de la invocacion para cada idioma afectado.
	 * @param paramRegenera
	 *        Contiene la informacion necesaria para la regeneracion del repositorio.
	 * @return Se devuelve un VO con codigo de exito/fracaso para cada ODE.
	 * @throws Exception
	 */
	protected ReindexarODEResultadoVO[] handleRegeneraIndiceIdioma(RegeneracionIndiceVO paramRegenera) throws Exception {
		ArrayList<ReindexarODEResultadoVO> retornoPublicador = new ArrayList<ReindexarODEResultadoVO>();
		if (paramRegenera == null)
		{
			logger.warn("Error intentando regenerar indices. Los parametros de invocacion son nulos.");
			throw new PublicarException("Error intentando regenerar indices. Los parametros de invocacion son nulos.");
		}
		String[] idiomas = paramRegenera.getIdIdiomas();
		if (idiomas == null || idiomas.length == 0) {
			logger.warn("Error intentando regenerar indices. No se suministran idiomas sobre los que actuar.");
			throw new Exception("Error intentando regenerar indices. No se suministran idiomas sobre los que actuar.");
		}
		Long idTarea= paramRegenera.getIdTarea();
		if (idTarea == null) {
			logger.error("Error intentando regenerar indices. No tenemos tarea sobre la que avisar fin de trabajo.");
			throw new Exception("Error intentando regenerar indices. No tenemos tarea sobre la que avisar fin de trabajo.");
		}
		try{
//			Para cada idioma, realizo el tratamiento de regeneracion del indice.
			for (int i = 0; i < idiomas.length; i++) {
				String idioma = idiomas[i];
//				Borrar todos los odes indexados por idioma
				if (logger.isInfoEnabled()) logger.info("Regenerando indice para idioma["+idioma+"]. Borramos toda la informacion en el indice");
				int tamPagina = Integer.parseInt(SrvPublicacionServiceImpl.getPropertyValue(TAMANIO_PAGINA));
				this.getSrvIndexadorService().borrarOdesPorIdioma(idioma, tamPagina);
//				Ahora tenemos que recoger los ODEs publicados por idioma de forma paginada e indexarlos con el mismo tamaño de página.
				IdiomaCriteria criterioIdioma = new IdiomaCriteria(idioma);
//				Recogemos la primera pagina de resultados de ODEs a indexar.
				Integer primerResultado = 0;
//				Integer finResultado = new Integer(tamPagina);
//				Iniciamos el criterio para un recorrido paginado
				criterioIdioma.setFirstResult(primerResultado);
				criterioIdioma.setMaximumResultSize(tamPagina);
				Boolean hayResultadosPendientes = true;
//				Recorremos paginas de resultados (recorridos incrementalmente) para llamar al indexador de forma controlada.
				for(int pagina=1;hayResultadosPendientes;pagina++) {
//					Me voy a por resultados
					List<OdePublicado[]> odesPublicados = this.getOdePublicadoDao().obtenODEsPublicadosPorIdioma(criterioIdioma);
					if (odesPublicados == null || odesPublicados.size() == 0) // no hay más resultados que extraer del idioma
					{
						if (logger.isInfoEnabled()) logger.info("Obtenidos odes nulos publicados para idioma ["+idioma+"] en pagina ["+pagina+"]. Terminamos de regenerar el indice del idioma.");
						break; // si ya no tengo más ODEs que tratar, dejo de iterar.
					}
					if (logger.isInfoEnabled()) logger.info("Obtenidos ["+odesPublicados.size()+"] odes publicados para idioma["+idioma+"] en pagina["+pagina+"]");
//					Indexo los resultados
//					Genero la informacion a indexar
					ArrayList<ReindexarODEResultadoVO> listaODEsNoIndexables = new ArrayList<ReindexarODEResultadoVO>();
					IdODEVO[] arrayIndexables = this.generaOdeIndiceFromPublicado(odesPublicados.toArray(new OdePublicado[0]),  //Lista de ODEs que quiero indexar 
							listaODEsNoIndexables); //Lista de ODEs de los que he tenido problemas
					if (logger.isDebugEnabled()) logger.debug("Obtenida informacion de indexacion de["+arrayIndexables.length+"] odes de ["+odesPublicados.size()+"]publicados para idioma["+idioma+"] en pagina["+pagina+"]");
//					Añado a la lista de retorno publicador los odes de los que no se ha podido sacar la info para indexar
					retornoPublicador.addAll(listaODEsNoIndexables);
//					Indexo la informacion.
					IndexadorVO[] retornoIndexacion = this.getSrvIndexadorService().indexarODE(arrayIndexables);
					if (logger.isDebugEnabled()) logger.debug("Indexados["+retornoIndexacion.length+"] odes publicados para idioma["+idioma+"] en pagina["+pagina+"]");
//					Con los que se han podido indexar, los mapeo a VO's de retorno del publicador
					ReindexarODEResultadoVO[] retornoPublicadorIndexadosCorrectos = new ReindexarODEResultadoVO[arrayIndexables.length];
					for (int j = 0; j < retornoPublicadorIndexadosCorrectos.length; j++) {
						retornoPublicadorIndexadosCorrectos[j]= new ReindexarODEResultadoVO();
					}
					this.getBeanMapper().map(retornoIndexacion, retornoPublicadorIndexadosCorrectos);
//					Añado a los que no he podido parsear los que he podido indexar.
					retornoPublicador.addAll(Arrays.asList(retornoPublicadorIndexadosCorrectos)); 
//					Calculo la pagina siguiente
//					Si hay resultados, mayores que 0 y del tamaño de la pagina, iré a por más
					if (odesPublicados != null && odesPublicados.size()>0 && odesPublicados.size()== tamPagina)
					{
//						Calculo la nueva busqueda por pagina
						criterioIdioma.setFirstResult(tamPagina*pagina);
					}
					else
						hayResultadosPendientes = false;
				}
			}
			//Optimizamos el indice, pues al realizar un borrados o reindexado se generan archivos .del que luego hacen que las operaciones
			//con los terminos indexados sean incorrectas o no reales
			if (logger.isDebugEnabled()) logger.debug("Se procede a optimizar el indice ...");
			this.getSrvIndexadorService().optimizarIndice(null);//con null optimizamos todos los subindices
			
//			Determinamos si el trabajo se ha realizado con errores o sin errores
//			Si el codigo de error de alguno de los ODEs es != 0, ha habido error => la tarea se ha resuelto con errores.
//			OJO: esto presupone que el servicio sabe los codigos de error (o de exito en este caso) de modulos externos (el indexador en este caso).
			if (logger.isDebugEnabled()) logger.debug("Resultado regeneracion indice manejados["+retornoPublicador.size()+"] odes.");
			Iterator<ReindexarODEResultadoVO> iter = retornoPublicador.iterator();
			Boolean trabajoConError = false;
			int count = 0;
			while (iter.hasNext()) {
				ReindexarODEResultadoVO res =iter.next();
				if (logger.isDebugEnabled()) logger.debug("Resultado regeneracion indice["+((res == null)?"error":res.getCode())+"].");
				if (res == null || res.getCode() != 0)
				{
					trabajoConError= true;
//					break;
					if (logger.isDebugEnabled()) logger.debug("Resultado regeneracion indice error numero["+count+"] odes. Codigo["+(res==null?null:res.getCode())+"]");
				}
				count++;
			}
			// LLamamos al servicio de auditoria para actualizar el estado del trabajo
			TareaEjecutadaPlanVO trabajo = new TareaEjecutadaPlanVO(); 
			// Creamos la tarea donde notificaremos el exito o el fracaso
			if (trabajoConError)
				trabajo.setEstado(ConstantesAgrega.TRABAJO_ERRONEO);
			else
				trabajo.setEstado(ConstantesAgrega.TRABAJO_CORRECTO);
			trabajo.setFechaFin(Calendar.getInstance());
			trabajo.setId(paramRegenera.getIdTarea());
			this.getSrvAuditoriaServicio().registrarTrabajoFechaFinPlan(trabajo);
			return retornoPublicador.toArray(new ReindexarODEResultadoVO[0]);

		} catch (Exception e) {

			logger.error("Error realizando la regeneracion del indice por idiomas.",e);
			throw new Exception("Error realizando la regeneracion del indice por idiomas.", e);
		}
	}


	/**
	 * Este metodo se encarga de actualizar la informacion de la imagen del ode en el indice.
	 * Actualiza el fichero de vistaPreviaAgrega.png del ODE con la nueva imagen 
	 * @param idODE
	 *        Id del ODE al que hay que cambiar la imagen.
	 * @param imagen
	 *        nuevo path de la imagen.
	 * @return Se devuelve true si hubo exito o false en caso contrario.
	 * @throws Exception
	 */
	protected boolean handleCambiarImagenODE(String idODE, String imagen)
			throws Exception {
		
		if(idODE==null || idODE.isEmpty()) {
			logger.error("En la operacion de actualizacion de imagen de un ODE no se ha recibido el id del ODE.");
			return false;
		}
		
		logger.debug("Operacion de actualizacion de imagen con valor '"+imagen+"' con ide ODE '"+idODE+"'.");
		
		IdODEPublicadoCriteria criterio = new IdODEPublicadoCriteria();
		criterio.setIdODE(idODE);
		//Recogemos la primera pagina de resultados de ODEs a indexar.
		criterio.setFirstResult(0);
		criterio.setMaximumResultSize(1);
		
		try {
			List<OdePublicado[]> odesPublicados = this.getOdePublicadoDao().obtenODEsPublicadosPorID(criterio);
			if (odesPublicados == null) {
				logger.error("No se ha encontrado el ODE con id "+idODE+" al que habia que cambiar la ruta de la imagen por '"+imagen+"'");
				return false;
			} else if (odesPublicados.size() != 1) {
				logger.error("En la operacion de actualizacion de imagen con valor '"+imagen+"' se han encontrado "+odesPublicados.size()+" ODEs con el id buscado: "+idODE);
				return false;
			}
	
			//Eliminamos el ODE
			String[] odesAeliminar = new String[1];
			odesAeliminar[0] = idODE;
			IndexadorVO[] resultadoEliminacion = this.getSrvIndexadorService().eliminarODE(odesAeliminar);
			if(resultadoEliminacion[0].getCode()==1) { 
				logger.error("En la operacion de actualizacion de imagen no se pudo eliminar el ODE con id '"+idODE+"'. Mensaje del servicio: "+resultadoEliminacion[0].getMensaje());
				return false;
			}
			
			// 11042013 
			// Se corrige problema que impedía cambiar la imagen de un ODE cuando el ODE tenía el fichero vistaPreviaAgrega.png en el contenido del ODE
			// El problema era que al subir la imagen, se modificaba en la galeríaimg, pero al reindexar el ODE, volvía a machacar esas imágenes
			// con la vistaPreviaAgrega cargada inicialmente en el ODE.
			// La solución pasa por actualizar el fichero vistaPreviaAgrega.png con la nueva imagen que ha elegido el usuario
			// Para eso, machacamos la imagen del ODE con la de que se ha actualizado en la galeriaimg
			
			// Obtengo el array de localizadores
			LocalizadorVO localODE = this.getSrvLocalizadorService().consultaLocalizador(idODE);
	
			// La imagen que se pasa es en el formato adecuado para el índice compass. 
			// Para acceder a la imagen física hay que añadirle el uplods y el serverID para encontrarla.
			String sImagenOriginal = imagen.replace(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PROPERTY_IMAGE_APACHE_PATH), AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_GALERIA_IMG)+AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID) );
			
			String sImagenDestino=localODE.getPath()+"/"+this.getSrvPropiedadService().getValorPropiedad(AgregaProperties.VISTA_PREVIA_AGREGA);

			copiarFichero(new File(sImagenOriginal),new File(sImagenDestino));

			// 11042013 Continuamos con el proceso de reindexación
			
			//Insertamos
			ArrayList<ReindexarODEResultadoVO> listaODEsNoIndexables = new ArrayList<ReindexarODEResultadoVO>();
			IdODEVO[] arrayIndexables = this.generaOdeIndiceFromPublicado(odesPublicados.toArray(new OdePublicado[0]),  //Lista de ODEs que quiero indexar 
					listaODEsNoIndexables); //Lista de ODEs de los que he tenido problemas	
			
			if(listaODEsNoIndexables.size()>0) {
				logger.error("En la operacion de actualizacion de imagen con valor '"+imagen+"' hubo algun problema el generar el indice desde el publicado. Mensaje del servicio: "+listaODEsNoIndexables.get(0).getMensaje());
				return false;
			}
			if(arrayIndexables == null || arrayIndexables.length == 0){
				logger.error("En la operacion de actualizacion de imagen con valor '"+imagen+"' la lista de indexables esta vacia o es null.");
				return false;
			}
			//Cambiamos la imagen y reindexamos
			arrayIndexables[0].setImgFile(imagen);
			IndexadorVO[] retornoIndexacion = this.getSrvIndexadorService().indexarODE(arrayIndexables);
			logger.debug("Resultado del cambio de imagen: "+retornoIndexacion[0].getMensaje());
			return true;
			
		} catch (Exception e) {
			logger.error("ERROR en la operacion de actualizacion de imagen con valor '"+imagen+"' con ide ODE '"+idODE+"'.",e);
			throw new Exception ("ERROR en la operacion de actualizacion de imagen con valor '"+imagen+"' con ide ODE '"+idODE+"'.",e);
		}
	}
	

	private es.pode.indexador.negocio.servicios.indexado.IdODEVO[] generaOdeIndiceFromPublicado(
			OdePublicado[] arrayOdePublicados, ArrayList<ReindexarODEResultadoVO> listResultado) throws Exception {
		es.pode.indexador.negocio.servicios.indexado.IdODEVO[] arrayODEsIndice = null;
		try {
			if (arrayOdePublicados != null && arrayOdePublicados.length > 0) // si hay odes publicados para este idioma
			{
//				arrayODEsIndice = new es.pode.indexador.negocio.servicios.indexado.IdODEVO[arrayOdePublicados.length];
				// Cojemos todas las localizaciones para el array de odesPublicados que nos pasan
				String[] arrayIds = new String[arrayOdePublicados.length];
				for (int i = 0; i < arrayOdePublicados.length; i++) {
					arrayIds[i] = arrayOdePublicados[i].getIdODE();
				}
				// Obtengo el array de localizadores
				LocalizadorVO[] arrayLocalizaciones = this.getSrvLocalizadorService().buscarLocalizadoresPorId(arrayIds);
				// Para cada localizador Parseo el objeto
				ArrayList<IdODEVO> listIndexables = new ArrayList<IdODEVO>(10);
				for (int j = 0; j < arrayLocalizaciones.length; j++) {
					String sIdentificador = arrayLocalizaciones[j].getIdentificador();
					String sPath = arrayLocalizaciones[j].getPath();
					try {
						// Parseamos el ODE extrayendo la informacion a indexar
						if (logger.isDebugEnabled())logger.debug("Localizamos el ODE[" + sIdentificador + "] y con path [" + sPath+ "]y lo parseamos.");
						// Extraemos la informacion de valoracion para este ODE.
						String sValoracion = String.valueOf(this.getSrvValoracionService().consultarValoracion(arrayIds[j]));
						if (logger.isDebugEnabled())logger.debug("La valoracion del ODE [" + sIdentificador + "] es [" + sValoracion + "]");

						// Parseamos
						File fileManifest = new File(sPath, MANIFEST_NAME);
						Manifest imsmanifest = this.getScormDao().parsearODEEager(fileManifest);

						// Mapear la informacion de indexacion en el VO que pasamos al indexador, utilizando la valoracion
						// que nos pasan para indexarla.
						// rellenamos y añadimos a la lista todos los idodevos
						if (logger.isDebugEnabled())logger.debug("Generamos el objeto de indexacion del ODE [" + sIdentificador + "]");
						ManifestAgrega manifAgrega = new ManifestAgrega(imsmanifest);
						// Estamos mapeando la valoracion al reindexar.
						SrvNodoService nodo = this.getSrvNodoService();
						SrvEstructurasEducativasService estructuras = this.getSrvEstructurasEducativasService();
						SrvTaxonomiaService taxonomia = this.getSrvTaxonomiaService();
						es.pode.indexador.negocio.servicios.indexado.IdODEVO obj2Index = TratamientoODE
								.rellenaIdOdeVO(
										manifAgrega,
										sPath,
										sIdentificador,
										sValoracion,
										new Float(tamanioDir(sPath).floatValue()),
										nodo,
										estructuras,
										taxonomia,
										this, 
										this.getSrvPropiedadService());
						listIndexables.add(obj2Index);
					} catch (Exception ex) {
						logger.error("Se ha producido un error al intentar indexar el objeto [" + sIdentificador+ "] y path [" + sPath + "]", ex);
						listResultado.add(new ReindexarODEResultadoVO(sPath, ERROR_PARSEO_MANIFEST, 11));
					}
				}
				arrayODEsIndice = listIndexables.toArray(new es.pode.indexador.negocio.servicios.indexado.IdODEVO[listIndexables.size()]);
				if (logger.isDebugEnabled())
					logger.debug("Tenemos un total de [" + arrayODEsIndice.length + "] odes correctos y ["+ listResultado.size() + "] odes con errores");
			} else {
				logger.warn("No hay odes publicados ni se extrae informacion de indexacion para el idioma");
			}

		} catch (Exception ex) {
			logger.error("Error generando el indice de objetos publicados", ex);
			throw ex;
		}
		return arrayODEsIndice;
	}

	/**
	 * método auxiliar que tiene la funcionalidad común de publicar y publicar
	 * pif: la transición, mover el ode y la indexación.
	 * Este método se utiliza para la publicación con la licencia explícita
	 * 
	 * @throws Exception
	 */
	private ResultadoOperacionVO publicar_aux(java.lang.String idODE, String mec, java.lang.String idUsuario,
			java.lang.String comentarios, String comunidades, String universal, boolean cargaMasiva, String identificadorLicencia, String textoLicencia)
	throws Exception {	
		
		SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
		LocalizadorVO localizadorVO = localizadorService.crearLocalizadorPublicado(idODE, mec);
		if (logger.isDebugEnabled())
			logger.debug("Creamos el nuevo localizador publicado para identificador[" + idODE + "] y MEC[" + mec + "] y comentarios ["+comentarios+"]");

		if(!(localizadorVO.getMec().equals(""))){
			logger.warn("ERROR: Intentando publicar ODE con identificador [" + idODE + "]  con usuario[" + idUsuario
					+ "] y comentarios[" + comentarios
					+ "]. El identificador del ODE tiene una lozalizacion invalida => MEC[" + localizadorVO.getMec()+ "]");
			return new ResultadoOperacionVO(IDENTIFICADOR_ODE_LOCALIZACION_INVALIDA,
					I18nModuloPublicacion.getPropertyValueI18n(IDENTIFICADOR_ODE_LOCALIZACION_INVALIDA),idODE,0L);
		}
		EstadoDao estadoDao = this.getEstadoDao();
		TransicionDao transicionDao = this.getTransicionDao();
		OdePublicadoDao idiomaODEPublicadoDao = this.getOdePublicadoDao();
		// copiarlo
		LocalizadorVO oldLocal = localizadorService.consultaLocalizador(idODE);
		if (logger.isDebugEnabled()) {
			logger.debug("Movemos el contendio del ODE no publicado con identificador["
					+ oldLocal.getIdentificador() + "] a la localizacion del identificador["+ localizadorVO.getIdentificador() + "] publicado");
			logger.debug("Movemos [" + oldLocal.getPath() + "] -> [" + localizadorVO.getPath() + "]");
		}
		File oldFile = new File(oldLocal.getPath());
		if (oldFile.exists()) {
			// es necesario moverlo primero, pero solo lo borraremos al final.
			File newFile = new File(localizadorVO.getPath());
			Long tamanioODE = UtilesFicheros.moveDir(oldFile, newFile);

			// Extraemos el manifest y modificamos el lom que queremos
			File extraeSubmanifest = new File(localizadorVO.getPath(), MANIFEST_NAME);
			Manifest imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);
			ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
			String identifiadorManifest = manAgrega.getManifest().getIdentifier();
			
			//Si es carga masiva ponemos universal hardcodeado
			if (!cargaMasiva) {

				// Lom lom = manAgrega.obtenerLom(idODE, null);
				Lom lom = manAgrega.obtenerLom(identifiadorManifest, null);

				if (lom != null) {
					if(logger.isDebugEnabled())
						logger.debug("Publicacion del ode, añadiendo licencia;tipo: " + identificadorLicencia + ",ambito: "
								+ universal + ", comunidades: " + comunidades + ";");

					LomAgrega lomAgrega = new LomAgrega(lom);
					lomAgrega.getRightsAgrega().setDerechosDeAutor(identificadorLicencia);
					lomAgrega.getRightsAgrega().setAcceso(universal, comunidades);

					// Hacemos el set
					lom.setRights(lomAgrega.getRightsAgrega().getRights());
					if(logger.isDebugEnabled())
						logger.debug("introducePublicadorManifest:Re-introduciendo Lom en Manifest");
					// manAgrega.setLom(idODE, null, lom);

					//Antes de cambiar el UUID por MEC, vamos a verificar que no tiene ningún MEC, en el caso que tenga uno (no puede dar ningún otro caso,
					// puesto que el método de obtenerValidacion daría error si tuviera mas de un MEC, lo vamos a almacenar en relaciones y luego lo borramos de 
					//los identificadores y le insertamos el nuevo mec.
					logger.debug("Antes de modificar el identificador el comentario es "+comentarios);
					String entrada=lomAgrega.getGeneralAgrega().insertarIdentificadorMEC(ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.CATALOGO_MEC),mec);
//					RelationAgrega[] listaRelaciones=null;
					
					if(entrada!=null && !entrada.equals("")){
						logger.debug("Si modificamos el identificador el comentario es "+comentarios);
//						ArrayList relaciones=lomAgrega.getRelationsAgrega();
						RelationAgrega relacionNueva= new RelationAgrega(new Relation());
						RecursoAgrega recursoNuevo=new RecursoAgrega();
						ArrayList<LangStringAgrega> descripciones=new ArrayList<LangStringAgrega>();
						LangStringAgrega lang=new LangStringAgrega();
						lang.setIdioma("es");
						lang.setValor(SrvPublicacionServiceImpl.getPropertyValue("descripcion_mec_antiguo")+" "+entrada);
						descripciones.add(lang);
						recursoNuevo.setDescripciones(descripciones);
						IdentificadorAgrega identificador=new IdentificadorAgrega();
						identificador.setCatalogo(ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.CATALOGO_MEC));
						identificador.setEntrada(entrada);
						recursoNuevo.setIdentificador(identificador);

						relacionNueva.setRecursoAv(recursoNuevo);
						relacionNueva.setTipoAv(SrvPublicacionServiceImpl.getPropertyValue("descripcion_basado"));//se basa en
						lom.addRelation((relacionNueva).getRelation());
					}
					manAgrega.setLom(identifiadorManifest, null, lom);
					imsmanifest = manAgrega.getManifest();
					if(logger.isDebugEnabled())	logger.debug("introducePublicadorManifest:Lom modificado en objeto Manifest");
				}
				if(logger.isDebugEnabled())	logger.debug("Publicacion del ode, añadiendo licencia::Escribiendo Manifest modificado");
				// guardamos el manifest modificado
				File fManifest = new File(localizadorVO.getPath(), SrvPublicacionServiceImpl.MANIFEST_NAME);
				this.getScormDao().escribirODE(imsmanifest, fManifest);
				//TODO Copiar en el directorio localizadorVO.getPath() el fichero licencia.txt, este fichero se recogerá de uploads/licencias/identificadorLicencia
				//Actualmente existe una carpeta por cada uno de los tipos de licencias

				logger.debug("Vamos a copiar el licencia.txt a la misma altura que imsmanifest , la licencia de tipo"+identificadorLicencia);
//					String urlLicencias=ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.URL_LICENCIAS);
//					String urlEntero=urlLicencias+"/"+identificadorLicencia+"/"+this.LICENCIA_NAME;
//					File localizarLicencia=new File(urlEntero);
				File licencia=new File(localizadorVO.getPath()+"/"+SrvPublicacionServiceImpl.LICENCIA_NAME);
				if(licencia.exists()){
					UtilesFicheros.eliminar(licencia);
					if(!licencia.exists()){
						logger.debug("La licencia ha sido borrada "+licencia);
					}
				}
				FileWriter fichero = null;
				PrintWriter pw = null;
				try{
					fichero = new FileWriter(localizadorVO.getPath()+"/"+SrvPublicacionServiceImpl.LICENCIA_NAME);
					String lineSep = System.getProperty("line.separator");
					pw = new PrintWriter(fichero);
					String[] token=textoLicencia.split(lineSep);
					for(int i=0;i< token.length;i++){
						pw.println(token[i].toString());
					}
					pw.close();
				}catch(Exception e){
					logger.error("Error generando el  fichero licencia.txt", e);
				}

			} else {
				if(logger.isDebugEnabled())
					logger.debug("Publicación de ode en carga masiva, ponemos ambito a universal;");
				// Lom lom = manAgrega.obtenerLom(idODE, null);
				Lom lom = manAgrega.obtenerLom(identifiadorManifest, null);

				LomAgrega lomAgrega = new LomAgrega(lom);
				// licencia ponemos la que venga
				// lomAgrega.getRightsAgrega().setDerechosDeAutor(tipoLicencia);
				lomAgrega.getRightsAgrega().setAcceso(SrvPublicacionServiceImpl.UNIVERSAL, "");

				// Hacemos el set
				lom.setRights(lomAgrega.getRightsAgrega().getRights());
				if(logger.isDebugEnabled())
					logger.debug("introducePublicadorManifest:Re-introduciendo Lom en Manifest");
				// manAgrega.setLom(idODE, null, lom);
				manAgrega.setLom(identifiadorManifest, null, lom);
				imsmanifest = manAgrega.getManifest();
				if(logger.isDebugEnabled())	logger.debug("introducePublicadorManifest:Lom modificado en objeto Manifest");
			}
			logger.debug("Verificacion del MEC");

			// Comprueba si es primera indexacion y en tal caso, introduce MEC en LOMES.

			//cambiaUUIDxMEC(mec, localizadorVO.getPath(), imsmanifest, ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.CATALOGO_MEC)); 
			//Ya se lo hemos cambiado o añadido antes en insertarIdnetificadorMEC el catalogo mec
			ScormDao scorm = (ScormDao) this.getScormDao();
			// Aniadimos la contribucion del publicador
			String nombreApellidosUsuario = idUsuario2NombreApellidos(idUsuario);
			TratamientoODE.introducePublicadorManifest(localizadorVO.getPath(), nombreApellidosUsuario, imsmanifest, scorm);
			// Despues de la contribucion, tenemos que meter la localizacion del ODE en a pantalla de busqueda para que la ficha sea publica
			// y accesible en el caso de una busqueda por SQI.

			TratamientoODE.introduceLocalizacionWEB(localizadorVO.getPath(), idUsuario, imsmanifest, scorm, ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.CATALOGO_MEC));
			SrvNodoService nodo = this.getSrvNodoService();
			SrvEstructurasEducativasService estructuras = this.getSrvEstructurasEducativasService();
			SrvTaxonomiaService taxonomia = this.getSrvTaxonomiaService();

			// Aqui se hace el asunto de la imagen
			es.pode.indexador.negocio.servicios.indexado.IdODEVO idOdeVo = null;
			IndexadorVO res[] = null;
			es.pode.indexador.negocio.servicios.indexado.IdODEVO index[] = null;
			try {
				// 11042013 
				// Si en algun momento al realizar esta operacion se pierde la imagen del ode, se debe obtener la imagen actual del indice
				// y pasarsela como último parametro al TratamientoODE.rellenarIdOdeVO para que la mantenga
				// En el método reindexarODE hay un ejemplo que funciona correctamente.
				idOdeVo = TratamientoODE.rellenaIdOdeVO(
						new ManifestAgrega(imsmanifest), // Utilizamos el Manifest Agrega como wrapper del manifest del ODE
						localizadorVO.getPath(),
						mec,
						"-1",
						tamanioODE.floatValue(),
						nodo,
						estructuras,
						taxonomia,
						this, 
						this.getSrvPropiedadService());
				index = new es.pode.indexador.negocio.servicios.indexado.IdODEVO[1];
				index[0] = idOdeVo;
				res = this.getSrvIndexadorService().indexarODE(index);
			} catch (Exception ex) {
				// Borramos la info y salimos, no hay que deshacer bbdd
				this.getSrvLocalizadorService().eliminarLocalizador(localizadorVO.getIdentificador());
				logger.error("No se ha podido indexar ODE con id[" + idODE + "] mec[" + mec + "] y usuario["
						+ idUsuario + "], no se copia a la carpeta de publicados: error en indexación", ex);
				return new ResultadoOperacionVO(ERROR_INDEXACION, I18nModuloPublicacion.getPropertyValueI18n(ERROR_INDEXACION),idODE,0L);
			}
			if (res == null || !(res.length > 0) || (res[0].getCode() == 1)) {
				this.getSrvLocalizadorService().eliminarLocalizador(localizadorVO.getIdentificador());
				logger.warn("No se ha podido indexar ODE con id[" + idODE + "] mec[" + mec + "] y usuario["
						+ idUsuario + "], no se copia a la carpeta de publicados.");

				return new ResultadoOperacionVO(ERROR_INDEXACION, I18nModuloPublicacion.getPropertyValueI18n(ERROR_INDEXACION),idODE,0L);
			}
			if (logger.isDebugEnabled())
				logger.debug("Creamos la transicion del identificador[" + localizadorVO.getIdentificador()+ "] con estado actual[PUBLICADO]");
//					Actualizamos la transicion actual
			Transicion transicionActual = handleObtenerUltimaTransicion(idODE);
//			String ultimoComentario=transicionActual.getComentarios();
			String miIdioma="";
			if (idOdeVo!=null && idOdeVo.getCatalogacionPrimaria()!=null && idOdeVo.getCatalogacionPrimaria().getIdioma()!=null){
				miIdioma=idOdeVo.getCatalogacionPrimaria().getIdioma();
				logger.debug("mi idioma es " + miIdioma);
			}
//			EstadoVO estvo = this.obtenEstadoPorIdODE(idODE, miIdioma);
			transicionActual.setEstadoTransitado(estadoDao.obtenEstadoPorNombre(PUBLICADO));
			transicionDao.update(transicionActual);
			
			
//					Creamos la transicion de ODE publicado
			Date fecha = new Date(System.currentTimeMillis());
			transicionDao.create(DateManager.dateToCalendar(new Date(System.currentTimeMillis())), comentarios, mec, idUsuario, idOdeVo
					.getCatalogacionPrimaria().getTitulo(), transicionActual.getIdUsuarioCreacion(),// arrastramos el usuario de creacion del ODE
					fecha.getTime(),false, null, null, estadoDao.obtenEstadoPorNombre(PUBLICADO));

			// Creamos la nueva entrada del idioma en el que se ha publicado el ode
			// tenemos que calcular el MD5 del ODE para indicarlo en la tabla de publicados
			idiomaODEPublicadoDao.create(mec, idOdeVo.getCatalogacionPrimaria().getIdioma(), tamanioODE,
					DateManager.dateToCalendar(new Date(System.currentTimeMillis())), idOdeVo.getCatalogacionPrimaria().getTitulo(),
					TratamientoODE.obtenerMD5FromODE(imsmanifest));
			
			// Borramos la vieja info
			UtilesFicheros.eliminar(oldFile);
		} else {
			logger.warn("ERROR: Intentando publicar ODE con identificador [" + idODE + "]  con usuario["
					+ idUsuario + "] y comentarios[" + comentarios + "]. El fichero original[" + oldFile.getPath()+ "] no existe.");
			return new ResultadoOperacionVO(FICHERO_ORIGINAL_ODE_NO_EXISTE,
					I18nModuloPublicacion.getPropertyValueI18n(FICHERO_ORIGINAL_ODE_NO_EXISTE),idODE,0L);
		}		
		return new ResultadoOperacionVO(SIN_ERRORES, I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES),mec,0L);
	}


	/**
	 * 
	 * método auxiliar que tiene la funcionalidad común de publicar y publicar
	 * pif: la transición, mover el ode y la indexación.
	 * 
	 * @throws Exception
	 */
	private ResultadoOperacionVO publicar_aux(java.lang.String idODE, String mec, java.lang.String idUsuario,
			java.lang.String comentarios, String comunidades, String tipoLicencia, String universal, boolean cargaMasiva)throws Exception {

		SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
		SrvVocabulariosControladosService vocabulario = this.getSrvVocabulariosControladosService();
		SrvNodoService nodo = this.getSrvNodoService();
		SrvEstructurasEducativasService estructuras = this.getSrvEstructurasEducativasService();
		SrvTaxonomiaService taxonomia = this.getSrvTaxonomiaService();

		LocalizadorVO localizadorVO = localizadorService.crearLocalizadorPublicado(idODE, mec);
		if (logger.isDebugEnabled())
			logger.debug("Creamos el nuevo localizador publicado para identificador[" + idODE + "] y MEC[" + mec + "]");
		if (localizadorVO.getMec().equals(""))
			// esto es así pq el mec ahora está en el identifier del VO
		{
			EstadoDao estadoDao = this.getEstadoDao();
			TransicionDao transicionDao = this.getTransicionDao();
			OdePublicadoDao idiomaODEPublicadoDao = this.getOdePublicadoDao();
			ScormDao scorm = (ScormDao) this.getScormDao();

			// copiarlo
			LocalizadorVO oldLocal = localizadorService.consultaLocalizador(idODE);
			if (logger.isDebugEnabled()) {
				logger.debug("Movemos el contendio del ODE no publicado con identificador["
						+ oldLocal.getIdentificador() + "] a la localizacion del identificador["+ localizadorVO.getIdentificador() + "] publicado");
				logger.debug("Movemos [" + oldLocal.getPath() + "] -> [" + localizadorVO.getPath() + "]");
			}
			File oldFile = new File(oldLocal.getPath());
			if (!(oldFile.exists())) {
				logger.warn("ERROR: Intentando publicar ODE con identificador [" + idODE + "]  con usuario["
						+ idUsuario + "] y comentarios[" + comentarios + "]. El fichero original[" + oldFile.getPath()+ "] no existe.");
				return new ResultadoOperacionVO(FICHERO_ORIGINAL_ODE_NO_EXISTE,	I18nModuloPublicacion.getPropertyValueI18n(FICHERO_ORIGINAL_ODE_NO_EXISTE),idODE,0L);
			}
			// es necesario moverlo primero, pero solo lo borraremos al final.
			File newFile = new File(localizadorVO.getPath());
			Long tamanioODE = UtilesFicheros.moveDir(oldFile, newFile);

			// Extraemos el manifest y modificamos el lom que queremos
			File extraeSubmanifest = new File(localizadorVO.getPath(), MANIFEST_NAME);
			Manifest imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);
			ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
			String identifiadorManifest = manAgrega.getManifest().getIdentifier();
			Lom lom = manAgrega.obtenerLom(identifiadorManifest, null);
			if (!cargaMasiva) {
				if (lom != null) {
					if(logger.isDebugEnabled())
						logger.debug("Publicación del ode, añadiendo licencia;tipo: " + tipoLicencia + ",ambito: "+ universal + ", comunidades: " + comunidades + ";");
					LomAgrega lomAgrega = new LomAgrega(lom);
					lomAgrega.getRightsAgrega().setAcceso(SrvPublicacionServiceImpl.UNIVERSAL, "");
					lom.setRights(lomAgrega.getRightsAgrega().getRights());

					// Hacemos el set
					if(logger.isDebugEnabled())	logger.debug("introducePublicadorManifest:Re-introduciendo Lom en Manifest");
					manAgrega.setLom(identifiadorManifest, null, lom);
					imsmanifest = manAgrega.getManifest();

					if(logger.isDebugEnabled())	logger.debug("introducePublicadorManifest:Lom modificado en objeto Manifest");
				}
				if(logger.isDebugEnabled())	logger.debug("Publicación del ode, añadiendo licencia::Escribiendo Manifest modificado");
				// guardamos el manifest modificado
				File fManifest = new File(localizadorVO.getPath(), SrvPublicacionServiceImpl.MANIFEST_NAME);
				this.getScormDao().escribirODE(imsmanifest, fManifest);
				//TODO Copiar en el directorio localizadorVO.getPath() el fichero licencia.txt, este fichero se recogerá de uploads/licencias/identificadorLicencia
				//Actualmente existe una carpeta por cada uno de los tipos de licencias
			} else {
				if(logger.isDebugEnabled())	logger.debug("Publicación de ode en carga masiva, ponemos ambito a universal;");
				LomAgrega lomAgrega = new LomAgrega(lom);
				// licencia ponemos la que venga
				lomAgrega.getRightsAgrega().setAcceso(SrvPublicacionServiceImpl.UNIVERSAL, "");

				// Hacemos el set
				lom.setRights(lomAgrega.getRightsAgrega().getRights());
				if(logger.isDebugEnabled())
					logger.debug("introducePublicadorManifest:Re-introduciendo Lom en Manifest");
				manAgrega.setLom(identifiadorManifest, null, lom);
				imsmanifest = manAgrega.getManifest();
				if(logger.isDebugEnabled())
					logger.debug("introducePublicadorManifest:Lom modificado en objeto Manifest");
			}
			//Insertamos el texto de la licencia en un fichero licencia.txt en el ode; esta licencia corresponde al texto legal del tipo de
			// licencia que trae el ode.
			/*
			 * Extrae la licencia del lomes, consulta con fuentes taxonomicas el id de licencia segun VDEX y copia el fichero
			 * licencia.txt correspondiente en el ODE
			 */
			if(logger.isDebugEnabled()) logger.debug("Comenzando la identificación de la licencia para copiar licencia.txt");
			if(lom!=null) {
				TratamientoODE.insercionLicenciaOde(localizadorVO, lom, vocabulario);
			}
			// Comprueba si es primera indexacion y en tal caso, introduce MEC en LOMES.
			// Aniadimos la contribucion del publicador
			String nombreApellidosUsuario = idUsuario2NombreApellidos(idUsuario);
			TratamientoODE.introducePublicadorManifest(localizadorVO.getPath(), nombreApellidosUsuario, imsmanifest, scorm);
			// Despues de la contribucion, tenemos que meter la localizacion del ODE en a pantalla de busqueda para que la ficha sea publica
			// y accesible en el caso de una busqueda por SQI.
			TratamientoODE.introduceLocalizacionWEB(localizadorVO.getPath(), idUsuario, imsmanifest, scorm, ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.CATALOGO_MEC));

			// Aqui se hace el asunto de la imagen
			es.pode.indexador.negocio.servicios.indexado.IdODEVO idOdeVo = null;
			IndexadorVO res[] = null;
			es.pode.indexador.negocio.servicios.indexado.IdODEVO index[] = null;
			try {
				// 11042013 
				// Si en algun momento al realizar esta operacion se pierde la imagen del ode, se debe obtener la imagen actual del indice
				// y pasarsela como último parametro al TratamientoODE.rellenarIdOdeVO para que la mantenga
				// En el método reindexarODE hay un ejemplo que funciona correctamente.
				idOdeVo = TratamientoODE.rellenaIdOdeVO(// Utilizamos el Manifest Agrega como wrapper del manifest del ODE.
						new ManifestAgrega(imsmanifest),
						localizadorVO.getPath(),
						mec,
						"-1",
						new Float(tamanioODE.floatValue()),
						nodo, estructuras,
						taxonomia,
						this, 
						this.getSrvPropiedadService());

				index = new es.pode.indexador.negocio.servicios.indexado.IdODEVO[1];
				index[0] = idOdeVo;
				res = this.getSrvIndexadorService().indexarODE(index);
			} catch (Exception ex) {
				// Borramos la info y salimos, no hay que deshacer bbdd
				this.getSrvLocalizadorService().eliminarLocalizador(localizadorVO.getIdentificador());
				logger.error("No se ha podido indexar ODE con id[" + idODE + "] mec[" + mec + "] y usuario["
						+ idUsuario + "], no se copia a la carpeta de publicados: error en indexación",ex);
				return new ResultadoOperacionVO(ERROR_INDEXACION, I18nModuloPublicacion.getPropertyValueI18n(ERROR_INDEXACION),idODE,0L);
			}
			if (res == null || !(res.length > 0) || (res[0].getCode() == 1)) {
				this.getSrvLocalizadorService().eliminarLocalizador(localizadorVO.getIdentificador());
				logger.warn("No se ha podido indexar ODE con id[" + idODE + "] mec[" + mec + "] y usuario[" + idUsuario + "], no se copia a la carpeta de publicados: error en indexación");
				return new ResultadoOperacionVO(ERROR_INDEXACION, I18nModuloPublicacion.getPropertyValueI18n(ERROR_INDEXACION),idODE,0L);
			}
			if (logger.isDebugEnabled())
				logger.debug("Creamos la transicion del identificador[" + localizadorVO.getIdentificador()+ "] con estado actual[PUBLICADO]");
			// Actualizamos la transicion actual
			Transicion transicionActual = handleObtenerUltimaTransicion(idODE);
			transicionActual.setEstadoTransitado(estadoDao.obtenEstadoPorNombre(PUBLICADO));
			transicionDao.update(transicionActual);
			// Creamos la transicion de ODE publicado
			Date fecha = new Date(System.currentTimeMillis());
			transicionDao.create(DateManager.dateToCalendar(new Date(System.currentTimeMillis())), comentarios, mec, idUsuario, idOdeVo
					.getCatalogacionPrimaria().getTitulo(), transicionActual.getIdUsuarioCreacion(),// arrastramos el usuario de creacion del ODE.
					fecha.getTime(),false, null, null, estadoDao.obtenEstadoPorNombre(PUBLICADO));
			// Creamos la nueva entrada del idioma en el que se ha publicado el ODE
			idiomaODEPublicadoDao.create(mec, idOdeVo.getCatalogacionPrimaria().getIdioma(), tamanioODE,
					DateManager.dateToCalendar(new Date(System.currentTimeMillis())), idOdeVo.getCatalogacionPrimaria().getTitulo(),
					TratamientoODE.obtenerMD5FromODE(imsmanifest));
			// Borramos la vieja info
			UtilesFicheros.eliminar(oldFile);
		} else {
			logger.warn("ERROR: Intentando publicar ODE con identificador [" + idODE + "]  con usuario[" + idUsuario
					+ "] y comentarios[" + comentarios+ "]. El identificador del ODE tiene una lozalizacion invalida => MEC[" + localizadorVO.getMec()+ "]");
			return new ResultadoOperacionVO(IDENTIFICADOR_ODE_LOCALIZACION_INVALIDA,
					I18nModuloPublicacion.getPropertyValueI18n(IDENTIFICADOR_ODE_LOCALIZACION_INVALIDA),idODE,0L);
		}
		return new ResultadoOperacionVO(SIN_ERRORES, I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES),idODE,0L);
	}



	/**
	 * Este metodo devuelve los ODEs que estando en el estado que se le pasa,
	 * han sido creados por el usuario que se le pasa. Devuelve la lista de
	 * transiciones de ODEs que cumplen esta condicion ordenada por
	 * @param idUsuario identificador de Usuario
	 * @param estado Estado actual del ODE
	 * @return TransicionVO[]
	 */
	public static TransicionVO[] obtenerODEsPorEstadoUsuario(String idUsuario, String estado, EstadoDao estadoDao, TransicionDao transicion) {
		// Rescatamos los actuales que estan en el estado que nos pasan
		UsuarioCreacionEstadoFechaCriteria criterioActuales = new UsuarioCreacionEstadoFechaCriteria(idUsuario, // comprobamos que el usuario que nos pasan es el que creo el ODE.
				estadoDao.obtenEstadoPorNombre(estado), // estado actual
				null,// estado transitado
				null);// fecha
		// Obtenemos la lista de los ODEs que estan en estado actualmente.
		List lista = transicion.buscarODEsPorCriterioUsuarioCreacionEstadoFecha(
				TransicionDao.TRANSFORM_TRANSICIONVO, // vamos a devolver VOs
				criterioActuales); // el criterio de busqueda.

		// Devolvemos los elementos que hayamos encontrado que cumplen que el ODE actual este en estado y que fue el
		// usuario que nos pasan el que creo el ODE en su dia.
		if(logger.isDebugEnabled())	logger.debug("Obtenidos " + lista.size() + " odes por estado: " + estado + " para idusuario: " + idUsuario);
		return (TransicionVO[]) lista.toArray(new TransicionVO[0]);
	}


	/**
	 * Este metodo devuelve los ODEs que estando en el estado que se le pasa,
	 * han sido creados por el usuario que se le pasa. Devuelve la lista de
	 * transiciones de ODEs que cumplen esta condicion ordenada por
	 * @param idUsuario identificador de Usuario
	 * @param estado Estado actual del ODE
	 * @return TransicionVO[]
	 */
	public static TransicionVO[] obtenerODEsPorEstadoUsuarios(String[] idUsuarios, String estado, EstadoDao estadoDao, TransicionDao transicion) {
		// Rescatamos los actuales que estan en el estado que nos pasan
		UsuariosCreacionEstadoFechaCriteria criterioActuales = new UsuariosCreacionEstadoFechaCriteria(idUsuarios, // comprobamos que el usuario que nos pasan es el que creo el ODE.
				null, // estado transitado
				estadoDao.obtenEstadoPorNombre(estado),// estado actual
				null);// fecha
		// Obtenemos la lista de los ODEs que estan en estado actualmente y pertenecen a los tipos que nos pasan
		List lista = transicion.buscarODEsPorCriterioUsuariosCreacionEstadoFecha(
				TransicionDao.TRANSFORM_TRANSICIONVO, // vamos a devolver VOs
				criterioActuales); // el criterio de busqueda.

		// Devolvemos los elementos que hayamos encontrado que cumplen que el
		// ODE actual este en estado y que fue alguno de los
		// usuarios que nos pasan los que crearon el ODE en su dia.
		if(logger.isDebugEnabled())	logger.debug("Obtenidos " + lista.size() + " odes por estado: " + estado + " para usuarios: " + idUsuarios.length);
		return (TransicionVO[]) lista.toArray(new TransicionVO[lista.size()]);
	}


	/**
	 * Este metodo devuelve los ODEs que estando en el estado que se le pasa,
	 * han sido creados por el usuario que se le pasa. Devuelve la lista de
	 * transiciones de ODEs que cumplen esta condicion ordenada por
	 * @param idUsuario identificador de Usuario
	 * @param estado1 Estado que del ODE que recibe el método
	 * @param estado2 Estado actual del ODE
	 * @return TransicionVO[]
	 */
	private static TransicionVO[] obtenerODEsPorDosEstadosUsuario(String idUsuario, String estado1, String estado2, EstadoDao estadoDao, TransicionDao transicion) {
		// ESTADO1
		// Rescatamos los actuales que estan en el estado que nos pasan
		UsuarioCreacionEstadoFechaCriteria criterioActuales = 
			new UsuarioCreacionEstadoFechaCriteria(	idUsuario,// comprobamos que el usuario que nos pasan es el que creo el ODE 
					estadoDao.obtenEstadoPorNombre(estado1), // estado actual
					null,// estado transitado
					null);// fecha
		// Obtenemos la lista de los ODEs que estan en estado actualmente.
		List lista = transicion.buscarODEsPorCriterioUsuarioCreacionEstadoFecha(TransicionDao.TRANSFORM_TRANSICIONVO, // vamos a devolver VOs
				criterioActuales); // el criterio de busqueda.
		// ESTADO2
		criterioActuales = new UsuarioCreacionEstadoFechaCriteria(idUsuario, // comprobamos que el usuario que nos pasan es el que creo el ODE 
				estadoDao.obtenEstadoPorNombre(estado2), // estado actual
				null,// estado transitado
				null);// fecha

		lista.addAll(transicion.buscarODEsPorCriterioUsuarioCreacionEstadoFecha(
				TransicionDao.TRANSFORM_TRANSICIONVO, // vamos a devolver VOs
				criterioActuales)); // el criterio de busqueda.
		// Devolvemos los elementos que hayamos encontrado que cumplen que el
		// ODE actual este en estado y que fue el
		// usuario que nos pasan el que creo el ODE en su dia.
		if(logger.isDebugEnabled())
			logger.debug("Obtenidos " + lista.size() + " odes por 2 estados: " + estado1 + " y " + estado2+ " para idusuario: " + idUsuario);
		 return (TransicionVO[]) lista.toArray(new TransicionVO[0]);
	}

	/**
	 * Este metodo devuelve los ODEs que estando en el estado que se le pasa,
	 * han sido creados por el usuario que se le pasa. Devuelve la lista de
	 * transiciones de ODEs que cumplen esta condicion ordenada por
	 * @param idUsuario identificador de Usuario
	 * @param estado1 Estado que del ODE que recibe el método
	 * @param estado2 Estado actual del ODE
	 * @return TransicionVO[]
	 */
	private static TransicionVO[] obtenerODEsPorTresEstadosUsuario(String idUsuario, String estado1, String estado2,String estado3, EstadoDao estadoDao, TransicionDao transicion) {
		// ESTADO1
		// Rescatamos los actuales que estan en el estado que nos pasan
		UsuarioCreacionEstadoFechaCriteria criterioActuales = 
			new UsuarioCreacionEstadoFechaCriteria(	idUsuario,// comprobamos que el usuario que nos pasan es el que creo el ODE 
					estadoDao.obtenEstadoPorNombre(estado1), // estado actual
					null,// estado transitado
					null);// fecha
		// Obtenemos la lista de los ODEs que estan en estado actualmente.
		List lista = transicion.buscarODEsPorCriterioUsuarioCreacionEstadoFecha(TransicionDao.TRANSFORM_TRANSICIONVO, // vamos a devolver VOs
				criterioActuales); // el criterio de busqueda.
		// ESTADO2
		criterioActuales = new UsuarioCreacionEstadoFechaCriteria(idUsuario, // comprobamos que el usuario que nos pasan es el que creo el ODE 
				estadoDao.obtenEstadoPorNombre(estado2), // estado actual
				null,// estado transitado
				null);// fecha

		lista.addAll(transicion.buscarODEsPorCriterioUsuarioCreacionEstadoFecha(
				TransicionDao.TRANSFORM_TRANSICIONVO, // vamos a devolver VOs
				criterioActuales)); // el criterio de busqueda.

		// ESTADO3
		criterioActuales = new UsuarioCreacionEstadoFechaCriteria(idUsuario, // comprobamos que el usuario que nos pasan es el que creo el ODE 
				estadoDao.obtenEstadoPorNombre(estado3), // estado actual
				null,// estado transitado
				null);// fecha

		lista.addAll(transicion.buscarODEsPorCriterioUsuarioCreacionEstadoFecha(
				TransicionDao.TRANSFORM_TRANSICIONVO, // vamos a devolver VOs
				criterioActuales)); // el criterio de busqueda.

		// Devolvemos los elementos que hayamos encontrado que cumplen que el
		// ODE actual este en estado y que fue el
		// usuario que nos pasan el que creo el ODE en su dia.
		if(logger.isDebugEnabled())
			logger.debug("Obtenidos " + lista.size() + " odes por 3 estados: " + estado1 + " y " + estado2+ " y " + estado3+ " para idusuario: " + idUsuario);
		 return (TransicionVO[]) lista.toArray(new TransicionVO[0]);
	}

	
	/**
	 * Este metodo devuelve los ODEs que estando en el estado que se le pasa,
	 * han sido creados por el usuario que se le pasa, y que contienen la cadena que se les pasa como titulo. Devuelve la lista de
	 * transiciones de ODEs que cumplen esta condicion ordenada por
	 * @param titulo cadena de caracteres contenida en el titulo de los ODEs
	 * @param idUsuario identificador de Usuario
	 * @param estado1 Estado que del ODE que recibe el método
	 * @param estado2 Estado actual del ODE
	 * @return TransicionVO[]
	 */
	private static TransicionVO[] obtenerODEsPorTresEstadosUsuarioTitulo(String titulo, String idUsuario, String estado1, String estado2,String estado3, EstadoDao estadoDao, TransicionDao transicion) {
		// ESTADO1
		// Rescatamos los actuales que estan en el estado que nos pasan
		EstadoUsuarioTituloCriteria criterioActuales = 
			new EstadoUsuarioTituloCriteria(
					estadoDao.obtenEstadoPorNombre(estado1), // estado actual
					null, //estado transitado
					titulo,
					idUsuario// comprobamos que el usuario que nos pasan es el que creo el ODE 
					);
		// Obtenemos la lista de los ODEs que estan en estado actualmente.
		List lista = transicion.buscarOdesPorEstadoUsuarioTitulo(TransicionDao.TRANSFORM_TRANSICIONVO, // vamos a devolver VOs
				criterioActuales); // el criterio de busqueda.
		
		// ESTADO2
		criterioActuales = new EstadoUsuarioTituloCriteria(
				estadoDao.obtenEstadoPorNombre(estado2), // estado actual
				null, //estado transitado
				titulo,
				idUsuario// comprobamos que el usuario que nos pasan es el que creo el ODE 
				);
		lista.addAll(transicion.buscarOdesPorEstadoUsuarioTitulo(
				TransicionDao.TRANSFORM_TRANSICIONVO, // vamos a devolver VOs
				criterioActuales)); // el criterio de busqueda.

		// ESTADO3
		criterioActuales = new EstadoUsuarioTituloCriteria(
				estadoDao.obtenEstadoPorNombre(estado3), // estado actual
				null, //estado transitado
				titulo,
				idUsuario// comprobamos que el usuario que nos pasan es el que creo el ODE 
				);
		lista.addAll(transicion.buscarOdesPorEstadoUsuarioTitulo(
				TransicionDao.TRANSFORM_TRANSICIONVO, // vamos a devolver VOs
				criterioActuales)); // el criterio de busqueda.

		// Devolvemos los elementos que hayamos encontrado que cumplen que el
		// ODE actual este en estado y que fue el
		// usuario que nos pasan el que creo el ODE en su dia.
		logger.debug("Obtenidos " + lista.size() + " odes por 3 estados: " + estado1 + " y " + estado2+ " y " + estado3+ " para idusuario: " + idUsuario + " y para titulo :" + titulo);
		 return (TransicionVO[]) lista.toArray(new TransicionVO[0]);
	}
	

	/**
	 * Este metodo recoge los properties
	 * @param sKey
	 * 			La clave de la propiedad
	 * @return
	 * 		Devuelve la propiedad como un String
	 */
	public static String getPropertyValue(String sKey) {
		String sReturn = "";
		try {
			if (props == null) {
				InputStream fIsSpringProperties = SrvPublicacionServiceBase.class.getResourceAsStream(FILE_NAME_PROPERTIES);
				Properties properties = new java.util.Properties();
				properties.load(fIsSpringProperties);
				props=properties;
			}
			sReturn = props.getProperty(sKey);
			if (logger.isDebugEnabled()) logger.debug("propiedad obtenida: " + sReturn.toString());
		} catch (IOException e) {
			logger.error("Excepcion intentando obtener propiedad [" + sKey+ "] del fichero de propiedades del publicador[" + e.getCause() + "]");
		}
		// devolvemos la propiedad
		return sReturn;
	}
	

	/**
	 * Muestra los odes de todos los usuarios del noodo que estan despublicados
	 * (no disponibles).
	 * 
	 * @return TransicionVO[] Devuelve las transiciones de los ODEs que estan en
	 *         este estado.
	 * @throws Exception
	 */
	protected TransicionVO[] handleObtenODEsDespublicados() throws Exception {
		if (logger.isDebugEnabled())
			logger.debug("buscando Odes por estado: " + SrvPublicacionServiceImpl.NO_DISPONIBLE);
		TransicionDao transicion = this.getTransicionDao();
		EstadoCriteria criterio = new EstadoCriteria();
		EstadoDao estadoDao = this.getEstadoDao();

		criterio.setEstadoActual(estadoDao.obtenEstadoPorNombre(SrvPublicacionServiceImpl.NO_DISPONIBLE));
		List<TransicionVO> transiciones = transicion.buscarODEsPorCriterioEstado(TransicionDao.TRANSFORM_TRANSICIONVO, criterio);
		return transiciones.toArray(new TransicionVO[transiciones.size()]);
	}

	/**
	 * Este metodo devuelve los ODEs que estan en estado No Disponible asociados
	 * al usuario dado.
	 * 
	 * @param idUsuario
	 *            Identificador del usuario que creo el ODE y al que se le
	 *            asocia el ODE.
	 * @return TransicionVO[] Devuelve las transiciones de los ODEs que estan en
	 *         este estado para el usuario dado.
	 * @throws Exception
	 */
	protected TransicionVO[] handleObtenODEsDespublicadosPorUsuario(String idUsuario) throws Exception {
		return SrvPublicacionServiceImpl.obtenerODEsPorEstadoUsuario(idUsuario, SrvPublicacionServiceImpl.NO_DISPONIBLE, this.getEstadoDao(), this.getTransicionDao());
	}

	/**
	 * Este metodo devuelve los ODEs que estan en estado propuesto
	 * 
	 * @return TransicionVO[] Devuelve las transiciones de los ODEs que estan en
	 *         este estado para el usuario dado.
	 * @throws Exception
	 */
	protected TransicionVO[] handleObtenODEsPropuestos() throws Exception {
		if (logger.isDebugEnabled())
			logger.debug("buscando Odes por estado: " + SrvPublicacionServiceImpl.PROPUESTO);
		TransicionDao transicion = this.getTransicionDao();
		EstadoCriteria criterio = new EstadoCriteria();
		EstadoDao estadoDao = this.getEstadoDao();

		criterio.setEstadoActual(estadoDao.obtenEstadoPorNombre(SrvPublicacionServiceImpl.PROPUESTO));
		List<TransicionVO> transiciones = transicion.buscarODEsPorCriterioEstado(TransicionDao.TRANSFORM_TRANSICIONVO, criterio);
		return transiciones.toArray(new TransicionVO[transiciones.size()]);

	}

	/**
	 * Publica un ode que este en estado despublicado. No genera un nuevo codigo
	 * mec.
	 * 
	 * @param idODE
	 *            Identificador del ODE.
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @param comentarios
	 *            Comentarios que pueden ir asociados a la acción realizada.
	 * @param titulo
	 *            Titulo del ODE.
	 * @return ResultadoOperacionVO Devuelve un VO con el error que se ha producido en el caso
	 *         de detectarse algun problema.
	 * @throws Exception
	 * 
	 */
	protected ResultadoOperacionVO handlePublicarDespublicado(String idODE, String idUsuario, String comentarios,
			String titulo, String comunidades,  String universal,String identificadorLicencia, String textoLicencia) throws Exception {
		return publicarDespublicado( idODE, idUsuario, comentarios,
				titulo, comunidades,  universal, true,identificadorLicencia,  textoLicencia);
	}

	/**
	 * Publica un ode que este en estado despublicado. No genera un nuevo codigo
	 * mec.
	 * 
	 * @param idODE
	 *            Identificador del ODE.
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @param comentarios
	 *            Comentarios que pueden ir asociados a la acción realizada.
	 * @param titulo
	 *            Titulo del ODE.
	 * @param comunidades 
	 * 				comunidades a los que se asocia el ode
	 * @param universal
	 * 				si el ode tiene ámbito universal o no.
	 * @param validar 
	 * 				variable que indica si tenemos que validar el ode o no   
	 *@param identificadorLicencia
	 * 				tipo de licencia que tiene asociado el ode.  
	 *@param textoLicencia
	 * 				texto de la licencia 
	 * @return ResultadoOperacionVO Devuelve un VO con el error que se ha producido en el caso
	 *         de detectarse algun problema.
	 * @throws Exception
	 * 
	 */
	protected ResultadoOperacionVO publicarDespublicado(String idODE, String idUsuario, String comentarios,
			String titulo, String comunidades,  String universal, boolean validar, String identificadorLicencia, String textoLicencia) throws Exception {
		try {
			EstadoVO estado = this.obtenEstadoPorIdODE(idODE, LdapUserDetailsUtils.getIdioma());
			logger.debug("Publicando ODE (Despublicado)con identificador [" + idODE + "] en estado[" + estado.getClave()
					+ "] con usuario[" + idUsuario + "] y comentarios[" + comentarios + "].");
			if (estado.getClave().equals(SrvPublicacionServiceImpl.NO_DISPONIBLE)) {
				LocalizadorVO localizadorP = this.getSrvLocalizadorService().consultaLocalizador(idODE);
//   			Extraemos el manifest y modificamos el lom que queremos
				File extraeSubmanifest = new File(localizadorP.getPath(), MANIFEST_NAME);
				Manifest imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);
				ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
				Lom lom = manAgrega.obtenerLom(manAgrega.getManifest().getIdentifier(), null);
				
				if (lom != null) {
					if(logger.isDebugEnabled())	logger.debug("Publicación del ode, añadiendo licencia:Manipulando lom");
					LomAgrega lomAgrega = new LomAgrega(lom);
//					Publicación del ode, añadiendo el identificador que tenía al publicar, si lo han borrado se le inyecta, si lo han modificado se machaca el que tenía y si no lo han tocado se mantiene
					if(logger.isDebugEnabled())	logger.debug("Publicación del ode, añadiendo el identificador "+idODE+" con comentarios "+comentarios);
					lomAgrega.getGeneralAgrega().insertarIdentificadorMEC(ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.CATALOGO_MEC),idODE);
					lomAgrega.getRightsAgrega().setDerechosDeAutor(identificadorLicencia);
					lomAgrega.getRightsAgrega().setAcceso(universal, comunidades);
					// Hacemos el set
					lom.setRights(lomAgrega.getRightsAgrega().getRights());
					if(logger.isDebugEnabled())	logger.debug("introducePublicadorManifest:Re-introduciendo Lom en Manifest");
					manAgrega.setLom(manAgrega.getManifest().getIdentifier(), null, lom);
					imsmanifest = manAgrega.getManifest();
					if(logger.isDebugEnabled()) logger.debug("introducePublicadorManifest:Lom modificado en objeto Manifest");
				}
//				Modificamos el ciclo de vida, le introducimos la entidad y la fecha
				TratamientoODE.introducePublicadorManifest(localizadorP.getPath(), idUsuario, imsmanifest, (ScormDao) this.getScormDao());
				
//				Hacemos la validacion que nos devuelve el texto de error menos detallado.
				boolean ODEvalido = false;
				ValidaVO valid = null;
				if (validar)  // si hay que validar el ODE, lo valido y miro a ver si continuo
				{
					valid = this.getSrvValidadorService().obtenerValidacion(localizadorP.getPath());
					ODEvalido = valid.getEsValidoManifest().booleanValue();
					if(logger.isDebugEnabled())
						logger.debug("Validando el ODE [" + localizadorP.getPath() + "]");
				}
				else// si no hay que validar el ODE, es porque ya viene valido.
					ODEvalido = true;
				if(!ODEvalido){
					// bbdd
					logger.error("Imposible validar el ODE[" + localizadorP.getPath() + "] con error["+ valid.getResultadoValidacion() + "]. Imposible publicar");
					return new ResultadoOperacionVO(SrvPublicacionServiceImpl.ERROR_DE_VALIDACION, valid.getResultadoValidacion(),idODE,0L);
				}
				if (logger.isDebugEnabled())
					logger.debug("El ode: [" + localizadorP.getPath() + "] es válido");

				Long tamanio = tamanioDir(localizadorP.getPath());
				EstadoDao estadoDao = this.getEstadoDao();
				TransicionDao transicionDao = this.getTransicionDao();
				OdePublicadoDao idiomaODEPublicadoDao = this.getOdePublicadoDao();

				if(logger.isDebugEnabled())	logger.debug("Publicación del ode, añadiendo licencia::Escribiendo Manifest modificado");
				// guardamos el manifest modificado
				File fManifest = new File(localizadorP.getPath(), SrvPublicacionServiceImpl.MANIFEST_NAME);
				this.getScormDao().escribirODE(imsmanifest, fManifest);

				//TODO Habría que copiar la licencia dentro de la carpeta de repositorio, el texto de la licencia se encontrará dentro del directorio
				//cuyo nombre coincida con el valor de identificadorLicencia
				//La ruta de las licencias estará en agrega.properties (actualemente en desarrollo se encuentran en uploads/licencias)
				if(logger.isDebugEnabled()) logger.debug("Vamos a copiar el licencia.txt a la misma altura que imsmanifest , la licencia de tipo"+identificadorLicencia);
				File licencia=new File(localizadorP.getPath()+ "/" +SrvPublicacionServiceImpl.LICENCIA_NAME);
				if(licencia.exists()){
					UtilesFicheros.eliminar(licencia);
					if(!licencia.exists()){
						logger.debug("La licencia ha sido borrada "+licencia);
					}
				}
				if(logger.isDebugEnabled()) logger.debug("El texto de la licencia de origen existe"+textoLicencia +" y los vamos a copiar en "+ licencia);

				FileWriter fichero = null;
				PrintWriter pw = null;
				try
				{
					fichero = new FileWriter(localizadorP.getPath()+ "/" + SrvPublicacionServiceImpl.LICENCIA_NAME);
					String lineSep = System.getProperty("line.separator");
					pw = new PrintWriter(fichero);
					String[] token=textoLicencia.split(lineSep);
					for(int i=0;i< token.length;i++){
						pw.println(token[i].toString());
						if(logger.isDebugEnabled())logger.debug("Lo que pintamos es "+ token[i].toString());
					}
					pw.close();
				}catch(Exception e){
					logger.error("Error generando el  fichero licencia.txt en ODE despublicado["+idODE+"]", e);
				}

				SrvNodoService nodo = this.getSrvNodoService();
				SrvEstructurasEducativasService estructuras = this.getSrvEstructurasEducativasService();
				SrvTaxonomiaService taxonomia = this.getSrvTaxonomiaService();

				// Rellenar VOs para indexación,la valoracion, la dejamos a 3 de inicio, somos relativamente positivos.// Utilizamos el Manifest Agrega como wrapper del manifest del ODE.
				// 11042013 
				// Si en algun momento al realizar esta operacion se pierde la imagen del ode, se debe obtener la imagen actual del indice
				// y pasarsela como último parametro al TratamientoODE.rellenarIdOdeVO para que la mantenga
				// En el método reindexarODE hay un ejemplo que funciona correctamente.
				es.pode.indexador.negocio.servicios.indexado.IdODEVO idOdeVo = TratamientoODE.rellenaIdOdeVO(
						new ManifestAgrega(imsmanifest),
						localizadorP.getPath(),
						idODE,
						"-1",
						new Float(tamanioDir(localizadorP.getPath()).floatValue()),
						nodo,
						estructuras,
						taxonomia,
						this, 
						this.getSrvPropiedadService());

				es.pode.indexador.negocio.servicios.indexado.IdODEVO index[] = new es.pode.indexador.negocio.servicios.indexado.IdODEVO[1];
				index[0] = idOdeVo;
				IndexadorVO res[] = null;
				try {
					res = this.getSrvIndexadorService().indexarODE(index);
				} catch (Exception ex) {
					logger.error("No se ha podido indexar ODE con id[" + idODE + "] mec[" + idODE + "] y usuario["+ idUsuario + "], no se copia a la carpeta de publicados: error en indexación",ex);
					return new ResultadoOperacionVO(ERROR_INDEXACION, I18nModuloPublicacion.getPropertyValueI18n(ERROR_INDEXACION),idODE,0L);
				}
				if (res == null || !(res.length > 0) || (res[0].getCode() == 1)) {
					logger.warn("No se ha podido indexar ODE con id[" + idODE + "] mec[" + idODE + "] y usuario["+ idUsuario + "], no se copia a la carpeta de publicados: error en indexación");
					return new ResultadoOperacionVO(ERROR_INDEXACION, I18nModuloPublicacion.getPropertyValueI18n(ERROR_INDEXACION),idODE,0L);
				}
				// Actualizamos la transicion actual
				Transicion transicionActual = handleObtenerUltimaTransicion(idODE);
				logger.debug("Antes de la actualización, PUBLICADO, con comentarios "+transicionActual.getComentarios());
				String ultimoComentario=transicionActual.getComentarios();
				
				String miIdioma="";
				if (idOdeVo!=null && idOdeVo.getCatalogacionPrimaria()!=null && idOdeVo.getCatalogacionPrimaria().getIdioma()!=null){
					miIdioma=idOdeVo.getCatalogacionPrimaria().getIdioma();							
				}
				
				transicionActual.setEstadoTransitado(estadoDao.obtenEstadoPorNombre(PUBLICADO));
				logger.debug("Actualizamos la transición actual, PUBLICADO, con comentarios "+transicionActual.getComentarios());
				transicionDao.update(transicionActual);
				// Creamos la transicion de ODE publicado
//						Transicion transicionAux = handleObtenerUltimaTransicion(idODE);
				logger.debug("La última transición tenía los comentarios "+transicionActual.getComentarios());
				logger.debug("Creamos la última transición con comentarios "+comentarios);
				
				Date fecha = new Date(System.currentTimeMillis());
				transicionDao.create(DateManager.dateToCalendar(fecha), comentarios, idODE, idUsuario, idOdeVo.getCatalogacionPrimaria()
						.getTitulo(), transicionActual.getIdUsuarioCreacion(),// arrastramos el usuario de creacion del ODE
						fecha.getTime(),false, null, null, estadoDao.obtenEstadoPorNombre(PUBLICADO));
				logger.debug("Creamos la transicion del identificador[" + localizadorP.getIdentificador()+ "] con estado actual[PUBLICADO]");

				////////////////////////Tratamiento contenido inapropiado Publicar /////////////////
				//Si el ultimo comentario es un despublicado por contenido inapropiado entonces act tablas de contenido inapropiado
				EstadoVO estvo = this.obtenEstadoPorIdODE(idODE, miIdioma);
				if (ultimoComentario.equals(I18nModuloPublicacion.getPropertyValueI18n("COM_OK_CI")) && estvo!= null && estvo.getClave()!=null && estvo.getClave().equals(AgregaProperties.PUBLICADO)) {
					logger.debug("El Ode era un Reporte de contenido inapropiado y lo vamos a publicar. ");
					//como ya ha sido creada la transicion el ode aparece como publicado y solo debemos rechazar el reporte para que aparezca como arreglado
					this.getSrvContenidoInapropiadoService().rechazarContenidoInapropiado(idOdeVo.getIdODE(), miIdioma, AgregaProperties.NO_DISPONIBLE);							
				}
				////////////////////////Fin Tratamiento Contenido Inapropiado //////////////////////
				
				// Creamos la nueva entrada del idioma en el que se ha publicado el ode
				idiomaODEPublicadoDao.create(idODE, idOdeVo.getCatalogacionPrimaria().getIdioma(), tamanio,
						DateManager.dateToCalendar(new Date(System.currentTimeMillis())), idOdeVo.getCatalogacionPrimaria().getTitulo(),
						TratamientoODE.obtenerMD5FromODE(imsmanifest));
				boolean salida=getSrvCacheService().borrarHitCache();
				if(!salida) logger.debug("Error al borrar la cache.");
				return new ResultadoOperacionVO(SIN_ERRORES, I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES),idODE,0L);
			}
			logger.warn("ERROR: Intentando publicar ODE con identificador [" + idODE + "] en estado["
					+ estado.getClave() + "] con usuario[" + idUsuario + "] y comentarios[" + comentarios + "]. El estado del ODE no es NO DISPONIBLES.");
			return new ResultadoOperacionVO(TRANSICION_ESTADO_INVALIDA,
					I18nModuloPublicacion.getPropertyValueI18n(TRANSICION_ESTADO_INVALIDA),idODE,0L);
		} catch (Exception e) {
			PublicarException exception = new PublicarException("Fallo en la publicación de ODE con id " + idODE
					+ " y usuario " + idUsuario, e);
			logger.error("Fallo en la publicación de ODE con id " + idODE + " y usuario " + idUsuario, exception);
			throw exception;
		}
	}

	/**
	 * Este metodo calcula el tamanio del directorio o fichero que le pasan. Si
	 * se trata de un directorio calcula el tamanio del directorio en funcion
	 * del tamanio de los ficheros que contiene incluidos los ficheros que se
	 * hallen en subdirectorios dentro del directorio.
	 * @param path
	 * 			Ruta enviada al método
	 * @return long 
	 * 			La longitud de dicha ruta
	 */
	private Long tamanioDir(String path) throws IOException {
		if (path == null || path.equals(""))
			return 0L;
		long longitud = 0;
		File fichero = new File(path);
		if (fichero.isDirectory()) {
			String list[] = fichero.list();
			for (int i = 0; i < list.length; i++) {
				String subFichero = fichero.getAbsolutePath() + "/" + list[i];
				longitud += tamanioDir(subFichero).longValue();
			}
		} else {
			longitud = fichero.length();
		}
		return longitud;
	}

	/**
	 * Este metodo devuele la licencia de un ODE, del que sabemos su id.
	 * 
	 * @param idODE
	 *            Identificador del ODE.
	 * @param idioma
	 * @return LicenciaVO Devuelve la licencia.
	 * @throws Exception
	 */
	protected LicenciaVO handleObtenLicenciaODE(String idODE, String idioma) throws Exception {

		SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
		LocalizadorVO localizadorP = localizadorService.consultaLocalizador(idODE);

		File extraeSubmanifest = new File(localizadorP.getPath(), MANIFEST_NAME);
		Manifest imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);
		ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
		Lom lom = manAgrega.obtenerLom(manAgrega.getManifest().getIdentifier(), null);

		if (lom != null) {
			if(logger.isDebugEnabled())	logger.debug("Obten Licencia del ode["+idODE+"], añadiendo licencia:Manipulando lom");
			LicenciaVO licencia = new LicenciaVO();
			LomAgrega lomAgrega = new LomAgrega(lom);
			if(logger.isDebugEnabled()) logger.debug("Obten Licencia del ode["+idODE+"], obteniendo lomAgrega");
			licencia.setTipoLicencia(lomAgrega.getRightsAgrega().getDerechosDeAutor());
			if(logger.isDebugEnabled())	logger.debug("Obten Licencia del ode["+idODE+"], haciendo set de tipo licencia["+lomAgrega.getRightsAgrega().getDerechosDeAutor()+"]");
			licencia.setUniversal(lomAgrega.getRightsAgrega().getTipoDeAcceso());
			if(logger.isDebugEnabled())	logger.debug("Obten Licencia del ode["+idODE+"], haciendo set de tipo acceso["+lomAgrega.getRightsAgrega().getTipoDeAcceso()+"]");
			licencia.setComunidades(lomAgrega.getRightsAgrega().getDescripcionAcceso(idioma));
			if(logger.isDebugEnabled())	logger.debug("Obten Licencia del ode["+idODE+"], haciendo set de comunidades["+lomAgrega.getRightsAgrega().getDescripcionAcceso(idioma)+"]");
			return licencia;
		}
		logger.error("El Lom del ODE es null");
		return null;
	}

	/**
	 * Este metodo elimina un ODE publicado sin dejar rastro en la plataforma: -
	 * Elimina el historico de transiciones. - Elimina la localizacion de disco
	 * y todos los recursos. - Elimina la valoracion con que se haya valorado el
	 * ODE. - Elimina la indexacion del ODE. - Elimina la entrada de la tabla de
	 * publicados.
	 */
	private void eliminaODEPublicado(String mec) throws Exception {
		logger.debug("Invocando la eliminacion del ODE con mec[" + mec + "] de la plataforma.");
		// Eliminamos el historico de transiciones
		Transicion[] transiciones = (TransicionImpl[]) obtenHistorialPorIdODE(mec, TransicionDao.TRANSFORM_NONE);
		this.getTransicionDao().remove(Arrays.asList(transiciones));
		logger.info("Eliminadas [" + (transiciones != null ? transiciones.length : 0) + "] transiciones de mec[" + mec
				+ "] de la plataforma.");

		// Eliminamos los comentarios del ODE
		this.getSrvValoracionService().eliminarTodosComentarios(mec);
		logger.info("Eliminadas los comentarios de mec[" + mec + "] de la plataforma.");
		//Eliminamos la valoracion del ODE
		this.getSrvValoracionService().eliminarTodasValoraciones(mec);
		
		// Eliminamos la entrada en la tabla de publicados
		this.getOdePublicadoDao().eliminaODEsPublicadosPorId(mec);
		logger.info("Eliminada la entrada en publicados de mec[" + mec + "] de la plataforma.");
		// Eliminamos la indexacion
		this.getSrvIndexadorService().eliminarODE(new String[] { mec });
		logger.info("Eliminada la indexacion de mec[" + mec + "] de la plataforma.");
		//Optimizamos el indice, pues al realizar un borrados o reindexado se generan archivos .del que luego hacen que las operaciones
		//con los terminos indexados sean incorrectas o no reales
		if (logger.isDebugEnabled()) logger.debug("Vamos a optimizar el indice ...");
		this.getSrvIndexadorService().optimizarIndice(null);//con null optimizamos todos los subindices
		
		// Eliminamos la localizacion de disco y los recursos
		LocalizadorVO localizadorPub = null;
		LocalizadorVO localizadorNPub = null;
		try { 
			// Eliminamos la localizacion publicada
			// Esto puede fallar ya que busca el ODE en la carpeta repositorio.
			// Hay ejecuciones que pueden intentar eliminar ODEs que no estan publicados 
			// ni no publicados; en otras palabras que no existen en la plataforma como por el ejemplo
			// la tarea planificada de Carga de ODEs cuando se marca la opcion de sobrescribir ODEs
			localizadorPub = getSrvLocalizadorService().consultaLocalizador(mec);
			this.getSrvLocalizadorService().eliminarLocalizador(localizadorPub.getIdentificador());
			logger.info("Eliminada la localizacion de mec[" + mec + "] de repositorio [" + localizadorPub.getPath()+ "]");
		} catch (Exception e) {
			logger.warn("No se pudo eliminar localizador publicado de mec[" + mec + "]. Es posible que este ODE no existiera en la plataforma. "+e);
		}
		try {
			// Eliminamos la localizacion no publicada
			// Esto puede fallar ya que busca el ODE en la carpeta taller.
			// Hay ejecuciones que pueden intentar eliminar ODEs que no estan publicados 
			// ni no publicados; en otras palabras que no existen en la plataforma como por el ejemplo
			// la tarea planificada de Carga de ODEs cuando se marca la opcion de sobrescribir ODEs
			localizadorNPub = getSrvLocalizadorService().consultaLocalizadorNoPublicado(mec);
			this.getSrvLocalizadorService().eliminarLocalizador(localizadorNPub.getIdentificador());
			logger.info("Eliminada la localizacion de mec[" + mec + "] de taller [" + localizadorNPub.getPath() + "]");
		} catch (Exception e) {
			logger.warn("No se pudo eliminar localizador no publicado de mec[" + mec + "]. Es posible que este ODE no existiera en la plataforma."+e);
		}
		logger.info("Eliminado ODE publicado con mec[" + mec + "] de la plataforma.");
		return;
	}


	public void setJmsTemplate(JmsTemplate template) {
		this.jmsTemplate = template;
	}

	public JmsTemplate getJmsTemplate() {
		return this.jmsTemplate;
	}	

	/**
	 * Metodo que realiza la creacion de la ruta a mostrar y decide si se debe o
	 * no enviar a la cola JMS la peticion para generar la imagen o no. Si el
	 * archivo principal es: .doc, .xls, .ppt, .pdf, .mp3, .wav, etc. Se
	 * apuntara a un icono por defecto y no se solicitara la generacion de dicha
	 * imagen.
	 * 
	 * @param ode
	 *            vo del ode.
	 *            generar la imagen o no.
	 * @throws Exception
	 */
	//TODO Este es el método en donde se crearían las versiones escaladas de imágen dada o de por defecto si no hay
	//TODO Debería estar en TratamientoImagenes
	protected String handleCreateImage4Ode(OdeVO ode) throws Exception {
		String sReturn = null;
		try {
			if(logger.isDebugEnabled())	logger.debug("Begin:createImage4Ode");
			// Chequeo que el Archivo principal y el identificador vengan rellenos, si no es así lanzo una excepción.
			if (ode.getIdentificadorMEC() != null && !ode.getIdentificadorMEC().equals("") && ode.getMainFile() != null && !ode.getMainFile().equals("") && ode.getServerOn() != null && !ode.getServerOn().equals("")) {
				if (logger.isDebugEnabled()) logger.debug("Antes de enviar el mensaje id[" + ode.getIdentificadorMEC() + "] MainFile ["+ ode.getMainFile() + "] serverOn [" + ode.getServerOn() + "]");
//				this.sendMessage(ode);
				TratamientoImagenes.createImage4Odes(new OdeVO[]{ode}, this.getSrvPropiedadService());
				if(logger.isDebugEnabled()) logger.debug("El mensaje se ha enviado correctamente");
			} else
//				throw new RuntimeException("EL archivo principal no va relleno");
				logger.warn("AVISO: problema generando la imagen por defecto del ODE idMEC["+ode.getIdentificadorMEC()+"] localizador["+ode.getMainFile()+"] severOn["+ode.getServerOn()+"]. Suponemos que el ODE no tiene imagen por defecto.");
			// Si todo es correcto, envío el VO en un mensaje
			if(logger.isDebugEnabled())	logger.debug("End:createImage4Ode");
		} catch (Exception ex) {
			logger.error("Se ha producido un error en handleCreateImage4Ode", ex);
		}
		return sReturn;
	}

	/**
	 * Metodo encargado de enviar a la cola JMS el objeto del cual se desea
	 * generar una imagen.
	 * 
	 * @param ode
	 *            vo del ode.
	 * @throws Exception
	 * @deprecated
	 */
	protected void handleSendMessage(final OdeVO ode) throws Exception {
//		this.jmsTemplate.send(new MessageCreator() {
//			public Message createMessage(Session session) throws JMSException {
//				return new SimpleMessageConverter().toMessage(ode, session);
//			}
//		});
	}


	//TODO
	//LUIS: esta funcion debera ignorar aquellos ODEs con imagenes por defecto.
	//De esta forma no llamara a handleCreateImage4Ode que es la que crea imagenes.
	/**
	 * genera la imagen representativa del ode
	 * @param manifestAgrega
	 * @param sMec
	 * @param localizador TODO
	 * @param sLocalizador
	 */
	public String imagePathGenerate(ManifestAgrega manifestAgrega, String sMec, OdeVO ode, String localizador) {
		StringBuffer imagePathReturn = new StringBuffer();
		try {
			//La regeneracion de imagenes no es necesaria en la reindexacion aunque si en la publicacion.
			//Separamos la creacion del path de la generacion de imagenes --> TratamientoImagenes.pathGenerate
			this.handleCreateImage4Ode(ode);
			imagePathReturn = TratamientoImagenes.pathImagen(sMec, TratamientoImagenes.LITTLE_WIDTH, TratamientoImagenes.LITTLE_HEIGHT, localizador, true, this.getSrvPropiedadService());
		} catch (Exception ex) {
			logger.error("Se ha producido un error en imageGenerate", ex);
		}
		return imagePathReturn.toString();
	}

	protected es.pode.publicacion.negocio.servicios.IdODEVO[] handleBuscarODEsPorEstado(String estado, Integer inicio,
			Integer fin, Boolean hayMas) throws Exception {
		// No sirve eliminar del modelo
		return null;
	}

	/**
	 * Obtiene un historial de las transiciones de estados por los que ha ido
	 * pasando en su historia un ODE.
	 * 
	 * @param idODE
	 *            Identificador del ODE.
	 * @return se retorna el array con las transiciones ordenadas por fecha de
	 *         los estados del ODE.
	 * @throws Exception
	 * 
	 */
	/*
	 * Obtiene un historial de las transiciones de estados por los que ha ido
	 * pasando en su historia un ODE. Recibe un identificador de ODE y un
	 * identificador de transformacion para saber que clase de resultado se
	 * quiere: el VO de transicion o el Entity
	 */
	private Object[] obtenHistorialPorIdODE(String idODE, int transform) throws Exception {
		try {
			// Buscamos las transiciones ordenadas por fecha con el identificador que me pasan
			IdODEFechaCriteria criterio = new IdODEFechaCriteria(idODE, // identificador del ODE
					null,  //fecha
					null); //milisegundos
			TransicionDao transicion = this.getTransicionDao();
			// Obtenemos el historial del ODE que nos pasan.
			List list = transicion.buscarHistorialPorIdODEFecha(transform, criterio);

			// Tenemos que revisar que el id que nos pasan sea un MEC, en cuyo caso tenemos que recuperar el UUID
			// y anejar la informacion del ODE con el pasado con el UUID a la que ya hemos rescatado con el MEC
			LocalizadorVO localizadorNP = this.getSrvLocalizadorService().consultaLocalizadorNoPublicado(idODE);
			if (localizadorNP != null) {
				// Vamos a buscar el pasado de este ODE antes de ser publicado
				IdODEFechaCriteria criterio2 = new IdODEFechaCriteria(localizadorNP.getIdentificador(), // identificador del ODE (UUID)
						null,  //fecha
						null); //milisegundos
				// Tenemos que recuperar el pasado del ODE cuando no estaba publicado y anejarlo al que tenemos publicado
				List listaPasado = transicion.buscarHistorialPorIdODEFecha(transform, criterio2);// utilizamos el UUID del ODE.
				list.addAll(listaPasado);
			}
			if (TransicionDao.TRANSFORM_TRANSICIONVO == transform)
				return list.toArray(new TransicionVO[list.size()]);
			else
				return list.toArray(new TransicionImpl[list.size()]);
		} catch (Exception e) {
			ObtenerHistoriaODEException exception = new ObtenerHistoriaODEException("ERROR: fallo inesperado con id " + idODE, e);
			logger.error(exception);
			throw exception;
		}
	}



	/**
	 * Obtiene los odes que están propuestos para catalogación
	 * 
	 * @return se retorna el array con las transiciones ordenadas por fecha de
	 *         los ODEs en estado propuestos para catalogación.
	 * @throws Exception
	 * 
	 */
	protected TransicionVO[] handleObtenODESPropuestosCatalogacion() throws Exception {
		if(logger.isDebugEnabled())
			logger.debug("buscando Odes por estado: " + SrvPublicacionServiceImpl.PROPUESTO_CATALOGACION);
		TransicionDao transicion = this.getTransicionDao();
		EstadoCriteria criterio = new EstadoCriteria();
		EstadoDao estadoDao = this.getEstadoDao();
		criterio.setEstadoActual(estadoDao.obtenEstadoPorNombre(SrvPublicacionServiceImpl.PROPUESTO_CATALOGACION));
		List<TransicionVO> transiciones = transicion.buscarODEsPorCriterioEstado(TransicionDao.TRANSFORM_TRANSICIONVO, criterio);
		logger.info("Obtenidos " + transiciones.size() + " odes en estado " + SrvPublicacionServiceImpl.PROPUESTO_CATALOGACION);
		return transiciones.toArray(new TransicionVO[transiciones.size()]);
	}
	/**
	 * Obtiene los odes que están propuestos para catalogación por un usuario
	 * 
	 * @param idUsuario
	 * 			El identificador del usuario
	 * @return se retorna el array con las transiciones ordenadas por fecha de
	 *         los ODEs en estado propuestos para catalogación por un usuario.
	 * @throws Exception
	 * 
	 */
	protected TransicionVO[] handleObtenODESPropuestosCatalogacionPorUsuario(String idUsuario) throws Exception {
		return SrvPublicacionServiceImpl.obtenerODEsPorEstadoUsuario(idUsuario, SrvPublicacionServiceImpl.PROPUESTO_CATALOGACION, this.getEstadoDao(), this.getTransicionDao());
	}

	/**
	 * Este método propone para catalogación con o sin validación del ODE
	 * @param idODE
	 *                                 Identificador del ODE.
	 * @param idUsuario
	 *                                 Identificador del usuario que va a proponer para catalogación
	 * @param comentarios
	 *                                 Comentarios que pueden ir asociados a la acción realizada.
	 * @param titulo
	 *                                 Titulo del ODE.

	 * @return ResultadoOperacionVO Devuelve un VO con el error que se ha producido en el caso
	 *         de detectarse algun problema.
	 * @throws Exception
	 */
	protected ResultadoOperacionVO handleProponerCatalogacion(String idODE, String idUsuario, String comentarios,
			String titulo) throws Exception {
		return proponerCatalogacion(idODE, idUsuario, comentarios,	titulo, true,false);

	}

	/**
	 * Este método propone para catalogación con o sin validación del ODE
	 * @param idODE
	 * 			Identificador del ODE.
	 * @param idUsuario
	 * 			Identificador del usuario que va a proponer para catalogación
	 * @param comentarios
	 * 			Comentarios que pueden ir asociados a la acción realizada.
	 * @param titulo
	 * 			Titulo del ODE.
	 * @param validar
	 * 			Boolean para especificar si se debe validar el ode o no.
	 * @return ResultadoOperacionVO Devuelve un VO con el error que se ha producido en el caso
	 *         de detectarse algun problema.
	 * @throws Exception
	 */
	protected ResultadoOperacionVO proponerCatalogacion(String idODE, String idUsuario, String comentarios,	String titulo, boolean validar, boolean solicitarNuevaVersion) throws Exception {
		try {
			EstadoVO estado = this.obtenEstadoPorIdODE(idODE, LdapUserDetailsUtils.getIdioma());
			if(logger.isDebugEnabled())
				logger.debug("Proponiendo para catalogación ODE con identificador[" + idODE + "] usuario[" + idUsuario
						+ "] comentarios[" + comentarios + "] y en estado actual[" + estado + "]");
			// 20130827 Evolutivo modificacion de ODEs con versión
			//			Se añade el estado PUBLICADO_VERSIONANDOSE para que se permita proponer ODEs en ese estado
			if (estado != null && (estado.getClave().equals(SrvPublicacionServiceImpl.CREACION) || estado.getClave().equals(
					SrvPublicacionServiceImpl.RECHAZADO)
					|| estado.getClave().equals(
							SrvPublicacionServiceImpl.PUBLICADO_VERSIONANDOSE))) {
								
				// 20130827 Evolutivo modificación ODEs con versión
				//			Si el usuario ha solicitado que se cree una nueva versión se inserta la relación esVersionDe 
				//			para marcar el ODE y que al publicarlo se gestione como nueva versión
				if (solicitarNuevaVersion)
					insertarRelacionEsVersion(idODE);
				// Si no es nueva versión se elimina la relación esVersiónDe
				else
					eliminarRelacionEsVersionAutomatica(idODE);
								
				// validador
				SrvValidadorService validadorService = this.getSrvValidadorService();
				SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
				LocalizadorVO localizadorNP = localizadorService.consultaLocalizador(idODE);
//				Hacemos la validacion que nos devuelve el texto de error menos detallado.
				boolean ODEvalido = false;
				ValidaVO valid = null;
				if (validar){  // si hay que validar el ODE, lo valido y miro a ver si continuo
					valid = validadorService.obtenerValidacion(localizadorNP.getPath());
					ODEvalido = valid.getEsValidoManifest().booleanValue();
					if(logger.isDebugEnabled()) logger.debug("Validando el ODE [" + localizadorNP.getPath() + "]");
				}
				else// si no hay que validar el ODE, es porque ya viene valido.
					ODEvalido = true;
				if(!(ODEvalido)){
					logger.warn("ERROR: proponiendo para catalogación ODE con identificador.[" + idODE	+ "] ODE en estado[" + estado.getClave() + "]. El ode no valida correctamente: "
							+ valid.getResultadoValidacion());
					return new ResultadoOperacionVO(SrvPublicacionServiceImpl.ERROR_DE_VALIDACION, valid.getResultadoValidacion(),idODE,0L);
				}
				
				ValidarLicenciasVO validarLicencias = getSrvGruposLicencias().validarLicencias(localizadorNP.getPath());
				if(!validarLicencias.isResultado()) {
//					return new ResultadoOperacionVO(SrvPublicacionServiceImpl.ERROR_DE_VALIDACION,"Error en elemento "+validarLicencias.getIdentificadorElemento()+"con licencia "+validarLicencias.getLicenciaAdicional(),idODE,0L);
					logger.warn(validarLicencias.getLicenciaAdicional());
					return new ResultadoOperacionVO(SrvPublicacionServiceImpl.ERROR_DE_VALIDACION,"Error con licencia "+validarLicencias.getLicenciaAdicional(),idODE,0L);
				}
				
				try{
					this.getSrvAlbumService().desasociarODEAlbum(idODE);		
				}catch(Exception ex){
					logger.error("ERROR: desasociando el ode[" + idODE+ "] del album al que esta asociado.",ex);
				}
				EstadoDao estadoDao = this.getEstadoDao();
				TransicionDao transicionDao = this.getTransicionDao();
				Transicion transicionActual = handleObtenerUltimaTransicion(idODE);
				transicionActual.setEstadoTransitado(estadoDao
						.obtenEstadoPorNombre(SrvPublicacionServiceImpl.PROPUESTO_CATALOGACION));
				transicionDao.update(transicionActual);
				logger.info("Actualizamos estado de ODE con identificador[" + idODE	+ "] a PROPUESTO para catalogacion.");
				Date fecha = new Date(System.currentTimeMillis());
				transicionDao.create(DateManager.dateToCalendar(fecha), comentarios, idODE, idUsuario, titulo, transicionActual
						.getIdUsuarioCreacion(),// arrastramos el usuario de creacion
						fecha.getTime(),false, null, null, estadoDao.obtenEstadoPorNombre(SrvPublicacionServiceImpl.PROPUESTO_CATALOGACION));
							
				return new ResultadoOperacionVO(SIN_ERRORES, I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES),idODE,0L);
			}
			logger.warn("ERROR: proponiendo para catalogacion ODE con identificador[" + idODE
					+ "]. ODE en estado[" + estado.getClave()+ "] no se puede pasar a estado PROPUESTO para catalogacion.");
			return new ResultadoOperacionVO(TRANSICION_ESTADO_INVALIDA,I18nModuloPublicacion.getPropertyValueI18n(TRANSICION_ESTADO_INVALIDA),idODE,0L);
		} catch (Exception e) {
			Exception propuesto = new Exception(
					"Excepción en la proposicion de catalogación del ODE con identificador", e);
			logger.error("Excepción en la proposicion de catalogación del ODE con identificador[" + idODE
					+ "] idUsuario[" + idUsuario + "] comentarios[" + comentarios + "]y titulo[" + titulo + "]",propuesto);
			throw propuesto;
		}

	}

	/*	
	 * * Realiza las operaciones de creacion de un ODE en estado CREADO. Valida la
	 * informacion del ODE que se pasa en formato PIF(ZIP) y lo alberga en la
	 * plataforma bajo el usuario con el que se crea. Se descomprime en un
	 * temporal para validarlo, y si valida se crea un localizador y se copia en
	 * él teniendo en cuenta la cuota del usuario.
	 * */
	/**
	 * Un método como crearPif, sólo que toma en cuenta el tamaño del ODE a la hora de validar.
	 * Este metodo crea un ODE en estado CREADO a partir de un fichero PIF teniendo en cuenta el espacio consumido por el usuario (CREADOS más RECHAZADOS) y la cuota de espacio del usuario.
	 * Si el ODE no cabe en la cuota del usuario, se devuelve un error en consecuencia. Si hay espacio suficiente se crea el ODE.
	 * 
	 * @param pif
	 *            Fichero en formato PIF.
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @param comentarios
	 *            Comentarios que pueden ir asociados a la acción realizada.
	 * @param titulo
	 *            Titulo del ODE.
	 * @param idioma
	 *            Idioma del ODE.

	 * @return se retorna un VO con el error que se ha producido en el caso de
	 *         detectarse algun problema.
	 * @throws Exception
	 * 
	 */


	protected ResultadoOperacionVO handleCrearPifConCuota(DataHandler pif, String idUsuario, String comentarios, String titulo, String idioma) throws Exception {
//		Invocamos al metodo auxiliar indicandole que queremos tener en cuenta la cuota de usuario.
		return crearPIFAux(pif, idUsuario, comentarios, titulo, idioma, true, null,null);
	}
	/* Este metodo nos da las transiciones de los ODEs propuestos para catalogacion del grupo(s) de trabajo(s) al/ a los que pertenece
	 * el usuario. Los identificadores de entrada son los identificadores de los usuarios que comparten grupo(s) con nuestro usuario
	 * */
	/**
	 * Este metodo nos da las transiciones de los ODEs propuestos para catalogacion del grupo(s) de trabajo(s) al/ a los que pertenece
	 * el usuario. Los identificadores de entrada son los identificadores de los usuarios que comparten grupo(s) con nuestro usuario
	 * 
	 * @param idsUsuarios
	 * 			Los identificadores de entrada son los identificadores de los usuarios que comparten grupo(s) con nuestro usuario
	 * @return se retorna el array con las transiciones ordenadas por fecha de
	 *         los ODEs en estado propuestos para catalogación por un array de usuarios.
	 * @throws Exception
	 * 
	 */
	protected TransicionVO[] handleObtenODEsPropuestosCatalogacionPorUsuarios(String[] idsUsuarios) throws Exception {

		return SrvPublicacionServiceImpl.obtenerODEsPorEstadoUsuarios(idsUsuarios, SrvPublicacionServiceImpl.PROPUESTO_CATALOGACION, this.getEstadoDao(), this.getTransicionDao());
	}
	
	
   	/**
   	 * Metodo que recibe el nombre de un fichero fisico y del nombre de un recurso de un manifest
   	 * y devuelve true si el nombre del fichero fisico es el del nombre del recurso.
   	 */
   	private boolean esRecursoBuscado (String nombreRecursoOriginal, String nombreFichero) {
		
		String patron=nombreRecursoOriginal;
		String cadena=nombreFichero;
		
		//Eliminamos caracteres no alfanumericos; ñ, espacios, puntuacion, acentos, dieresis, etc.
		cadena = cadena.replaceAll("\\W", "");		
		patron = patron.replaceAll("\\W", ".*");
		
		Pattern pat = Pattern.compile(patron);
		Matcher mat = pat.matcher(cadena);
		
		if (mat.matches()) 
			return true;
		return false;
	}
   	

   	/**
   	 * Metodo que se ejecuta al importar un ODE. Este metodo examina todos los recursos del ODE
   	 * una vez que ya esta descomprimido en la carpeta del usuario y elimina acentos y caracteres 
   	 * extraños de los recursos editando no solo el nombre de los ficheros, si no tambien ajustando 
   	 * la entrada correspondiente en el manifest .
   	 * NOTA: un recurso puede tener varios ficheros y solo uno de esos ficheros esta asignado en 
   	 * el href del recurso.
   	 */
	private void eliminarCaracteresExtranosDeRecursos(String idODE) throws Exception {

		//Recorro cada recurso del manifest y le aplico el filtrado al nombre.
		//Si al aplicar el filtrado el nombre se ha modificado cambio en nombre del fichero fisico en disco. 

		SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
		LocalizadorVO localizadorNP = localizadorService.consultaLocalizador(idODE);

		File manifest = new File(localizadorNP.getPath(), MANIFEST_NAME);
		Manifest imsmanifest = this.getScormDao().parsearODEEager(manifest);
		ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
		
		//Recorremos los recursos
		for(int r=0; r<manAgrega.getManifest().getResources().getResourceCount(); r++)
		{
			Resource resource = manAgrega.getManifest().getResources().getResource(r);
			String nombreRecursoOriginal=resource.getHref();	
			
			if(nombreRecursoOriginal!=null) {
				
				String nombreRecursoNuevo = UtilesString.filtroCaracteresSimples(nombreRecursoOriginal);
				
				if(!nombreRecursoNuevo.contentEquals(nombreRecursoOriginal)) {
					resource.setHref(nombreRecursoNuevo);
					//No escribimos todavia el cambio en fichero ni buscamos el fichero fisico en disco
					//ya que el href del recurso coincidira siempre con el de algun fichero y esto
					//se hace en el bucle de abajo
				}
			}
						
			for(int f=0; f<resource.getFileCount(); f++)
			{
				es.pode.parseadorXML.castor.File fichero = resource.getFile(f);
				String nombreFicheroOriginal=fichero.getHref();
				String nombreFicheroNuevo = UtilesString.filtroCaracteresSimples(nombreFicheroOriginal);

				//Si el nombre no es el mismo modificamos el recurso
				if(!nombreFicheroNuevo.contentEquals(nombreFicheroOriginal)) {
					
					File recursoFisicoOriginal = new File(localizadorNP.getPath()+File.separator+nombreFicheroOriginal);
					File recursoFisicoNuevo = new File(localizadorNP.getPath()+File.separator+nombreFicheroNuevo);
					
					if(recursoFisicoOriginal.exists()) {
						recursoFisicoOriginal.renameTo(recursoFisicoNuevo);
						fichero.setHref(nombreFicheroNuevo);
						//Escribimos el cambio de nombre de fichero en disco
						this.getScormDao().escribirODE(imsmanifest, manifest);
						
					} else {
						//Buscamos el fichero que corresponde al recurso. Debido a algun problema
						//con caracteres extraños no se puede localizar como debiera. Es por ello
						//que debemos buscarlo y renombrarlo para evitar este tipo de problemas
						
						//Obtenemos la ruta del recurso. Suponemos que dicha ruta no contiene caracteres
						//extranos. Los caracteres extranos estan solo en el nombre del fichero.
						String carpetas[] = nombreFicheroOriginal.split(File.separator);
						String rutaRecurso="";
						for(int c=0; c<carpetas.length-1; c++)
							rutaRecurso=rutaRecurso+File.separator+carpetas[c];
						
						//Listamos el contenido de la ruta resultante y buscamos el fichero
						//al que pertenece el recurso.
						File carpetaRecurso= new File(localizadorNP.getPath()+File.separator+rutaRecurso);
						if(!carpetaRecurso.isDirectory()) {
							logger.debug(carpetaRecurso.getAbsolutePath()+" no es un directorio");
							continue;
						}
						File[] ficheros = carpetaRecurso.listFiles();
						
						for (int i = 0; i<ficheros.length; i++) {
							if(ficheros[i].isFile()) {
								if(esRecursoBuscado(nombreFicheroOriginal, ficheros[i].getName())) {
									ficheros[i].renameTo(recursoFisicoNuevo);
									fichero.setHref(nombreFicheroNuevo);
									//Escribimos el cambio de nombre de fichero en disco
									this.getScormDao().escribirODE(imsmanifest, manifest);
									break;
								}
							}
						}
					}
				}
				
			}
		}
		
	}
	
	
  	/**
   	 * Metodo que se ejecuta al importar un ODE. Este metodo examina todos los recursos del ODE
   	 * una vez que ya esta descomprimido en la carpeta del usuario y elimina acentos y caracteres 
   	 * extraños de los recursos editando no solo el nombre de los ficheros, si no tambien ajustando 
   	 * la entrada correspondiente en el manifest .
   	 * NOTA: un recurso puede tener varios ficheros y solo uno de esos ficheros esta asignado en 
   	 * el href del recurso.
   	 */
	private void eliminarCaracteresExtranosDeManifest(String rutaLocalizador, String rutaFicTemp) throws Exception {

		//Recorro cada recurso del manifest y le aplico el filtrado al nombre.
		//Si al aplicar el filtrado el nombre se ha modificado cambio en nombre del fichero fisico en disco. 

		File manifest = new File(rutaFicTemp, MANIFEST_NAME);
		Manifest imsmanifest = this.getScormDao().parsearODEEager(manifest);
		ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
		
		//Recorremos los recursos
		for(int r=0; r<manAgrega.getManifest().getResources().getResourceCount(); r++)
		{
			Resource resource = manAgrega.getManifest().getResources().getResource(r);
			String nombreRecursoOriginal=resource.getHref();	
			
			if(nombreRecursoOriginal!=null) {
				
				String nombreRecursoNuevo = UtilesString.filtroCaracteresSimples(nombreRecursoOriginal);
				
				if(!nombreRecursoNuevo.contentEquals(nombreRecursoOriginal)) {
					resource.setHref(nombreRecursoNuevo);
					//No escribimos todavia el cambio en fichero ni buscamos el fichero fisico en disco
					//ya que el href del recurso coincidira siempre con el de algun fichero y esto
					//se hace en el bucle de abajo
				}
			}
		
			for(int f=0; f<resource.getFileCount(); f++)
			{
				es.pode.parseadorXML.castor.File fichero = resource.getFile(f);
				String nombreFicheroOriginal=fichero.getHref();
				String nombreFicheroNuevo = UtilesString.filtroCaracteresSimples(nombreFicheroOriginal);

				//Si el nombre no es el mismo modificamos el recurso
				if(!nombreFicheroNuevo.contentEquals(nombreFicheroOriginal)) {
					
					File recursoFisicoOriginal = new File(rutaLocalizador+File.separator+nombreFicheroOriginal);
					File recursoFisicoNuevo = new File(rutaLocalizador+File.separator+nombreFicheroNuevo);
					
					if(recursoFisicoOriginal.exists()) {
						recursoFisicoOriginal.renameTo(recursoFisicoNuevo);
						fichero.setHref(nombreFicheroNuevo);
						//Escribimos el cambio de nombre de fichero en disco
						this.getScormDao().escribirODE(imsmanifest, manifest);
						
					} else {
						//Buscamos el fichero que corresponde al recurso. Debido a algun problema
						//con caracteres extraños no se puede localizar como debiera. Es por ello
						//que debemos buscarlo y renombrarlo para evitar este tipo de problemas
						
						//Obtenemos la ruta del recurso. Suponemos que dicha ruta no contiene caracteres
						//extranos. Los caracteres extranos estan solo en el nombre del fichero.
						String carpetas[] = nombreFicheroOriginal.split(File.separator);
						String rutaRecurso="";
						for(int c=0; c<carpetas.length-1; c++)
							rutaRecurso=rutaRecurso+File.separator+carpetas[c];
						
						//Listamos el contenido de la ruta resultante y buscamos el fichero
						//al que pertenece el recurso.
						File carpetaRecurso= new File(rutaLocalizador+File.separator+rutaRecurso);
						if(!carpetaRecurso.isDirectory()) {
							logger.debug(carpetaRecurso.getAbsolutePath()+" no es un directorio");
							continue;
						}
						File[] ficheros = carpetaRecurso.listFiles();
						
						for (int i = 0; i<ficheros.length; i++) {
							if(ficheros[i].isFile()) {
								if(esRecursoBuscado(nombreFicheroOriginal, ficheros[i].getName())) {
									ficheros[i].renameTo(recursoFisicoNuevo);
									fichero.setHref(nombreFicheroNuevo);
									//Escribimos el cambio de nombre de fichero en disco
									this.getScormDao().escribirODE(imsmanifest, manifest);
									break;
								}
							}
						}
					}
				}
				
			}
		}
		
	}
	
	
	private boolean eliminarCaracteresExtranosDeItems(Item[] items) {
		
		boolean escribirCambios=false;
		boolean escribirMasCambios=false;
		
		if(items==null) return false;
		
		for(int i=0; i<items.length; i++) {
			String titulo=items[i].getTitle();
			String tituloNuevo=eliminarCaracteresExtranos(titulo);
			
			if(!titulo.contentEquals(tituloNuevo)) {
				items[i].setTitle(tituloNuevo);
				escribirCambios=true;
			}
			
			if(items[i].getItem()!=null && items[i].getItem().length>0) {
				if(!escribirMasCambios)
					escribirMasCambios=eliminarCaracteresExtranosDeItems(items[i].getItem());
				else
					//Si ya hemos detectado que en algun sitio hay que hacer cambios
					//no modificamos el valor de escribirMasCambios
					eliminarCaracteresExtranosDeItems(items[i].getItem());
			}
		}
		return (escribirCambios||escribirMasCambios);
	}
	

  	/**
   	 * Metodo que se ejecuta al importar un ODE. Este metodo elimina los caracteres extraños del
   	 * titulo de un ODE y de las organizaciones modificando manifest
   	 * Recibe como parametro el path donde se encuentre el manifest (el localizador).
   	 */
	private void eliminarCaracteresExtranosDelTituloYOrganizaciones(String rutaManifest) throws Exception {

		File manifest = new File(rutaManifest, MANIFEST_NAME);
		Manifest imsmanifest = this.getScormDao().parsearODEEager(manifest);
		ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);

		boolean escribirCambios=false;
		boolean escribirMasCambios=false;

		//Modificamos titulo y descripcion
		String identifiadorManifest = manAgrega.getManifest().getIdentifier();
		Lom lom = manAgrega.obtenerLom(identifiadorManifest, null);
		
		if (lom != null) {
			LomAgrega lomAgrega = new LomAgrega(lom);
			
			String idioma = lomAgrega.getMetaMetadataAgrega().getIdioma();
			String titulo = lomAgrega.getGeneralAgrega().getTitulo(idioma);
			String tituloNuevo = eliminarCaracteresExtranos(titulo);
			
			Lom nuevoLom = null;
			
			if(!titulo.contentEquals(tituloNuevo)) {
				lomAgrega.getGeneralAgrega().setTitulo(tituloNuevo, idioma);
				nuevoLom = lomAgrega.getLom();
				manAgrega.setLom(identifiadorManifest, null, nuevoLom);
				escribirCambios=true;
			}
			
			List<String> descripciones = lomAgrega.getGeneralAgrega().getDescripcionesIdioma(idioma);
			for(int i=0; i<descripciones.size(); i++) {
				String descripNueva =  eliminarCaracteresExtranos(descripciones.get(i));

				if(!descripciones.get(i).contentEquals(descripNueva)) {
					lomAgrega.getGeneralAgrega().setDescripcion(i, descripNueva, idioma);
					if(nuevoLom==null)
						nuevoLom = lomAgrega.getLom();
					manAgrega.setLom(identifiadorManifest, null, nuevoLom);
					escribirCambios=true;
				}
			}
		} 
		
		//Modificamos organizaciones si las hay
		if(manAgrega.getManifest()!=null && manAgrega.getManifest().getOrganizations()!=null) {
			
			for(int o=0; o<manAgrega.getManifest().getOrganizations().getOrganizationCount(); o++) {
				
				Organization org = manAgrega.getManifest().getOrganizations().getOrganization(o);
				String titulo = org.getTitle();
				String tituloNuevo = eliminarCaracteresExtranos(titulo);
				
				if(!titulo.contentEquals(tituloNuevo)) {
					org.setTitle(tituloNuevo);
					escribirCambios=true;
				}
				escribirMasCambios=eliminarCaracteresExtranosDeItems(org.getItem());
			}
		}

		//Escribimos los cambios en disco
		if(escribirCambios||escribirMasCambios)
			this.getScormDao().escribirODE(imsmanifest, manifest);
	}
	
	
	private String eliminarCaracteresExtranos(String s) {
		String retorno = UtilesString.eliminaCaracteresProblematicosParaVisualizador(s);
		return retorno;
	}
	
	
	/**
	 * Crea un PIF en estado catalogado formato PIF (ZIP).
	 * 
	 * @param ficheroPIF
	 *            Objeto en formato PIF que se va a dejar en estado catalogado.
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @param comentarios
	 *            Comentarios que puede llevar cambiar el estado a catalogado.
	 * @param titulo
	 *            titulo del PIF
	 * @param idioma
	 * @param chequeaCuota
	 * @param id Identificador del ODE, si es null se asignará uno
	 * @return se retorna un VO con el error que se ha producido en el caso de
	 *         detectarse algun problema.
	 * @throws Exception
	 * 
	 */
	private ResultadoOperacionVO crearPIFAux(DataHandler ficheroPIF, String idUsuario, String comentarios, String titulo, String idioma, boolean chequeaCuota, String id, String estadoTransicion) throws Exception {

		String id_localizadorNP = "";
		try {

			//Eliminamos caracteres extraños del titulo
			titulo=eliminarCaracteresExtranos(titulo);	
			
			// utilizar un id generado por la clase de soporte que genera un uuid
			String idODE = id!=null&&!id.equals("")?id:es.pode.soporte.uuid.PodeUUIDGenerator.getOdeUUID(String.valueOf(System.currentTimeMillis()));
			// creacion
			this.getSrvLocalizadorService().crearLocalizadorNoPublicado(idUsuario, idODE);
			logger.debug("Creando PIF utilizando identificador[" + idODE + "], idUsuario[" + idUsuario+ "], comentarios[" + comentarios + "] y titulo[" + titulo + "]");

			SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
			LocalizadorVO localizadorNP = localizadorService.consultaLocalizador(idODE);
			id_localizadorNP = localizadorNP.getIdentificador();
			if(logger.isDebugEnabled())
				logger.debug("Encontramos localizador con identificador[" + localizadorNP.getIdentificador() + "] usuario["
						+ localizadorNP.getIdUsuario() + "] MEC[" + localizadorNP.getMec() + "] path["+ localizadorNP.getPath() + "] URL[" + localizadorNP.getUrl() + "]");

			// empezamos a descomprimir el pif y guardar lo que nos devuelve en un directorio temporal
			if(logger.isDebugEnabled())	logger.debug("Comenzamos a descomprimir el PIF y guardar en un dir temporal.");

			String pathtemp = "";			
			pathtemp = localizadorNP.getPath() + SrvPublicacionServiceImpl.getPropertyValue("carpeta.temporal") + "/" + titulo;
			String pathLocal = localizadorNP.getPath();
			int position = pathLocal.lastIndexOf("/");
			String pathPadre=pathLocal.substring(0, position);
			String pathTemporal=pathPadre+ "/" +SrvPublicacionServiceImpl.getPropertyValue("tempDir") + "/" + titulo ;//El .zip lo tiene el titulo
			logger.debug("El pathtemp es " + pathtemp + "El path donde se encuentra el zip es "+pathTemporal);
			//	File fileTemporal=new File(pathTemporal);
			File ficheroZip = new File(pathtemp);
			(ficheroZip.getParentFile()).mkdirs();
			ficheroZip.createNewFile();
			ficheroZip.deleteOnExit(); 
			FileOutputStream fos = new FileOutputStream(ficheroZip);
			ficheroPIF.writeTo(fos);
			String pathtempDestino = localizadorNP.getPath();
			File destinoTemporal = new File(pathtempDestino);
			logger.debug("El destinoTemporal es "+destinoTemporal);
			destinoTemporal.mkdirs();
			destinoTemporal.deleteOnExit();
			fos.close();
			// La comprobacion se delega en el empaquetador para hacer un 'importar inteligente'. El atributo
			// 'titulo' es el nombre del fichero proporcionado por la aplicación cliente
			String tipoFichero =null;
			try {
				AnalizaArchivoVO resultadoAnalisis = this.getSrvFachadaAgregarService().analizarArchivo(pathtemp); 
				tipoFichero = resultadoAnalisis.getTipoArchivo();
				if(logger.isDebugEnabled()) logger.debug("El fichero importado " + titulo + " es de tipo " + tipoFichero);
				// Si el tipo de fichero es comprimido, lo descomprimimos en la localización definitiva
				if(ConstantesAgrega.FICHERO.equals(tipoFichero)) {
					UtilesFicheros.copiar(ficheroZip, destinoTemporal);
				}else if(ConstantesAgrega.ODE_NO_VALIDO.equals(tipoFichero)) {
					logger.warn("El archivo " + titulo + " es un ODE no valido. Se rechaza su importacion");
					this.getSrvLocalizadorService().eliminarLocalizador(id_localizadorNP);
					String mensajeConcatenado="";
					for(int i=0;i<resultadoAnalisis.getMensajes().length;i++){
						if(i==0){
							mensajeConcatenado=resultadoAnalisis.getMensajes()[0]+";";
						}else{
							mensajeConcatenado=mensajeConcatenado+resultadoAnalisis.getMensajes()[i]+";";
						}
					}
					return new ResultadoOperacionVO(ERROR_DE_VALIDACION,mensajeConcatenado,idODE,0L);
				} else {
					getZipDao().descomprimir(ficheroZip.getPath(), pathtempDestino);
					if(logger.isDebugEnabled())
						logger.debug("Fiechro "+ficheroZip.getAbsolutePath()+" descomprimido en "+pathtempDestino);
				}
			} catch (Exception e) {
//				borramos todo lo que hayamos hecho hasta ahora
				this.getSrvLocalizadorService().eliminarLocalizador(id_localizadorNP);
				logger.error("Error analizando fichero [" + pathtemp + "], con nombre[" + titulo + "]", e);
				//Vamos a borrar la carpeta temporal
				logger.debug("Borramos la carpeta "+ficheroZip); //+" si existe y tambien el zip que  esta en el temporal "+fileTemporal);
				UtilesFicheros.eliminar(ficheroZip);
				return new ResultadoOperacionVO(ERROR_DESCOMPRIMIENDO_FICHERO_ZIP,
						I18nModuloPublicacion.getPropertyValueI18n(ERROR_DESCOMPRIMIENDO_FICHERO_ZIP),idODE,0L);
			} 

			String tipo="";
//			UNA VEZ DESCOMPRIMIDO, SACAMOS EL TIPO DE ODE Q SE ESTA IMPORTANDO: SCORM 1.2, IMS CP O SCORM 2004
			es.pode.soporte.validador.TipoOde tipoOde = new es.pode.soporte.validador.TipoOde();
			tipoOde.obtenerTipoOde(localizadorNP.getPath() + "/" +MANIFEST_NAME);
			tipo = tipoOde.getTipo();
			if(logger.isDebugEnabled())
				logger.debug("PUBLICACION ANTES DE ANALIZARARCHIVO, EL TIPO DE ODE ES : " + tipo);

			// En funcion del tipo de archivo hay que generar un imsmanifest.xml que recubra el contenido.
			if(ConstantesAgrega.FICHERO.equals(tipoFichero)) {
				this.getSrvFachadaAgregarService().generarManifest(localizadorNP.getIdentificador(), new String[]{titulo}, titulo, idioma);
			} else if(ConstantesAgrega.ARCHIVO.equals(tipoFichero)) {
				Collection<File> ficheros = FileUtils.listFiles(destinoTemporal, null, true);
				if(ficheros!=null ) {
					java.io.File[] ficherosArray = new java.io.File[ficheros.size()];
					if(logger.isDebugEnabled()) logger.debug("Se han encontrado " + ficheros.size() + "dentro del zip " + titulo);
					ficherosArray = ficheros.toArray(ficherosArray);
					String[] rutas = new String[ficheros.size()];
					String rutaAReemplazar = destinoTemporal.getPath().replaceAll("\\\\", "/");
					if(logger.isDebugEnabled()) logger.debug("Ruta a reemplazar con / : " + rutaAReemplazar);
					for(int i=0;i<ficherosArray.length;i++) {
						String ruta = ficherosArray[i].getPath().replaceAll(destinoTemporal.getPath(), "");
						if(ruta.startsWith("/")) ruta = ruta.substring(1);
						if(logger.isDebugEnabled()) logger.debug("Ruta " + ficherosArray[i].getPath() + " cambiada a " +ruta);
						rutas[i]=ruta;
					}
					getSrvFachadaAgregarService().generarManifest(localizadorNP.getIdentificador(), rutas, rutas[0], idioma);

				} else {
					logger.error("Se ha intentado importar un ZIP vacio");
				}
			} else if(ConstantesAgrega.RCP.equals(tipoFichero) && (tipo.equals(ConstantesAgrega.SCORM_04))) {
				if(logger.isDebugEnabled())
					logger.debug("PUBLICACION ENTRA EN OPCION RCP Y 2004!!!!");
				this.getSrvFachadaAgregarService().generarManifestRCP(localizadorNP.getIdentificador(), idioma);
			} // PARA ConstantesAgrega.CA no hace falta hacer nada
			
			String xsl="";
			// copiar los esquemas por si acaso no los trajese
			try {
				
				//aqui debemos chequear q tipo de ode es y hacer la conversion y copiar los esquemas correspondientes
				//Hacemos la transformación a scorm 2004	
				try {
					if (tipo !=null) {
						String oriTrans="";
						String destTrans="";
						TransformadorSaxonImpl transformador = new TransformadorSaxonImpl();					
						if (tipo.equals(ConstantesAgrega.SCORM_12)) {
							xsl=SrvPublicacionServiceImpl.getPropertyValue("xslt_scorm_12");
							if (logger.isDebugEnabled())
								logger.debug("EL ODE ES SCORM 12");

						}else if (tipo.equals(ConstantesAgrega.IMS_CP)) {
							xsl=SrvPublicacionServiceImpl.getPropertyValue("xslt_ims_cp");
							if (logger.isDebugEnabled())
								logger.debug("EL ODE ES IMSCP");
						}
						//Si xsl es "" es que no es ni scorm_12 ni ims_cp sino scorm2004
						if (!xsl.equals("")) {
							//TODO No transformamos submanifiestos... Tienen mismo tipo que manifiesto ppal
							if (logger.isDebugEnabled())
								logger.debug("EL VALOR DE XSL ES : " +  xsl);
							oriTrans = localizadorNP.getPath() +"/"+MANIFEST_NAME;
							destTrans = localizadorNP.getPath() + "/imsmanifestTransformado.xml";
							transformador.transformar(xsl, oriTrans, destTrans);
							// Falta renombrar el imsmanifestTransformado a imsmanifest pq asi está mal!!
							File fichOri = new File(oriTrans);
							/*
							 * Dependiendo del SO, no se puede renombrar un fichero a otro que ya existe.
							 * Borro el original antes de renombrar
							 */
							boolean deleteOri = fichOri.delete();
							if(!deleteOri) logger.warn("No se ha podido borrar " + fichOri);
							File fichDest = new File(destTrans);
							fichDest.renameTo(fichOri);

							if (logger.isDebugEnabled())logger.debug("HEMOS TRANSFORMADO DE " + oriTrans + " " + destTrans + " Y EL TIPOFICHERO ERA: " + tipoFichero);
//							como no era 2004, se ha hecho la transformacion y si era un rcp generamos el manifest
							if (logger.isDebugEnabled())
								logger.debug("LLAMAMOS A BORRARESQUEMAS SI SE HA HECHO TRANSFORMACION...... ");
							TratamientoODE.borrarEsquemas(pathtempDestino, tipo);

							if(ConstantesAgrega.RCP.equals(tipoFichero)) {
								//NECESITAMOS LOS ESQUEMAS DE 2004
								TratamientoODE.copiarEsquemas(pathtempDestino);
								if (logger.isDebugEnabled())logger.debug("HEMOS COPIADO LOS ESQUEMAS A " + pathtempDestino + "Y VAMOS A HACER LA VALIDACION LIGERA");
								this.getSrvValidadorService().obtenervalidacionLigera(pathtempDestino,	ConstantesAgrega.RCP);
								this.getSrvFachadaAgregarService().generarManifestRCP(localizadorNP.getIdentificador(), idioma);
								if (logger.isDebugEnabled())logger.debug("DESPUES DE generarManifestRCP");
							}
						}
					}
				}catch (Exception et) {
					logger.error("Error al realizar la transformación a Scorm 2004 ", et);
//					borramos todo lo que hayamos hecho hasta ahora
					this.getSrvLocalizadorService().eliminarLocalizador(id_localizadorNP);
					//Vamos a borrar la carpeta temporal
					logger.debug("Borramos la carpeta "+ficheroZip + "si existe."); //+"y tambien el zip que  esta en el temporal "+fileTemporal);
					UtilesFicheros.eliminar(ficheroZip);
					return new ResultadoOperacionVO(ERROR_EN_TRANSFORMACION, I18nModuloPublicacion.getPropertyValueI18n(ERROR_EN_TRANSFORMACION),idODE,0L);
				}
//				Una vez transformado el manifest a 2004, copiamos los esquemas
				TratamientoODE.copiarEsquemas(pathtempDestino);
				if (logger.isDebugEnabled()) logger.debug("HEMOS COPIADO LOS ESQUEMAS A " + pathtempDestino);

			} catch (Exception e1) {
				logger.error("No se pudieron copiar los esquemas al importar un pif: ", e1);
				// borramos todo lo que hayamos hecho hasta ahora
				this.getSrvLocalizadorService().eliminarLocalizador(id_localizadorNP);
				//Vamos a borrar la carpeta temporal
				logger.debug("Borramos la carpeta "+ficheroZip+" si existe.");  // y tambien el zip que  esta en el temporal "+fileTemporal);
				UtilesFicheros.eliminar(ficheroZip);
				return new ResultadoOperacionVO(ERROR_COPIANDO_ESQUEMAS, I18nModuloPublicacion.getPropertyValueI18n(ERROR_COPIANDO_ESQUEMAS),idODE,0L);
			}

			Long consumoODE = 0L;
			SrvLocalizadorService localizador = this.getSrvLocalizadorService();
			if (chequeaCuota)  // si atendemos al chequeo del espacio en disco usado.
			{
				//Antes de seguir vemos si el ODE no supera el espacio de su cuota. Ya tenemos descomprimido el ODE en disco y podemos ver si se ha pasado
				consumoODE = localizador.consultaEspacioLocalizador(id_localizadorNP);
				long cuotaConsumida = TratamientoCuota.calculaCuotaConsumidaUsuario(localizador, idUsuario, this.handleObtenODEsCreadosPorUsuario(idUsuario));
				long cuotaUsuario = 0;
				try{
				 cuotaUsuario = LdapUserDetailsUtils.getCuota().longValue();
				}catch (Exception e) {
					UsuarioVO user = getSrvAdminUsuariosService().obtenerDatosUsuario(idUsuario);
					cuotaUsuario = user.getCuota();
				}
				if(logger.isDebugEnabled()) logger.debug("El espacio consumido en disco por el ODE["+idODE+"] con localizador["+localizadorNP.getPath()+"]del usuario["+idUsuario+"] es de ["+consumoODE+"] bytes");
				if(logger.isDebugEnabled()) logger.debug("Los ODEs del usuario["+idUsuario+"] ocupan un total de ["+cuotaConsumida+"] y tiene una cuota de ["+cuotaUsuario+"] bytes");
				if ((cuotaConsumida + consumoODE) > cuotaUsuario) // nos hemos pasado de cuota, le damos la vuelta al asunto y salimos
				{
					logger.warn("Error creando ODE. La cuota del usuario["+idUsuario+"]["+cuotaUsuario+"]bytes se ha superado con el ODE["+idODE+"]["+consumoODE+"]bytes en ["+(cuotaConsumida - cuotaUsuario)+"]bytes");
					// borramos todo lo que hayamos hecho hasta ahora
					localizador.eliminarLocalizador(id_localizadorNP);
					//Vamos a borrar la carpeta temporal
					logger.debug("Borramos la carpeta "+ficheroZip+" si existe "); //y tambien el zip que  esta en el temporal "+fileTemporal);
					UtilesFicheros.eliminar(ficheroZip);
					return new ResultadoOperacionVO(ERROR_EXCEDER,I18nModuloPublicacion.getPropertyValueI18n(ERROR_EXCEDER) ,idODE,new Long(consumoODE));
				}
				logger.info("Creando ODE desde PIF con id["+idODE+"] tamanio["+consumoODE+"] para usuario["+idUsuario+"] con cuota consumida["+cuotaConsumida+"] y cuota total["+cuotaUsuario+"]");
			}					
						
			// validador
			SrvValidadorService validadorService = this.getSrvValidadorService();
			// reaalizamos una validacion ligera en lugar de carga ode.
			ValidaVO valid = validadorService.obtenervalidacionLigera(localizadorNP.getPath(), "CA");
			logger.info("Validando el ODE a importar[" + localizadorNP.getPath() + "] : Valido["
					+ valid.getEsValidoManifest().booleanValue() + "]");

			// la hora de testear cuidado con el validador
			if (valid.getEsValidoManifest().booleanValue()) {
				// Extraemos el manifest del ODE.
				File extraeSubmanifest = new File(localizadorNP.getPath(), MANIFEST_NAME);
				if (logger.isDebugEnabled()) logger.debug("ES VALIDO Y VA A HACER EL PARSEO");
				Manifest imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);
				if(logger.isDebugEnabled()) logger.debug("Validado el ODE [" + localizadorNP.getPath() + "]!!");

				String resultadoVocab = this.comprobarVocabulariosYFechas(imsmanifest);
				//Metemos la información de validación ligera 
				if ((valid.getResultadoValidacion()!=null) && (!valid.getResultadoValidacion().equals(""))) {
					resultadoVocab = new StringBuffer(resultadoVocab).append(valid.getResultadoValidacion()).toString();					
				}
				//Metemos la información sobre el tipo de transformación que se ha realizado
				if (!xsl.equals("") && tipo!=null && !tipo.equals("")) {
					if (logger.isDebugEnabled())logger.debug("SE HA REALIZADO LA TRANSFORMACIÓN DE " + tipo + " A SCORM2004 idioma " + idioma);
					resultadoVocab = new StringBuffer(resultadoVocab).append(I18nModuloPublicacion.getPropertyValueI18n("publicacion.origenTransformacion", idioma))
					.append(tipo).append(" ").append(I18nModuloPublicacion.getPropertyValueI18n("publicacion.transforma2004", idioma)).toString();
					if (logger.isDebugEnabled())logger.debug("SACAMOS EL VALOR DE RESULTADO de resultadoVocab " + resultadoVocab);					
				}
				// Por ultimo, en caso de que todo haya ido bien, tenemos que recubrir el MEC y el identificador del 
				// manifest que tenga este ODE con el identificador UUID que utilizamos en la plataforma, ya que este
				// ODE esta en estado CREADO => esta en el taller. Para ello vamos a utilizar el mismo metodo que ya 
				// existe y hace todo esto con el identificador MEC en el momento de publicar, pero le vamos a pasar
				// el identificador de taller (el UUID)

				ScormDao scorm = (ScormDao) this.getScormDao();
				TratamientoODE.cambiaUUIDxMEC(idODE, localizadorNP.getPath(), imsmanifest, ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.CATALOGO_AGREGA), scorm); 
				// utilizamos el catalogo agrega imsmanifest
				// si el ode tiene lomes el título lo cambiamos al que trajese con el lomes

				// manifest en la posicion exacta
				ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
				String identifiadorManifest = manAgrega.getManifest().getIdentifier();
				Lom lom = manAgrega.obtenerLom(identifiadorManifest, null);
				if (lom != null) {
					LomAgrega lomAgrega = new LomAgrega(lom);
					idioma=lomAgrega.getMetaMetadataAgrega().getIdioma();
					titulo = lomAgrega.getGeneralAgrega().getTitulo(idioma);
				} else {
					logger.warn("El Lom del manifest " + idODE+ " no tiene objeto general, el nombre del fichero será el titulo del ODE");
				}	
				
				//Eliminamos caracteres extraños de los recursos y del titulo para evitar problemas
				//y para que el manifest del ODE pueda validarse
				eliminarCaracteresExtranosDeRecursos(idODE);				
				eliminarCaracteresExtranosDelTituloYOrganizaciones(localizadorNP.getPath());
				
				// Creamos la transicion
				EstadoDao estadoDao = this.getEstadoDao();
				TransicionDao transicionDao = this.getTransicionDao();
				Date fecha = new Date(System.currentTimeMillis());
				
				String estadoFinal = null;
				if (estadoTransicion!=null)
					estadoFinal=estadoTransicion;
				else
					estadoFinal=CREACION;
						
				transicionDao.create(DateManager.dateToCalendar(fecha), comentarios, idODE, idUsuario, titulo, idUsuario,// el usuario de creacion es el mismo que me pasan
						fecha.getTime(),false, null, null, estadoDao.obtenEstadoPorNombre(estadoFinal));
				//Vamos a borrar la carpeta temporal
				//	logger.debug("Borramos la carpeta "+ficheroZip+" si existe y tambien el zip que  esta en el temporal "+fileTemporal);
				UtilesFicheros.eliminar(ficheroZip);
				//	UtilesFicheros.eliminar(fileTemporal);
				if(resultadoVocab.equals(""))
					return new ResultadoOperacionVO(SIN_ERRORES, I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES),idODE,consumoODE);
				return new ResultadoOperacionVO(VOCAB_BORRADOS, resultadoVocab,idODE,consumoODE);
			}// si no es válido rollback
			// borramos todo lo que hayamos hecho hasta ahora
			this.getSrvLocalizadorService().eliminarLocalizador(id_localizadorNP);
			logger.warn("Atención: no se ha validado el ODE [" + localizadorNP.getPath()
					+ "]correctamente. No se ha creado el Pif, error: " + valid.getResultadoValidacion() + "]");
			//Vamos a borrar la carpeta temporal
			//	logger.debug("Borramos la carpeta "+ficheroZip+" si existe y tambien el zip que  esta en el temporal "+fileTemporal);
			UtilesFicheros.eliminar(ficheroZip);
			//UtilesFicheros.eliminar(fileTemporal);
			return new ResultadoOperacionVO(SrvPublicacionServiceImpl.ERROR_DE_VALIDACION, valid.getResultadoValidacion(),idODE,0L);
		}// try
		catch (Exception e) {
			logger.error("Fallo creando PIF con fichero[" + titulo + "] idusuario[" + idUsuario + "] comentarios["+ comentarios + "] titulo[" + titulo + "] )", e);
			// borramos todo lo que hayamos hecho hasta ahora
			this.getSrvLocalizadorService().eliminarLocalizador(id_localizadorNP);
			throw new Exception(e);
		}
	}
	
	/**
	 * Este metodo nos da las transiciones de los ODEs compartidos
	 * 
	 * @return se retorna el array con las transiciones ordenadas por fecha de
	 *         los ODEs en estado compartido.
	 * @throws Exception
	 * 
	 */
	protected TransicionAutorVO[] handleObtenODESCompartidos() throws Exception {
		TransicionDao transicion = this.getTransicionDao();
		EstadoCompartidoCriteria criterio=new EstadoCompartidoCriteria();
		EstadoCompartidoCriteria criterio2=new EstadoCompartidoCriteria();
		criterio2.setCompartido(true);
		criterio2.setEstadoTransitado(null);
		criterio2.setEstadoActual(this.getEstadoDao().obtenEstadoPorNombre(CREACION));
		criterio.setCompartido(true);
		criterio.setEstadoTransitado(null);
		criterio.setEstadoActual(this.getEstadoDao().obtenEstadoPorNombre(RECHAZADO));

		List transiciones = transicion.buscarOdesPorEstadoCompartido(TransicionDao.TRANSFORM_TRANSICIONAUTORVO, criterio2);
		List transiciones2=transicion.buscarOdesPorEstadoCompartido(TransicionDao.TRANSFORM_TRANSICIONAUTORVO, criterio);
		transiciones.addAll(transiciones2);
		logger.info("Obtenidos " + transiciones.size() + " odes en estado "	+ SrvPublicacionServiceImpl.CREACION+" y "+SrvPublicacionServiceImpl.RECHAZADO);
		return (TransicionAutorVO[]) transiciones.toArray(new TransicionAutorVO[transiciones.size()]);
	} 

	/**
	 * Metodo para descompartir un ODE que esta compartido
	 * 
	 * @param idODE
	 *            El identifcador que se quiere descompartir.
	 * @return se retorna un VO con el error que se ha producido en el caso de
	 *         detectarse algun problema.
	 * @throws Exception
	 * 
	 */
	protected ResultadoOperacionVO handleCompartirODE(String idODE) throws Exception {
		try {
			//logger.debug("Hemos entrado en compartirODE con identificador:["+idODE+"]");
			EstadoVO actual=this.obtenEstadoPorIdODE(idODE, LdapUserDetailsUtils.getIdioma());
			logger.debug("El estadoVO que obtenemos con ese ODE es: clave ["+actual.getClave()+"], nombre["+actual.getClave());
			logger.debug("Las transiciones valida son["+SrvPublicacionServiceImpl.CREACION+" y " +SrvPublicacionServiceImpl.RECHAZADO);
			if(((actual.getClave()).equals(SrvPublicacionServiceImpl.CREACION))||((actual.getClave()).equals(SrvPublicacionServiceImpl.RECHAZADO))){
				Transicion transicion = handleObtenerUltimaTransicion(idODE);;
				transicion.setCompartido(true);
				this.getTransicionDao().update(transicion);
				logger.info("Se ha compartido bien el ODE con identificador" + idODE + "]");
				return new ResultadoOperacionVO(SIN_ERRORES, I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES),idODE,0L);
			}
			logger.warn("El ODE para compartir con identificador ["+ idODE+"] no esta en estado [" + SrvPublicacionServiceImpl.CREACION + "] o [" + SrvPublicacionServiceImpl.RECHAZADO + "] esta en estado [" + actual.getClave() + "]  )");
			return new ResultadoOperacionVO(NO_ESTADO_VALIDO, I18nModuloPublicacion.getPropertyValueI18n(NO_ESTADO_VALIDO),idODE,0L);
		} catch (RuntimeException e) {
			logger.error("Fallo compartiendo ODE con identificador[" + idODE + "] )", e);
			throw new Exception(e);
		}
	}

	/**
	 * Metodo para eliminar de compartidos un ODE que esta compartido
	 * 
	 * @param idODE
	 *            El identifcador que se quiere compartir.
	 * @return se retorna un VO con el error que se ha producido en el caso de
	 *         detectarse algun problema.
	 * @throws Exception
	 * 
	 */
	protected ResultadoOperacionVO handleDescompartirODE(String idODE) throws Exception {
		try {
			//logger.debug("Hemos entrado en descompartirODE con identificador:["+idODE+"]");
			EstadoVO actual=this.obtenEstadoPorIdODE(idODE, LdapUserDetailsUtils.getIdioma());
			logger.debug("El estadoVO que obtenemos con ese ODE es: clave ["+actual.getClave()+"], nombre["+actual.getClave());
			logger.debug("Las transiciones valida son["+SrvPublicacionServiceImpl.CREACION+" y " +SrvPublicacionServiceImpl.RECHAZADO);
			if(((actual.getClave()).equals(SrvPublicacionServiceImpl.CREACION))||((actual.getClave()).equals(SrvPublicacionServiceImpl.RECHAZADO))){
				Transicion transicion = handleObtenerUltimaTransicion(idODE);
				transicion.setCompartido(false);
				this.getTransicionDao().update(transicion);
				logger.info("Se ha descompartido bien el ODE con identificador" + idODE + "]");
				return new ResultadoOperacionVO(SIN_ERRORES, I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES),idODE,0L);
			}
			logger.error("El ODE para descompartir con identificador ["+ idODE+"] no esta en estado [" + SrvPublicacionServiceImpl.CREACION + "] o [" + SrvPublicacionServiceImpl.RECHAZADO + "] esta en estado ["
					+ actual.getClave() + "]  )");
			return new ResultadoOperacionVO(NO_ESTADO_VALIDO, I18nModuloPublicacion.getPropertyValueI18n(NO_ESTADO_VALIDO),idODE,0L);
		} catch (RuntimeException e) {
			logger.error("Fallo descompartiendo ODE con identificador[" + idODE + "] )", e);
			throw new Exception(e);
		}
	}

	/**
	 * Metodo para crear ode desde una URL dada.
	 * 
	 * @param url
	 *            URL donde está el ode
	 * @param idUsuario
	 * 				Identificador del usuario
	 * @param comentarios
	 * 				Comentarios introducidos
	 * @param titulo
	 * 				Titulo del ode
	 * @param idioma	
	 * 				Idioma del ode
	 * @param idiomaDestinatario
	 * 				Idioma del destinatario del ode
	 * @param tipo
	 * 				Tipo de recurso
	 * @return se retorna un VO con el error que se ha producido en el caso de
	 *         detectarse algun problema.
	 * @throws Exception
	 * 
	 */
	protected ResultadoOperacionVO handleCrearDesdeURL(String url,
			String idUsuario, String comentarios, String titulo, String idioma, String idiomaDestinatario, String tipo) throws Exception {
		String id_localizadorNP = "";
		try {
			// utilizar un id generado por la clase de soporte que genera un uuid
			String idODE = es.pode.soporte.uuid.PodeUUIDGenerator.getOdeUUID(String.valueOf(System.currentTimeMillis()));
			// creacion
			this.getSrvLocalizadorService().crearLocalizadorNoPublicado(idUsuario, idODE);
		//	logger.info("Creando ODE utilizando url"+url+"], identificador[" + idODE + "], idUsuario[" + idUsuario+ "], comentarios[" + comentarios + "] y titulo[" + titulo + "]");
			SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
			LocalizadorVO localizadorNP = localizadorService.consultaLocalizador(idODE);
			id_localizadorNP = localizadorNP.getIdentificador();
			if(logger.isDebugEnabled()) logger.debug("Encontramos localizador con identificador[" + id_localizadorNP + "] usuario["
					+ localizadorNP.getIdUsuario() + "] MEC[" + localizadorNP.getMec() + "] path[" + localizadorNP.getPath() + "] URL[" + localizadorNP.getUrl() + "]");
			this.getSrvFachadaAgregarService().generarManifestLom(id_localizadorNP, null, url, idioma, titulo, comentarios,idiomaDestinatario, tipo);
			Long consumoODE = 0L;
			//Antes de seguir vemos si el ODE no supera el espacio de su cuota. Ya tenemos descomprimido el ODE en disco y podemos ver si se ha pasado
			consumoODE = this.getSrvLocalizadorService().consultaEspacioLocalizador(id_localizadorNP);
			long cuotaConsumida = TratamientoCuota.calculaCuotaConsumidaUsuario(localizadorService, idUsuario, this.handleObtenODEsCreadosPorUsuario(idUsuario));
			long cuotaUsuario = LdapUserDetailsUtils.getCuota().longValue();
			if(logger.isDebugEnabled()) logger.debug("El espacio consumido en disco por el ODE["+idODE+"] con localizador["+localizadorNP.getPath()+"]del usuario["+idUsuario+"] es de ["+consumoODE+"] bytes");
			if(logger.isDebugEnabled()) logger.debug("Los ODEs del usuario["+idUsuario+"] ocupan un total de ["+cuotaConsumida+"] y tiene una cuota de ["+cuotaUsuario+"] bytes");
			if ((cuotaConsumida + consumoODE) > cuotaUsuario) // nos hemos pasado de cuota, le damos la vuelta al asunto y salimos
			{
				logger.error("Error creando ODE. La cuota del usuario["+idUsuario+"]["+cuotaUsuario+"]bytes se ha superado con el ODE["+idODE+"]["+consumoODE+"]bytes en ["+(cuotaConsumida - cuotaUsuario)+"]bytes");
				// borramos todo lo que hayamos hecho hasta ahora
				this.getSrvLocalizadorService().eliminarLocalizador(id_localizadorNP);
				return new ResultadoOperacionVO(ERROR_EXCEDER,I18nModuloPublicacion.getPropertyValueI18n(ERROR_EXCEDER) ,idODE,Long.valueOf(consumoODE));
			}
			logger.info("Creando ODE desde PIF con id["+idODE+"] tamanio["+consumoODE+"] para usuario["+idUsuario+"] con cuota consumida["+cuotaConsumida+"] y cuota total["+cuotaUsuario+"]");
			// validador
			SrvValidadorService validadorService = this.getSrvValidadorService();
			// reaalizamos una validacion ligera en lugar de carga ode.
			ValidaVO valid = validadorService.obtenervalidacionLigera(localizadorNP.getPath(), "CA");
			logger.info("Validando el ODE a importar[" + localizadorNP.getPath() + "] : Valido["+ valid.getEsValidoManifest().booleanValue() + "]");
			// la hora de testear cuidado con el validador
			if (valid.getEsValidoManifest().booleanValue()) {
				if(logger.isDebugEnabled()) logger.debug("Validado el ODE [" + localizadorNP.getPath() + "]!!");
				// Creamos la transicion
				EstadoDao estadoDao = this.getEstadoDao();
				TransicionDao transicionDao = this.getTransicionDao();
				Date fecha = new Date(System.currentTimeMillis());
				transicionDao.create(DateManager.dateToCalendar(fecha), comentarios, idODE, idUsuario, titulo, idUsuario,// el usuario de creacion es el mismo que me pasan
						Long.valueOf(fecha.getTime()),false, null, null, estadoDao.obtenEstadoPorNombre(CREACION));
				return new ResultadoOperacionVO(SIN_ERRORES, I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES),idODE,consumoODE);
			}// si no es válido rollback
			// borramos todo lo que hayamos hecho hasta ahora
			this.getSrvLocalizadorService().eliminarLocalizador(id_localizadorNP);
			logger.error("Atención: no se ha validado el ODE [" + localizadorNP.getPath()+ "]correctamente. No se ha creado el Pif, error: " + valid.getResultadoValidacion() + "]");
			return new ResultadoOperacionVO(SrvPublicacionServiceImpl.ERROR_DE_VALIDACION, valid.getResultadoValidacion(),idODE,Long.valueOf(0));
		}// try
		catch (Exception e) {
			logger.error("Fallo creando ODE con url[" + url +"] titulo["+titulo+ "] idusuario[" + idUsuario + "] comentarios["+ comentarios+"] )", e);
			// borramos todo lo que hayamos hecho hasta ahora
			this.getSrvLocalizadorService().eliminarLocalizador(id_localizadorNP);
			throw new Exception(e);
		}
	}

	/**
	 * 
	 * @param manifest
	 * @return
	 * @throws Exception
	 */
	private String comprobarVocabulariosYFechas(Manifest manifest) throws Exception{
		String vocabControlados = SrvPublicacionServiceImpl.getPropertyValue("vocabulariosControlados");
		String[] vocabularios = vocabControlados.split(",");

		SrvVocabulariosControladosService vocabSrv= this.getSrvVocabulariosControladosService();
		VocabularioVO[] vocabVO = vocabSrv.obtenerCombos(vocabularios, "en");

		/********vocabularios de general********/
		VocabularioVO idiomaV = vocabVO[0];//1.3
		ArrayList<String> aIdiomaV = this.obtenValoresVocabulario(idiomaV);

		VocabularioVO estructuraV = vocabVO[1];//1.7
		ArrayList<String> aEstructuraV = this.obtenValoresVocabulario(estructuraV);

		VocabularioVO nivelAgregacionV = vocabVO[2];//1.8
		ArrayList<String> aNivelAgregacionV = this.obtenValoresVocabulario(nivelAgregacionV);

		/********vocabularios de ciclo de vida********/
		VocabularioVO estadoV = vocabVO[3];//2.2
		ArrayList<String> aEstadoV = this.obtenValoresVocabulario(estadoV);

		VocabularioVO tipoContribucionV = vocabVO[4];//2.3.1
		ArrayList<String> aTipoContribucionV = this.obtenValoresVocabulario(tipoContribucionV);

		/********vocabularios de meta-metadatos********/
		VocabularioVO tipoContribucionMetaV = vocabVO[5];//3.2.1
		ArrayList<String> aTipoContribucionMetaV = this.obtenValoresVocabulario(tipoContribucionMetaV);

		VocabularioVO idiomaCatalogacionV = vocabVO[6];//3.4
		ArrayList<String> aIdiomaCatalogacionV = this.obtenValoresVocabulario(idiomaCatalogacionV);

		/********vocabularios de tecnica********/
		VocabularioVO formatoV = vocabVO[7];//4.1
		ArrayList<String> aFormatoV = this.obtenValoresVocabulario(formatoV);

		VocabularioVO tipoRequisitoV = vocabVO[8];//4.4.1.1
		ArrayList<String> aTipoRequisitoV = this.obtenValoresVocabulario(tipoRequisitoV);

		VocabularioVO nombreRequisitoV = vocabVO[9];//4.4.1.2
		ArrayList<String> aNombreRequisitosV = this.obtenValoresVocabulario(nombreRequisitoV);

		/********vocabularios de uso educativo********/
		VocabularioVO tipoInteractividadV = vocabVO[10];//5.1
		ArrayList<String> aTipoInteractividadV = this.obtenValoresVocabulario(tipoInteractividadV);

		VocabularioVO tipoRecursoV = vocabVO[11];//5.2
		ArrayList<String> aTipoRecursoV = this.obtenValoresVocabulario(tipoRecursoV);

		VocabularioVO nivelInteractividadV = vocabVO[12];//5.3
		ArrayList<String> aNivelInteractividadV = this.obtenValoresVocabulario(nivelInteractividadV);

		VocabularioVO densidadSemanticaV = vocabVO[13];//5.4
		ArrayList<String> aDensidadSemanticaV = this.obtenValoresVocabulario(densidadSemanticaV);

		VocabularioVO destinatarioV = vocabVO[14];//5.5
		ArrayList<String> aDestinatarioV = this.obtenValoresVocabulario(destinatarioV);

		VocabularioVO contextoV = vocabVO[15];//5.6
		ArrayList<String> aContextoV = this.obtenValoresVocabulario(contextoV);

		VocabularioVO dificultadV = vocabVO[16];//5.8
		ArrayList<String> aDificultadV = this.obtenValoresVocabulario(dificultadV);

		VocabularioVO idiomaDestinatarioV = vocabVO[17];//5.11
		ArrayList<String> aIdiomaDestinatarioV = this.obtenValoresVocabulario(idiomaDestinatarioV);

		VocabularioVO procesoCognitivoV = vocabVO[18];//5.12
		ArrayList<String> aProcesoCognitivoV = this.obtenValoresVocabulario(procesoCognitivoV);

		/********vocabularios de derechos********/
		VocabularioVO costeV = vocabVO[19];//6.1
		ArrayList<String> aCosteV = this.obtenValoresVocabulario(costeV);

		VocabularioVO derechosAutorV = vocabVO[20];//6.2
		ArrayList<String> aDerechosAutorV = this.obtenValoresVocabulario(derechosAutorV);

		VocabularioVO tipoAccesoV = vocabVO[21];//6.4.1
		ArrayList<String> aTipoAccesoV = this.obtenValoresVocabulario(tipoAccesoV);

		/********vocabularios de relacion********/
		VocabularioVO tipoRelacionV = vocabVO[22];//7.1
		ArrayList<String> aTipoRelacionV = this.obtenValoresVocabulario(tipoRelacionV);

		/********vocabularios de clasificacion********/
		VocabularioVO propositoV = vocabVO[23];//9.1
		ArrayList<String> aPropositoV = this.obtenValoresVocabulario(propositoV);

		StringBuffer resultado = new StringBuffer("");
		ManifestAgrega manifestAgrega = new ManifestAgrega(manifest);
		HashMap lomesMap = manifestAgrega.recuperarLomesMap();
		Set padreLomes = lomesMap.keySet();
		Object[] padresLomesArray= padreLomes.toArray();
		for(int i=0; i< padresLomesArray.length;i++){
			boolean haCambiado = false;
			String idPadre = (String) padresLomesArray[i];
			Lom lomes = (Lom) lomesMap.get(padresLomesArray[i]);
			LomAgrega lomAgrega = new LomAgrega(lomes);

//			/**********************General**********************/
			logger.debug("Comprobando campos de la categoría General");
			GeneralAgrega generalAgrega = lomAgrega.getGeneralAgrega();
			List idiomas = generalAgrega.getIdiomasAv();
			boolean cambioIdiomas = false;
			ArrayList<String> new_Idiomas= new ArrayList<String>();
			for(int k = 0; k < idiomas.size();k++){
				String idioma = (String) idiomas.get(k);
				if(idioma !=null && !aIdiomaV.contains(idioma)){
					logger.debug("Valor erroneo de idioma: " + idioma + ". Borramos valor.");
					resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
					.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.idioma"))
					.append(idioma).append(";");
					haCambiado = true;
					cambioIdiomas = true;
				}
				else{
					new_Idiomas.add(idioma);
				}
			}
			if(cambioIdiomas)
				generalAgrega.setIdiomasAv(new_Idiomas);

			String estructura = generalAgrega.getEstructuraAv();
			if(estructura !=null && !aEstructuraV.contains(estructura)){
				logger.info("Valor erroneo de estructura: " + estructura +". Borramos valor.");
				resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
				.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.estructura"))
				.append(estructura).append(";");
				generalAgrega.setEstructuraAv(null);
				haCambiado = true;			
			}

			String nivelAgregacion = generalAgrega.getNivelDeAgregacionAv();
			if(nivelAgregacion !=null && !aNivelAgregacionV.contains(nivelAgregacion)){
				logger.info("Valor erroneo de nivel de agregacion: " + nivelAgregacion +". Borramos valor.");
				resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
				.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.nivelAgregacion"))
				.append(nivelAgregacion).append(";");
				generalAgrega.setNivelDeAgregacionAv(null);
				haCambiado = true;
			}

//			/**********************Ciclo de vida**********************/
			logger.debug("Comprobando campos de la categoría Ciclo de vida");
			LifeCycleAgrega cicloAgrega = lomAgrega.getLifeCycleAgrega();
			String estado = cicloAgrega.getEstatusAv();
			if(estado !=null && !aEstadoV.contains(estado)){
				logger.info("Valor erroneo de estado: " + estado +". Borramos valor.");
				resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
				.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.estado"))
				.append(estado).append(";");
				cicloAgrega.setEstatusAv(null);
				haCambiado = true;
			}

			ArrayList<ContribucionAgrega> contribuciones = cicloAgrega.getContribucionesAv();
			ArrayList<ContribucionAgrega> new_contribuciones= new ArrayList<ContribucionAgrega>();
			boolean cambioContrib = false;
			for(int j = 0; j < contribuciones.size(); j++){
				ContribucionAgrega contribucion = contribuciones.get(j);
				String tipo = contribucion.getTipo();
				if(tipo !=null && !aTipoContribucionV.contains(tipo)){
					logger.info("Valor erroneo de tipo de contribucion: " + tipo + ". Borramos valor.");
					resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
					.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.tipoContribCiclo"))
					.append(tipo).append(";");
					contribucion.setTipo(null);
					haCambiado = true;
					cambioContrib = true;
				}

				FechaAgrega fecha = contribucion.getFecha();
				if(fecha!=null && fecha.getFecha()!=null){
					String sFecha = fecha.getFecha();
					boolean correcta = this.comprobarFecha(sFecha);
					if(!correcta){
						logger.info("Valor erroneo de fecha: " + sFecha + ". Borramos valor.");
						resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
						.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.fechaCiclo"))
						.append(sFecha).append(";");
						fecha.setFecha(null);
						contribucion.setTipo(null);
						haCambiado = true;
						cambioContrib = true;
					}
				}

				if((contribucion.getEntidades()!=null && contribucion.getEntidades().size()>0)||
						(contribucion.getTipo()!=null && !contribucion.getTipo().equals("")) ||
						(contribucion.getFecha()!=null && contribucion.getFecha().getFecha()!=null && !contribucion.getFecha().getFecha().equals("")) ||
						(contribucion.getFecha()!=null && contribucion.getFecha().getDescripciones()!=null && contribucion.getFecha().getDescripciones().size()>0)){
					new_contribuciones.add(contribucion);
				}
			}
			if(cambioContrib){
				cicloAgrega.setContribucionesAv(new_contribuciones);
			}

//			/**********************Meta-metadatos**********************/
			logger.debug("Comprobando campos de la categoría Meta-metadatos");
			MetaMetadataAgrega metadatosAgrega =lomAgrega.getMetaMetadataAgrega();
			String idiomaCatalogacion = metadatosAgrega.getIdiomaAv();
			if(idiomaCatalogacion !=null && !aIdiomaCatalogacionV.contains(idiomaCatalogacion)){
				logger.info("Valor erroneo de idioma de catalogacion: " + idiomaCatalogacion + ". Borramos valor.");
				resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
				.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.idiomaCatalogacion"))
				.append(idiomaCatalogacion).append(";");
				metadatosAgrega.setIdiomasAv(null);
				haCambiado = true;
			}

			ArrayList<ContribucionAgrega> contribucionesMeta = metadatosAgrega.getContribucionesAv();
			ArrayList<ContribucionAgrega> new_contribucionesMeta= new ArrayList<ContribucionAgrega>();
			boolean cambioContribMeta = false;
			for(int j = 0; j < contribucionesMeta.size(); j++){
				ContribucionAgrega contribucionMeta = contribucionesMeta.get(j);
				String tipo = contribucionMeta.getTipo();
				if(tipo !=null && !aTipoContribucionMetaV.contains(tipo)){
					logger.info("Valor erroneo de tipo de contribucion: " + tipo + ". Borramos valor.");
					resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
					.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.tipoContribMeta"))
					.append(tipo).append(";");
					contribucionMeta.setTipo(null);
					haCambiado = true;
					cambioContribMeta = true;
				}

				FechaAgrega fecha = contribucionMeta.getFecha();
				if(fecha!=null && fecha.getFecha()!=null){
					String sFecha = fecha.getFecha();
					boolean correcta = this.comprobarFecha(sFecha);
					if(!correcta){
						logger.info("Valor erroneo de fecha: " + sFecha + ". Borramos valor.");
						resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
						.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.fechaMeta"))
						.append(sFecha).append(";");
						fecha.setFecha(null);
						contribucionMeta.setTipo(null);
						haCambiado = true;
						cambioContribMeta = true;
					}
				}
				if((contribucionMeta.getEntidades()!=null && contribucionMeta.getEntidades().size()>0)||
						(contribucionMeta.getTipo()!=null && !contribucionMeta.getTipo().equals("")) ||
						(contribucionMeta.getFecha()!=null && contribucionMeta.getFecha().getFecha()!=null && !contribucionMeta.getFecha().getFecha().equals("")) ||
						(contribucionMeta.getFecha()!=null && contribucionMeta.getFecha().getDescripciones()!=null && contribucionMeta.getFecha().getDescripciones().size()>0)){
					new_contribucionesMeta.add(contribucionMeta);
				}
			}
			if(cambioContribMeta){
				cicloAgrega.setContribucionesAv(new_contribucionesMeta);
			}			

			/**********************Tecnica**********************/
			logger.debug("Comprobando campos de la categoría Tecnica");
			TechnicalAgrega tecnicaAgrega = lomAgrega.getTechnicalAgrega();
			ArrayList  formatos = tecnicaAgrega.getFormatosAv();
			boolean cambioFormatos = false;
			ArrayList<String> new_formatos = new ArrayList<String>();
			for(int j = 0; j < formatos.size();j++){
				String formato = (String) formatos.get(j);
				if(formato !=null && !aFormatoV.contains(formato)){
					logger.info("Valor erroneo de formato: " + formato + ". Borramos valor.");
					resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
					.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.formato"))
					.append(formato).append(";");
					haCambiado = true;
					cambioFormatos = true;
				}
				else{
					new_formatos.add(formato);
				}
			}
			if(cambioFormatos)
				tecnicaAgrega.setFormatosAv(new_formatos);
			ArrayList<ArrayList<RequisitoAgrega>> requisitos = tecnicaAgrega.getRequisitosAv();
			boolean cambioRequisitos = false;
			for(int j = 0; j < requisitos.size();j++){
				ArrayList<RequisitoAgrega> orComposites = requisitos.get(j);
				boolean cambioOrComposites = true;
				for(int k = 0; k < orComposites.size(); k++){
					RequisitoAgrega requisito = orComposites.get(k);
					String tipo = requisito.getTipo();
					boolean cambioRequisito =false;
					if(tipo !=null && !aTipoRequisitoV.contains(tipo)){
						logger.info("Valor erroneo de tipo requisito: " + tipo + ". Borramos valor.");
						resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
						.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.tipoRequisito"))
						.append(tipo).append(";");
						requisito.setTipo(null);
						haCambiado = true;
						cambioRequisitos = true;
						cambioOrComposites =true;
						cambioRequisito= true;
					}

					String nombre = requisito.getNombre();
					if(nombre !=null && !aNombreRequisitosV.contains(nombre)){
						logger.info("Valor erroneo de nombre de requisito: " + nombre + ". Borramos valor.");
						resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
						.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.nombreRequisito"))
						.append(nombre).append(";");
						requisito.setNombre(null);
						haCambiado = true;
						cambioRequisitos = true;
						cambioOrComposites = true;
						cambioRequisito = true;
					}
					if(cambioRequisito)
						orComposites.set(k, requisito);
				}
				if(cambioOrComposites)
					requisitos.set(j, orComposites);
			}
			if(cambioRequisitos)
				tecnicaAgrega.setRequisitosAv(requisitos);

			/**********************Uso educativo**********************/
			logger.debug("Comprobando campos de la categoría Uso educativo");
			ArrayList<EducationalAgrega> usosEduAgrega = lomAgrega.getEducationalsAgrega();
			ArrayList<EducationalAgrega> new_usosEducativos= new ArrayList<EducationalAgrega>();
			boolean cambioUsosEdu = false;
			for(int j = 0; j < usosEduAgrega.size();j++){
				EducationalAgrega usoEducativo = usosEduAgrega.get(j);
				//***********tipoInteractividad***********
				String tipoInteractividad = usoEducativo.getTipoDeInteractividadAv();
				if(tipoInteractividad !=null && !aTipoInteractividadV.contains(tipoInteractividad)){
					logger.info("Valor erroneo de tipo de interactividad: " + tipoInteractividad + ". Borramos valor.");
					resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
					.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.tipoInteractividad"))
					.append(tipoInteractividad).append(";");
					usoEducativo.setTipoDeInteractividadAv(null);
					haCambiado = true;
					cambioUsosEdu=true;
				}
				//***********tipoRecurso***********							
				ArrayList<String> tipoRecurso = usoEducativo.getTiposDeRecursoAv();
				boolean cambioTipoRecurso = false;
				ArrayList<String> new_tipoRecurso = new ArrayList<String>();
				for(int k = 0; k < tipoRecurso.size();k++){
					String recurso = tipoRecurso.get(k);
					if(recurso !=null && !aTipoRecursoV.contains(recurso)){
						logger.info("Valor erroneo de tipo de recurso: " + recurso + ". Borramos valor.");
						resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
						.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.tipoRecurso"))
						.append(recurso).append(";");
						haCambiado = true;
						cambioTipoRecurso = true;
						cambioUsosEdu=true;
					}
					else{
						new_tipoRecurso.add(recurso);
					}
				}
				if(cambioTipoRecurso)
					usoEducativo.setTiposDeRecursoAv(new_tipoRecurso);
				//***********nivelInteractividad***********
				String nivelInteractividad = usoEducativo.getNivelDeInteractividadAv();
				if(nivelInteractividad !=null && !aNivelInteractividadV.contains(nivelInteractividad)){
					logger.info("Valor erroneo de nivel de interactividad: " + nivelInteractividad + ". Borramos valor.");
					resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
					.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.nivelInteractividad"))
					.append(nivelInteractividad).append(";");
					usoEducativo.setNivelDeInteractividadAv(null);
					haCambiado = true;
					cambioUsosEdu=true;
				}
				//***********densidadSemantica***********
				String densidadSemantica = usoEducativo.getDensidadSemanticaAv();
				if(densidadSemantica !=null && !aDensidadSemanticaV.contains(densidadSemantica)){
					logger.info("Valor erroneo de densidad semantica: " + densidadSemantica + ". Borramos valor.");
					resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
					.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.densidadSemantica"))
					.append(densidadSemantica).append(";");
					usoEducativo.setDensidadSemanticaAv(null);
					haCambiado = true;
					cambioUsosEdu=true;
				}
				//***********destinatarios***********
				ArrayList<String> destinatarios = usoEducativo.getDestinatariosAv();
				boolean cambioDestinatarios = false;
				ArrayList<String> new_Destinatarios = new ArrayList<String>();
				for(int k = 0; k < destinatarios.size();k++){
					String destinatario = destinatarios.get(k);
					if(destinatario !=null && !aDestinatarioV.contains(destinatario)){
						logger.info("Valor erroneo de destinatario: " + destinatario + ". Borramos valor.");
						resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
						.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.destinatario"))
						.append(destinatario).append(";");
						haCambiado = true;
						cambioDestinatarios = true;
						cambioUsosEdu=true;
					}
					else{
						new_Destinatarios.add(destinatario);
					}
				}
				if(cambioDestinatarios)
					usoEducativo.setDestinatariosAv(new_Destinatarios);
				//***********contextos***********
				ArrayList<String> contextos = usoEducativo.getContextosAv();
				boolean cambioContextos = false;
				ArrayList<String> new_Contextos = new ArrayList<String>();
				for(int k = 0; k < contextos.size();k++){
					String contexto = contextos.get(k);
					if(contexto !=null && !aContextoV.contains(contexto)){
						logger.info("Valor erroneo de contexto: " + contexto + ". Borramos valor.");
						resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
						.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.contexto"))
						.append(contexto).append(";");
						haCambiado = true;
						cambioContextos = true;
						cambioUsosEdu=true;
					}
					else{
						new_Contextos.add(contexto);
					}
				}
				if(cambioContextos)
					usoEducativo.setContextosAv(new_Contextos);

				//***********dificultad***********
				String dificultad = usoEducativo.getDificultadAv();
				if(dificultad !=null && !aDificultadV.contains(dificultad)){
					logger.info("Valor erroneo de dificultad: " + dificultad + ". Borramos valor.");
					resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
					.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.dificultad"))
					.append(dificultad).append(";");
					usoEducativo.setDificultadAv(null);
					haCambiado = true;
					cambioUsosEdu=true;
				}

				//***********Tiempo de aprendizaje***********				
				DuracionAgrega duracionAgrega = usoEducativo.getTiempoTipicoAprendizajeAv();
				if(duracionAgrega!=null){
					String duracion =duracionAgrega.getDuracion();
					if(duracion!=null){
						Pattern mask=Pattern.compile("^P([0-9]+Y){0,1}([0-9]+M){0,1}([0-9]+D){0,1}(T([0-9]+H){0,1}([0-9]+M){0,1}([0-9]+(.[0-9]+){0,1}S){0,1}){0,1}$"); //ejm ee-zz
						Matcher matcher = mask.matcher(duracion);
						boolean correcto = matcher.matches();
						if(!correcto){
							logger.info("Valor erroneo de duracion: " + duracion + ". Borramos valor.");
							resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
							.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.duracion"))
							.append(duracion).append(";");
							duracionAgrega.setDuracion(null);
							usoEducativo.setTiempoTipicoAprendizajeAv(duracionAgrega);
							haCambiado = true;
							cambioUsosEdu=true;
						}
					}
				}

				//***********idiomasDestinatarios***********
				ArrayList<String> idiomasDestinatarios = usoEducativo.getIdiomasDestinatarioAv();
				boolean cambioIdiomasDest = false;
				ArrayList<String> new_IdiomasDest = new ArrayList<String>();
				for(int k = 0; k < idiomasDestinatarios.size();k++){
					String idioma = idiomasDestinatarios.get(k);
					if(idioma !=null && !aIdiomaDestinatarioV.contains(idioma)){
						logger.info("Valor erroneo de idioma destinario: " + idioma + ". Borramos valor.");
						resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
						.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.idiomaDestinatario"))
						.append(idioma).append(";");
						haCambiado = true;
						cambioIdiomasDest = true;
						cambioUsosEdu=true;
					}
					else{
						new_IdiomasDest.add(idioma);
					}
				}
				if(cambioIdiomasDest)
					usoEducativo.setIdiomasDestinatarioAv(new_IdiomasDest);
				//***********procesosCognitivos***********				
				ArrayList<String> procesosCognitivos = usoEducativo.getProcesosCognitivosAv();
				boolean cambioProcesosCog = false;
				ArrayList<String> new_ProcesosCog = new ArrayList<String>();
				for(int k = 0; k < procesosCognitivos.size();k++){
					String proceso = procesosCognitivos.get(k);
					if(proceso !=null && !aProcesoCognitivoV.contains(proceso)){
						logger.info("Valor erroneo de proceso cognitivo: " + proceso + ". Borramos valor.");
						resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
						.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.proceso"))
						.append(proceso).append(";");
						haCambiado = true;
						cambioProcesosCog = true;
						cambioUsosEdu=true;
					}
					else{
						new_ProcesosCog.add(proceso);
					}
				}
				if(cambioProcesosCog)
					usoEducativo.setProcesosCognitivosAv(new_ProcesosCog);

				if((usoEducativo.getTipoDeInteractividadAv()!=null && !usoEducativo.getTipoDeInteractividadAv().equals("")) ||
						(usoEducativo.getTiposDeRecursoAv()!=null && usoEducativo.getTiposDeRecursoAv().size()>0) ||
						(usoEducativo.getNivelDeInteractividadAv()!=null && !usoEducativo.getNivelDeInteractividadAv().equals("")) ||
						(usoEducativo.getDensidadSemanticaAv()!=null && !usoEducativo.getDensidadSemanticaAv().equals("")) ||
						(usoEducativo.getDestinatariosAv()!=null && usoEducativo.getDestinatarios().size()>0) ||
						(usoEducativo.getContextosAv()!=null && usoEducativo.getContextosAv().size()>0) ||
						(usoEducativo.getRangosEdadAv()!=null && usoEducativo.getRangosEdadAv().size()>0) ||
						(usoEducativo.getDificultadAv()!=null && !usoEducativo.getDificultadAv().equals("")) ||
						(usoEducativo.getTiempoTipicoAprendizajeAv()!=null && usoEducativo.getTiempoTipicoAprendizajeAv().getDuracion()!=null && !usoEducativo.getTiempoTipicoAprendizajeAv().getDuracion().equals("")) ||
						(usoEducativo.getTiempoTipicoAprendizajeAv()!=null && usoEducativo.getTiempoTipicoAprendizajeAv().getDescripciones()!=null && usoEducativo.getTiempoTipicoAprendizajeAv().getDescripciones().size()>0) ||
						(usoEducativo.getDescripcionesAv()!=null && usoEducativo.getDescripcionesAv().size()>0) ||
						(usoEducativo.getIdiomasDestinatarioAv()!=null && usoEducativo.getIdiomasDestinatarioAv().size()>0) ||
						(usoEducativo.getProcesosCognitivosAv()!=null && usoEducativo.getProcesosCognitivosAv().size()>0)){
					new_usosEducativos.add(usoEducativo);
				}	
			}
			if(cambioUsosEdu){
				EducationalAgrega[] aUsosEducativos= new_usosEducativos.toArray(new EducationalAgrega[new_usosEducativos.size()]);
				lomAgrega.setEducationalsAgrega(aUsosEducativos);
			}
			/**********************Derechos**********************/
			logger.debug("Comprobando campos de la categoría Derechos");
			RightsAgrega derechosAgrega = lomAgrega.getRightsAgrega();

			String coste = derechosAgrega.getCosteAv();
			if(coste !=null && !aCosteV.contains(coste)){
				logger.info("Valor erroneo de coste: " + coste + ". Borramos valor.");
				resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
				.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.coste"))
				.append(coste).append(";");
				derechosAgrega.setCosteAv(null);
				haCambiado = true;
			}

			String derechosAutor = derechosAgrega.getDerechosDeAutorAv();
			if(derechosAutor !=null && !aDerechosAutorV.contains(derechosAutor)){
				logger.info("Valor erroneo de derechos de autor: " + derechosAutor + ". Borramos valor.");
				resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
				.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.derechosAutor"))
				.append(derechosAutor).append(";");
				derechosAgrega.setDerechosDeAutorAv(null);
				haCambiado = true;
			}

			AccesoAgrega acceso = derechosAgrega.getAccesoAv();
			String tipoAcceso = acceso.getTipoAcceso();
			if(tipoAcceso !=null && !aTipoAccesoV.contains(tipoAcceso)){
				logger.info("Valor erroneo de tipo de acceso: " + tipoAcceso + ". Borramos valor.");
				resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
				.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.tipoAcceso"))
				.append(tipoAcceso).append(";");
				acceso.setTipoAcceso(null);
				derechosAgrega.setAccesoAv(acceso);
				haCambiado = true;
			}

			/**********************Relación**********************/
			logger.debug("Comprobando campos de la categoría Relacion");
			ArrayList<RelationAgrega> relacionesAgrega = lomAgrega.getRelationsAgrega();
			ArrayList<RelationAgrega> new_relaciones= new ArrayList<RelationAgrega>();
			boolean cambioRelacion = false;
			for(int j = 0;j < relacionesAgrega.size(); j++){
				RelationAgrega relacionAgrega = relacionesAgrega.get(j);
				String tipo = relacionAgrega.getTipoAv();
				if(tipo !=null && !aTipoRelacionV.contains(tipo)){
					logger.info("Valor erroneo de tipo de relacion: " + tipo + ". Borramos valor.");
					resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
					.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.tipoRelacion"))
					.append(tipo).append(";");
					relacionAgrega.setTipoAv(null);
					haCambiado = true;
					cambioRelacion=true;
				}
				if((relacionAgrega.getTipoAv()!=null && !relacionAgrega.getTipoAv().equals("")) ||
						(relacionAgrega.getRecursoAv()!=null && relacionAgrega.getRecursoAv().getDescripciones()!=null && 
								relacionAgrega.getRecursoAv().getDescripciones().size()>0) ||
								(relacionAgrega.getRecursoAv()!=null && relacionAgrega.getRecursoAv().getIdentificador()!=null && 
										relacionAgrega.getRecursoAv().getIdentificador().getCatalogo()!=null && 
										!relacionAgrega.getRecursoAv().getIdentificador().getCatalogo().equals("")) ||
										(relacionAgrega.getRecursoAv()!=null && relacionAgrega.getRecursoAv().getIdentificador()!=null && 
												relacionAgrega.getRecursoAv().getIdentificador().getEntrada()!=null && 
												!relacionAgrega.getRecursoAv().getIdentificador().getEntrada().equals(""))){
					new_relaciones.add(relacionAgrega);
				}
			}
			if(cambioRelacion){
				RelationAgrega[] aRelaciones= new_relaciones.toArray(new RelationAgrega[new_relaciones.size()]);
				lomAgrega.setRelationsAgrega(aRelaciones);
			}
			/**********************Anotacion**********************/
			logger.debug("Comprobando campos de la categoría Anotacion");
			ArrayList<AnnotationAgrega> anotacionesAgrega = lomAgrega.getAnnotationsAgrega();
			ArrayList<AnnotationAgrega> new_anotaciones= new ArrayList<AnnotationAgrega>();
			boolean cambioAnotacion = false;
			for(int j = 0;j < anotacionesAgrega.size(); j++){
				AnnotationAgrega anotacionAgrega = anotacionesAgrega.get(j);
				FechaAgrega fecha = anotacionAgrega.getFechaAv();
				if(fecha!=null && fecha.getFecha()!=null){
					String sFecha = fecha.getFecha();
					boolean correcta = this.comprobarFecha(sFecha);

					if(!correcta){
						logger.info("Valor erroneo de fecha: " + sFecha + ". Borramos valor.");
						resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
						.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.fechaAnotacion"))
						.append(sFecha).append(";");
						fecha.setFecha(null);
						anotacionAgrega.setFechaAv(fecha);
						haCambiado = true;
						cambioAnotacion=true;
					}
					if(fecha.getFecha() != null || (fecha.getDescripciones()!=null && fecha.getDescripciones().size()>0)){
						new_anotaciones.add(anotacionAgrega);
					}
				}								
			}
			if(cambioAnotacion){
				AnnotationAgrega[] aAnotaciones= new_anotaciones.toArray(new AnnotationAgrega[new_anotaciones.size()]);
				lomAgrega.setAnnotationsAgrega(aAnotaciones);
			}
			/**********************Clasificacion**********************/
			logger.debug("Comprobando campos de la categoría Clasificacion");
			ArrayList<ClassificationAgrega> clasificacionesAgrega = lomAgrega.getClassificationsAgrega();
			ArrayList<ClassificationAgrega> new_clasificaciones= new ArrayList<ClassificationAgrega>();
			boolean cambioClasificacion = false;
			for(int j = 0; j < clasificacionesAgrega.size(); j++){
				ClassificationAgrega clasificacionAgrega = clasificacionesAgrega.get(j);
				String proposito = clasificacionAgrega.getPropositoAv();
				if(proposito !=null && !aPropositoV.contains(proposito)){
					logger.info("Valor erroneo de proposito: " + proposito + ". Borramos valor.");
					resultado.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.texto.borrado"))
					.append(I18nModuloPublicacion.getPropertyValueI18n("vocabularios.proposito"))
					.append(proposito).append(";");
					clasificacionAgrega.setPropostitoAv(null);
					haCambiado = true;
					cambioClasificacion=true;
				}
				if((clasificacionAgrega.getPropositoAv()!=null && !clasificacionAgrega.getPropositoAv().equals("")) ||
						(clasificacionAgrega.getRutaTaxonomicasAv()!=null && clasificacionAgrega.getRutaTaxonomicasAv().size() >0) ||
						(clasificacionAgrega.getPalabrasClaveAv()!=null && clasificacionAgrega.getPalabrasClaveAv().size()>0) ||
						(clasificacionAgrega.getDescripcionesAv()!=null && clasificacionAgrega.getDescripcionesAv().size()>0)){
					new_clasificaciones.add(clasificacionAgrega);
				}
			}
			if(cambioClasificacion){
				ClassificationAgrega[] aClasificaciones= new_clasificaciones.toArray(new ClassificationAgrega[new_clasificaciones.size()]);
				lomAgrega.setClassificationsAgrega(aClasificaciones);
			}
			if (logger.isDebugEnabled())
				logger.debug("OBTENERELEMENTO CON EL IDPADRE " + idPadre);
			//obtenemos la localización del lomes, por si estuviera referenciado por location
			Object elemento = manifestAgrega.obtenerElemento(idPadre);
			String location = null;
			if (elemento instanceof Manifest) {
				if (logger.isDebugEnabled())
					logger.debug("ELEMENTO DE TIPO MANIFEST");
				Manifest new_elemento = (Manifest) elemento;
				location = this.obtenLocation(new_elemento.getMetadata());				
			}else if (elemento instanceof Organization){
				if (logger.isDebugEnabled())
					logger.debug("ELEMENTO DE TIPO ORGANIZATION");
				Organization new_elemento = (Organization) elemento;
				location = this.obtenLocation(new_elemento.getMetadata());
			}else if (elemento instanceof Item){
				if (logger.isDebugEnabled())
					logger.debug("ELEMENTO DE TIPO ITEM");
				Item new_elemento = (Item) elemento;
				location = this.obtenLocation(new_elemento.getMetadata());
			}else if (elemento instanceof Resource){
				if (logger.isDebugEnabled())
					logger.debug("ELEMENTO DE TIPO RESOURCE");
				Resource new_elemento = (Resource) elemento;
				location = this.obtenLocation(new_elemento.getMetadata());
			}

			if (haCambiado) {//si el lomes ha cambiado se inserta de nuevo el manifest o se sobreescribe 
				//el fichero si estuviera referenciado mediante location
				if (logger.isDebugEnabled())
					logger.debug("LLAMAMOS A SETLOM CON idPadre " + idPadre + " location " + location + " lomes " + lomes);
				manifestAgrega.setLom(idPadre, location, lomes);
			}
		}
		return resultado.toString();
	}

	private String obtenLocation(Metadata metadata){
		String location = null;
		if (metadata != null && metadata.getGrp_any() != null && metadata.getGrp_any().getAnyObject() != null) {
			Object[] array = metadata.getGrp_any().getAnyObject();
			for (int k = 0;  k < array.length; k++) {
				if (array[k] instanceof Location) {
					location = ((Location)array[k]).getContent();
				}
			}
		}
		if (logger.isDebugEnabled())
			logger.debug("DEVOLVEMOS EL LOCATION " + location);
		return location;
	}

	private ArrayList<String> obtenValoresVocabulario(VocabularioVO vocabulario){
		ArrayList<String> resultado = new ArrayList<String>();
		TerminoVO[] valores = vocabulario.getTerminos();
		for(int i = 0; i< valores.length;i++){
			resultado.add(valores[i].getNomTermino());
		}
		return resultado;
	}

	/**
	 * Comprueba la fecha pasada
	 * @param fecha
	 * @return boolean si la fecha es correcta o no
	 */
	private boolean comprobarFecha(String fecha){
		boolean correcto = true;
		String expresion = "^([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]|[0-9][1-9][0-9]{2}|[1-9][0-9]{3})(\\-(0[1-9]|1[0-2])(\\-(0[1-9]|[1-2][0-9]|3[0-1])(T([0-1][0-9]|2[0-3])(:[0-5][0-9](:[0-5][0-9](\\.[0-9]{1,}(Z|((\\+|\\-)([0-1][0-9]|2[0-3]):[0-5][0-9]))?)?)?)?)?)?)?$";
		Pattern mask=Pattern.compile(expresion);
		Matcher matcher = mask.matcher(fecha);
		correcto = matcher.matches();
		return correcto;
	}
	/**
	 * Este metodo nos da las transiciones de los ODEs propuestos para publicaión del grupo(s) de trabajo(s) al/ a los que pertenece
	 * el usuario. Los identificadores de entrada son los identificadores de los usuarios que comparten grupo(s) con nuestro usuario
	 * 
	 * @param ids
	 * 			Los identificadores de entrada son los identificadores de los usuarios que comparten grupo(s) con nuestro usuario
	 * @return se retorna el array con las transiciones ordenadas por fecha de
	 *         los ODEs en estado propuestos para publicación por un array de usuarios.
	 * @throws Exception
	 * 
	 */

	protected TransicionVO[] handleObtenODEsPropuestosPorUsuarios(String[] ids) throws Exception {
		return SrvPublicacionServiceImpl.obtenerODEsPorEstadoUsuarios(ids, SrvPublicacionServiceImpl.PROPUESTO, this.getEstadoDao(), this.getTransicionDao());
	}

	/**
	 * Este metodo nos da las transiciones de los ODEs compartidos del grupo(s) de trabajo(s) al/ a los que pertenece
	 * el usuario. Los identificadores de entrada son los identificadores de los usuarios que comparten grupo(s) con nuestro usuario
	 * 
	 * @param ids
	 * 			Los identificadores de entrada son los identificadores de los usuarios que comparten grupo(s) con nuestro usuario
	 * @return se retorna el array con las transiciones ordenadas por fecha de
	 *         los ODEs en estado propuestos para catalogación por un array de usuarios.
	 * @throws Exception
	 * 
	 */
	protected TransicionAutorVO[] handleObtenODEsCompartidosPorUsuarios(String[] ids) throws Exception {
		TransicionAutorVO[] result = null;
		try {
			TransicionDao transicion = this.getTransicionDao();
			EstadoCompartidoUsuariosCriteria criterio=new EstadoCompartidoUsuariosCriteria();
			EstadoCompartidoUsuariosCriteria criterio2=new EstadoCompartidoUsuariosCriteria();
			criterio2.setCompartido(true);
			criterio2.setEstadoTransitado(null);
			criterio2.setEstadoActual(this.getEstadoDao().obtenEstadoPorNombre(CREACION));
			criterio2.setIdsUsuarios(ids);
			criterio.setCompartido(true);
			criterio.setEstadoTransitado(null);
			criterio.setEstadoActual(this.getEstadoDao().obtenEstadoPorNombre(RECHAZADO));
			criterio.setIdsUsuarios(ids);

			List transiciones = transicion.buscarOdesPorEstadoCompartidoUsuarios(TransicionDao.TRANSFORM_TRANSICIONAUTORVO, criterio2);
			List transiciones2=transicion.buscarOdesPorEstadoCompartidoUsuarios(TransicionDao.TRANSFORM_TRANSICIONAUTORVO, criterio);
			transiciones.addAll(transiciones2);
			logger.info("Obtenidos " + transiciones.size() + " odes en estado "+ SrvPublicacionServiceImpl.CREACION+" y "+SrvPublicacionServiceImpl.RECHAZADO+" para la lista de usuarios");
			result = (TransicionAutorVO[]) transiciones.toArray(new TransicionAutorVO[transiciones.size()]);
		} catch (Exception e) {
			logger.error("Error obteniendo los odes compartidos por usuarios, "+ e);
			throw e;
		}
		return result;
	}

	/**
	 * Este metodo nos da las transiciones de los ODEs despublicados del grupo(s) de trabajo(s) al/ a los que pertenece
	 * el usuario. Los identificadores de entrada son los identificadores de los usuarios que comparten grupo(s) con nuestro usuario
	 * 
	 * @param ids
	 * 			Los identificadores de entrada son los identificadores de los usuarios que comparten grupo(s) con nuestro usuario
	 * @return se retorna el array con las transiciones ordenadas por fecha de
	 *         los ODEs en estado propuestos para catalogación por un array de usuarios.
	 * @throws Exception
	 * 
	 */
	protected TransicionVO[] handleObtenODEsDespublicadosPorUsuarios(String[] ids) throws Exception {
		return SrvPublicacionServiceImpl.obtenerODEsPorEstadoUsuarios(ids, SrvPublicacionServiceImpl.NO_DISPONIBLE, this.getEstadoDao(), this.getTransicionDao());
	}

	/**
	 * Este metodo nos da las transiciones de los ODEs compartidos con un titulo semejante, del grupo(s) de trabajo(s) al/ a los que pertenece
	 * el usuario. Los identificadores de entrada son los identificadores de los usuarios que comparten grupo(s) con nuestro usuario
	 * 
	 * @param ids
	 *              Los identificadores de entrada son los identificadores de los usuarios que comparten grupo(s) con nuestro usuario
	 * @param titulo
	 *              Título por el que se desea buscar

	 * @return se retorna el array con las transiciones con titulo semejante ordenadas por fecha de
	 *         los ODEs en estado propuestos para catalogación por un array de usuarios.
	 * @throws Exception
	 * 
	 */

	protected TransicionAutorVO[] handleObtenOdesCompartidosPorTituloYUsuarios(String[] ids, String titulo) throws Exception {
		TransicionAutorVO[] result = null;

		try {
			titulo = this.concatenarBusqueda(titulo);
			logger.debug("El filtro de búsqueda es: " + titulo);
			TransicionDao transicion = this.getTransicionDao();
			EstadoCompartidoUsuariosTituloCriteria criterio=new EstadoCompartidoUsuariosTituloCriteria();
			EstadoCompartidoUsuariosTituloCriteria criterio2=new EstadoCompartidoUsuariosTituloCriteria();
			criterio2.setCompartido(true);
			criterio2.setEstadoTransitado(null);
			criterio2.setEstadoActual(this.getEstadoDao().obtenEstadoPorNombre(CREACION));
			criterio.setCompartido(true);
			criterio.setEstadoTransitado(null);
			criterio.setEstadoActual(this.getEstadoDao().obtenEstadoPorNombre(RECHAZADO));
			criterio2.setTitulo(titulo);
			criterio.setTitulo(titulo);
			if(ids!=null && ids.length>0){
				criterio2.setIdsUsuarios(ids);
				criterio.setIdsUsuarios(ids);
			}else{
				criterio2.setIdsUsuarios(null);
				criterio.setIdsUsuarios(null);
			}
			List transiciones = transicion.buscarOdesPorEstadoCompartidoUsuariosTitulo(TransicionDao.TRANSFORM_TRANSICIONAUTORVO, criterio2);
			List transiciones2=transicion.buscarOdesPorEstadoCompartidoUsuariosTitulo(TransicionDao.TRANSFORM_TRANSICIONAUTORVO, criterio);
			transiciones.addAll(transiciones2);
			logger.info("Obtenidos " + transiciones.size() + " odes en estado "
					+ SrvPublicacionServiceImpl.CREACION+" y "+SrvPublicacionServiceImpl.RECHAZADO+" para la lista de usuarios "+ Arrays.toString(ids)+"y el titulo "+titulo);
			result = (TransicionAutorVO[]) transiciones.toArray(new TransicionAutorVO[transiciones.size()]);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		return result;
	}

	
	//TODO
	//LUIS: esta funcion debera ignorar aquellos ODEs con imagenes por defecto
	/**
	 * Llama a la galería de imagenes para regenerar las imágenes (thubnails) de los ODEs del repositorio.
	 * @param idTarea Identificador de la tarea para el servicio de auditoria.
	 * @return Booleano indicando si la tarea se ha realizado con exito.
	 */
	@Override
	protected Boolean handleRegenerarImagenes(Long idTarea) throws Exception {
		new ArrayList<ReindexarODEResultadoVO>(10);
		TareaEjecutadaPlanVO trabajo = new TareaEjecutadaPlanVO(); 
		Boolean vuelta=false;
		try {
			if (idTarea == null){
				vuelta=false;
				logger.warn("Error intentando regenerar imagenes. El identificador de la tarea es nula.");
				throw new PublicarException(
				"Error intentando regenerar imagenes. El identificador de la tarea es nula.");
			}
			I18n i18n=new I18n();
			String[] idiomas=i18n.obtenerIdiomasBuscables();
			if (idiomas == null || idiomas.length == 0) {
				logger.warn("Error intentando regenerar imagenes. No se suministran idiomas sobre los que actuar.");
				trabajo.setEstado(ConstantesAgrega.TRABAJO_ERRONEO);
				trabajo.setFechaFin(Calendar.getInstance());
				trabajo.setId(idTarea);
				try {
					this.getSrvAuditoriaServicio().registrarTrabajoFechaFinPlan(trabajo);
				} catch (Exception e) {
					logger.error("Error en la invocacion al servicio de auditoria con estado del trabajo erroneo",e);
					throw new Exception("Error en la invocacion al servicio de auditoria con estado del trabajo erroneo");
				}
				throw new Exception("Error intentando regenerar imagenes. No se suministran idiomas sobre los que actuar.");
			}
			//Borramos las imagenes existentes

			String serverID = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID);
			String galeria = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.PATH_GALERIA_IMG);
			String fileString=galeria+serverID+"/";
			File fileBorrar=new File(fileString);
			new UtilesFicheros();
			UtilesFicheros.eliminar(fileBorrar);
			logger.debug("Hemos borrado los ficheros de las imagenes con url "+fileString);
			// Comenzamos con la paginacion
			for (int i = 0; i < idiomas.length; i++) {
				IdiomaCriteria criterioIdioma = new IdiomaCriteria();
				criterioIdioma.setIdioma(idiomas[i]);
				Boolean hayMas = true;
				Integer inicio = 0;
				if (logger.isDebugEnabled()) logger.debug("Cogemos la propiedad pagina");
				Integer iPagina = Integer.parseInt(SrvPublicacionServiceImpl.getPropertyValue(TAMANIO_PAGINA));
				if (logger.isDebugEnabled()) logger.debug("Pagina [" + iPagina + "]");
				Integer fin = iPagina;

				while (hayMas.booleanValue()) {
					logger.debug("Regenerando imagenes[" + idiomas[i] + "] paginando desde[" + inicio + "] hasta["
							+ fin + "] hayMas?[" + hayMas + "]");
					criterioIdioma.setFirstResult(inicio);
					criterioIdioma.setMaximumResultSize(iPagina);

					List resultados = this.getOdePublicadoDao().obtenODEsPublicadosPorIdioma(criterioIdioma);
					if (resultados == null)
						hayMas = false;
					else if (resultados != null
							&& (resultados.size() == 0 || (resultados.size() > 0 && resultados.size() < iPagina.intValue())))
						hayMas = false;
					if (hayMas.booleanValue()) {
						inicio = fin;
						fin = new Integer(fin.intValue() + iPagina.intValue());
					}
					OdePublicado[] odesPublicados = null;
					if (resultados != null && resultados.size() > 0) {
						if (logger.isDebugEnabled())logger.debug("Resultados[" + resultados.size() + "]");
						odesPublicados = (OdePublicado[]) resultados.toArray(new OdePublicado[resultados.size()]);
						if (logger.isDebugEnabled())logger.debug("Recuperamos [" + resultados.size() + "] ODEs publicados para el idioma["
								+ idiomas[i] + "]");
						// Preparamos los objetos de salida, ya que pueden ocurrir errores con los ODEs recuperados y
						// habra que marcarlos como erroneos

						//Aqui se regenera todo el indice, como lo hacemos para que solo regenere la imagen?
						if (odesPublicados == null || !(odesPublicados.length > 0)){
							logger.warn("No hay odes publicados ni se extrae informacion de regeneracion para el idioma");
						}else{   //(odesPublicados != null && odesPublicados.length > 0) 
							// Cojemos todas las localizaciones para el array de odesPublicados que nos pasan
							String[] arrayIds = new String[odesPublicados.length];
							for (int j = 0; j < odesPublicados.length; j++) {
								arrayIds[j] = odesPublicados[j].getIdODE();
							}
							// Obtengo el array de localizadores
							LocalizadorVO[] arrayLocalizaciones = this.getSrvLocalizadorService().buscarLocalizadoresPorId(arrayIds);
							// Para cada localizador Parseo el objeto
							OdeVO[] arrayOdes=new OdeVO[arrayLocalizaciones.length];
							for (int j = 0; j < arrayLocalizaciones.length; j++) {
								String sIdentificador = arrayLocalizaciones[j].getIdentificador();
								String sPath = arrayLocalizaciones[j].getPath();
								try {
									// Parseamos el ODE extrayendo la informacion a indexar
									if (logger.isDebugEnabled())
										logger.debug("Localizamos el ODE[" + sIdentificador + "] y con path [" + sPath+ "]y lo parseamos.");

									// Parseamos
									File fileManifest = new File(sPath, MANIFEST_NAME);
									Manifest imsmanifest = this.getScormDao().parsearODEEager(fileManifest);

									// Mapear la informacion de indexacion en el VO que pasamos al indexador, utilizando la valoracion
									// que nos pasan para indexarla.
									// rellenamos y añadimos a la lista todos los idodevos
									if (logger.isDebugEnabled())
										logger.debug("Generamos el objeto de regeneracion del ODE [" + sIdentificador + "]");

									ManifestAgrega manifAgrega = new ManifestAgrega(imsmanifest);
									AgregaPropertiesImpl properties = (AgregaPropertiesImpl) AgregaPropertiesImpl.getInstance();									
									String pathOde=TratamientoImagenes.localPathGenerate(manifAgrega, sIdentificador, sPath, properties, this.getSrvPropiedadService());
									OdeVO odeVO=new OdeVO();
									odeVO.setIdentificadorMEC(sIdentificador);
									odeVO.setMainFile(pathOde);//Es el href de la imagen
									odeVO.setServerOn(serverID);
									logger.debug("Creamos el odeVO con identificador "+sIdentificador+" el mainFile "+pathOde+ " y el server "+serverID);
									arrayOdes[j]=odeVO;
								} catch (Exception ex) {
									vuelta=false;
									logger.error("Se ha producido un error al intentar regenerar la imagen [" + sIdentificador+ "] y path [" + sPath + "]", ex);
								}
							}
							TratamientoImagenes.createImage4Odes(arrayOdes, this.getSrvPropiedadService());
							vuelta=true;
						}
					} else {
						logger.warn("No existen odesPublicados para el indice de idioma [" + idiomas[i] + "]");
					}// else
					if (logger.isDebugEnabled())
						logger.debug("Fin del WHILE -> Inicio[" + inicio + "] fin [" + fin + "] hayMas [" + hayMas+ "]");
				}// while
			}// for 
		} catch (Exception ex) {
			vuelta=false;
			logger.error("Se ha producido un error al regenerar imagenes", ex);
			throw ex;
		}
		// LLamamos al servicio de auditoria para actualizar el estado del trabajo
		trabajo.setEstado(ConstantesAgrega.TRABAJO_CORRECTO);
		trabajo.setFechaFin(Calendar.getInstance());
		trabajo.setId(idTarea);
		try {
			this.getSrvAuditoriaServicio().registrarTrabajoFechaFinPlan(trabajo);
		} catch (Exception e) {
			logger.error("Error en la invocacion al servicio de auditoria con estado del trabajo correcto["	+ e.getMessage() + "]",e);
			throw new Exception("Error en la invocacion al servicio de auditoria con estado del trabajo correcto", e);
		}
		return vuelta;
	}

	/**
	 * Obtiene un listado de ODEs compartidos por los usuarios filtrado por
	 * título.
	 * 
	 * @param titulo
	 *            Cadena con las palabras que se desean encontrar en el título
	 *            de los ODEs. La busqueda es case-insensitive y se parte la
	 *            cadena de busqueda para encontrar las palabras sueltas en el
	 *            título (por ejemplo, la cadena 'animales mar' puede encontrar
	 *            un ODE titulado 'Comportamiento de los animales en el mar')
	 * @return Array con los datos de los ODEs encontrados.
	 */
	@Override
	protected TransicionAutorVO[] handleObtenOdesCompartidosPorTitulo(String titulo) throws Exception {
		TransicionAutorVO[] result = null;
		try {
			logger.debug("titulo" + titulo);
			titulo = this.concatenarBusqueda(titulo);
			logger.debug("El filtro de búsqueda es: " + titulo);
			TransicionDao transicion = this.getTransicionDao();
			EstadoCompartidoTituloCriteria criterio=new EstadoCompartidoTituloCriteria();
			EstadoCompartidoTituloCriteria criterio2=new EstadoCompartidoTituloCriteria();
			criterio2.setCompartido(true);
			criterio2.setEstadoTransitado(null);
			criterio2.setEstadoActual(this.getEstadoDao().obtenEstadoPorNombre(CREACION));
			criterio.setCompartido(true);
			criterio.setEstadoTransitado(null);
			criterio.setEstadoActual(this.getEstadoDao().obtenEstadoPorNombre(RECHAZADO));
			criterio2.setTitulo(titulo);
			criterio.setTitulo(titulo);

			List transiciones = transicion.buscarOdesPorEstadoCompartidoTitulo(TransicionDao.TRANSFORM_TRANSICIONAUTORVO, criterio2);
			List transiciones2=transicion.buscarOdesPorEstadoCompartidoTitulo(TransicionDao.TRANSFORM_TRANSICIONAUTORVO, criterio);
			transiciones.addAll(transiciones2);
			logger.info("Obtenidos " + transiciones.size() + " odes en estado "
					+ SrvPublicacionServiceImpl.CREACION+" y "+SrvPublicacionServiceImpl.RECHAZADO+" para la lista de usuarios todos y el titulo "+titulo);
			result = (TransicionAutorVO[]) transiciones.toArray(new TransicionAutorVO[transiciones.size()]);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		return result;
	}

	private String concatenarBusqueda (String texto) throws Exception{
		try {
			logger.debug("titulo" + texto);
			StringBuffer busqueda = new StringBuffer("%");
			StringTokenizer token = new StringTokenizer(texto, " ");
			while ((token.hasMoreElements())){
				busqueda.append(token.nextElement().toString()).append("%");
			}
			logger.info("La busqueda se realizará por los siguientes criterios: " + busqueda.toString());
			return busqueda.toString();
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	/**
	 * Errores del validador: 0.0 sin errores 1.1 imsmanifest es incorrecto 1.2
	 * error de parseo en el manifest 1.3 error, la etiqueta principal debe ser
	 * manifest o lom 1.4 error de chequeo de contenidos 2.1 el LOM-ES es
	 * obligatorio para metadata 2.2 el LOM-ES es incorrecto 2.3 el LOM-ES es
	 * incorrecto para la etiqueta de organizaciones 3.1 no existe el ODE 4.1
	 * los datos del formulario son obligatorios 4.2 el Titulo es obligatorio
	 * 4.3 el Idioma es obligatorio 4.4 la Descripcion es obligatoria 4.5 el
	 * Tipo es obligatorio 4.6 el Contexto es obligatorio 4.7 la Edad es
	 * obligatoria 4.8 el Idioma Destino es obligatorio 4.9 el Proceso Cognitivo
	 * es obligatorio Errores detectables en la publicacion de un ode via el
	 * metodo publica PIF 10.1 Fallo en escritura a disco o el fichero ya existe
	 * 10.2 El mec ya existía 10.3 No se ha podido publicar el ode, error en
	 * indexación 10.4 El fichero original del ODE no existe 10.5 El
	 * identificador del ODE tiene una lozalizacion invalida 10.6 El ODE ya esta
	 * creado en la plataforma
	 * 
	 */

	/**
	 * Publica objetos en formato PIF (ZIP) procendentes de una carga masiva.
	 * 
	 * @param odes
	 *            Array de las rutas de los odes que se quieren publicar
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @param sobrescribir
	 *            Parametro que nos indica si se debe sobrescribir un ode en caso de que ya haya alguno igual publicao en la plataforma. Valores s/n.
	 * @param nombreCarga
	 *            El nombre que se le da a la carga de odes
	 * @param pathCarga
	 * 				El path que contiene a todos los odes que se quieren publicar
	 * @return se retorna un array de VOs con el resultado de la publicación de los odes. Cada VO retorna con el error que se ha producido en el caso de
	 *         detectarse algun problema.
	 * @throws Exception
	 * 
	 */

	protected ResultadoOperacionCargaVO[] handlePublicarPifCarga(String[] odes, String idUsuario, 
			String sobrescribir, String nombreCarga, String pathCarga) throws Exception
			{
		/*Tiene que haber un momento a lo largo del método, que yo mire si los odes, tienen odes similares, y en ese caso,
		 * concatenarlos (posiblemente, separados con comas) y pasarselos  al resultadoOperacionCargaVO, 
		 * para que lo tenga el servicio de planificador   
		 * El problema esta donde se va a mirar. De momento, podemos ir añadiendo la variable al VO
		 * */
		ResultadoOperacionCargaVO[] resultado = null;
		String idODE = null;
		String nombreZip = "";
		String pathZip = "";
		logger.info("Publicando odes (de carga), cantidad "+odes.length);
		resultado = new ResultadoOperacionCargaVO[odes.length];
		String titulo="";
		String comentarios="";
		String tipo="";

		//Recorremos el array de objetos a cargar
		for(int i=0; i < odes.length; i++)
		{
			String xsl="";
			boolean sobrescrito=false;
			boolean odesContienen=false;
			resultado[i] = new ResultadoOperacionCargaVO();
			String odeAlt=odes[i];
			if(odeAlt.indexOf("\\")!=(-1)){//Unificamos las escrituras de los path; si vienen con doble \ lo convertimos a /
				odeAlt=odeAlt.replace('\\', '/');
				odesContienen=true;
			}
			int posicion=0;
			if(odesContienen){
				posicion=odeAlt.lastIndexOf("/");
			}else{
				posicion=odes[i].lastIndexOf("/");
			}
			titulo=odeAlt.substring(posicion+1,odes[i].length());

			String pathAlt=odeAlt.substring(0,posicion);
			if(pathAlt.indexOf("\\")!=(-1)){
				pathAlt=pathAlt.replace('\\', '/');
			}
			logger.info("El nombre del zip es "+titulo+" y el path restante "+ pathAlt+" siendo el pathCarga "+pathCarga);
			nombreZip=titulo;
			comentarios=nombreCarga+" "+titulo;

			//Sacaremos de odes[i] el nombre del zip y el path del zip. El path del zip deberá ser relativo al path de la carga por lo que 
			resultado[i].setNombreZip(titulo);
			boolean pathContiene=false;
			String pathCargaAlt=pathCarga;
			if(pathCarga.indexOf("\\")!= (-1)){
				pathCargaAlt=pathCargaAlt.replace('\\','/');
				pathContiene=true;
			}
			if(logger.isDebugEnabled())logger.debug("El nuevo pathAlt es "+pathAlt+ " y el nuevo pathCarga "+pathCargaAlt);
			String[] pathSplitado=pathAlt.split(pathCargaAlt);
			if(pathSplitado.length>1){
				pathZip=pathSplitado[1];//Para obtener la ruta relativa al pathCarga del ode, que es el que guardaremos en base de datos
				if(pathContiene){
					pathZip.replace('/', '\\');//Damos marcha atrás en la conversión, para guardarlo como nos lo ha escrito el usuario
				}
				if(logger.isDebugEnabled())logger.debug("El path relativo es "+pathZip);
			}else{
				pathZip="";
			}

			resultado[i].setPathZip(pathZip);
			idODE = String.valueOf(System.currentTimeMillis());
			resultado[i].setTituloOde(titulo);
			resultado[i].setSobrescrito("n");//será n por defecto
			try { // creacion
				logger.info("Publicando PIF (de carga) utilizando identificador[" + idODE + "] idUsuario[" + idUsuario
						+ "] y comentarios[" + comentarios + "]");

				ResultadoOperacionVO res = handleCreacion(idODE, idUsuario, comentarios, titulo);
				if (!res.getIdResultado().equals(SIN_ERRORES)) {
					logger.warn("ERROR[" + res.getIdResultado() + "][" + res.getDescripcion()
							+ "]:publicando PIF con fichero[" + odes[i] + "] idusuario["+ idUsuario + "] comentarios[" + comentarios + "] idODE["+ res.getIdODE());
					resultado[i].setIdResultado(res.getIdResultado());
					resultado[i].setDescripcion(res.getDescripcion());
					resultado[i].setIdODE( res.getIdODE());
					resultado[i].setTamainoODE(0L);
					continue;
				}
				SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
				LocalizadorVO localizadorNP = localizadorService.consultaLocalizador(idODE);

				logger.debug("Publicando PIF (de carga):Encontramos localizador con identificador[" + localizadorNP.getIdentificador() + "] usuario["
						+ localizadorNP.getIdUsuario() + "] MEC[" + localizadorNP.getMec() + "] path["
						+ localizadorNP.getPath() + "] URL[" + localizadorNP.getUrl() + "]");

				//Obtenemos el pif (dataHandler) del zip que se pasa en odes[i]
				FileDataSource fileDS = new FileDataSource(odes[i]);
				logger.debug("FileDataSource: " + fileDS.toString());
				DataHandler pif = new DataHandler(fileDS);
				logger.debug("DataHandler: " + pif.toString());
				
				// Empezamos a descomprimir el pif y guardar lo que nos devuelve en un directorio temporal
				String pathtemp = pifATemp(localizadorNP, pif);
				File ficheroZip = new File(pathtemp);
				if(logger.isDebugEnabled())
					logger.debug("Publicando PIF (de carga):Comenzamos a descomprimir el PIF "+ficheroZip.getPath()+ "" + "  y guardar en:" + pathtemp+";");
				if (this.getZipDao().esZip(pathtemp)){
					try {
						this.getZipDao().descomprimir(ficheroZip.getPath(), localizadorNP.getPath());
					} catch (Exception e1) {
						logger.error("Publicando PIF (de carga):Error descomprimiendo fichero [" + pathtemp + "], con nombre[" + pif.getName() + "]",e1);
						logger.debug("Borramos la carpeta "+ficheroZip); //+" si existe y tambien el zip que  esta en el temporal "+fileTemporal);
						UtilesFicheros.eliminar(ficheroZip);
						this.handleEliminar(idODE, idUsuario);
						resultado[i].setIdResultado(ERROR_DESCOMPRIMIENDO_FICHERO_ZIP);
						resultado[i].setDescripcion(I18nModuloPublicacion.getPropertyValueI18n(ERROR_DESCOMPRIMIENDO_FICHERO_ZIP));
						resultado[i].setIdODE(idODE);
						resultado[i].setTamainoODE(0L);
						continue;
					}
				}
				else {
					logger.warn("Error de formato descomprimiendo fichero [" + pathtemp + "], con nombre[" + pif.getName()+ "]");
					logger.debug("Borramos la carpeta "+ficheroZip); //+" si existe y tambien el zip que  esta en el temporal "+fileTemporal);
					UtilesFicheros.eliminar(ficheroZip);
					this.handleEliminar(idODE, idUsuario);
					resultado[i].setIdResultado(ERROR_FORMATO_ZIP);
					resultado[i].setDescripcion(I18nModuloPublicacion.getPropertyValueI18n(ERROR_FORMATO_ZIP));
					resultado[i].setIdODE(idODE);
					resultado[i].setTamainoODE(0L);
					continue;
				}

				// copiar los esquemas por si acaso no los trajese
				try {
					//aqui debemos chequear q tipo de ode es y hacer la conversion y copiar los esquemas correspondientes
					//SACAMOS EL TIPO DE ODE Q SE VA A CARGAR: SCORM 1.2, IMS CP O SCORM 2004
					es.pode.soporte.validador.TipoOde tipoOde = new es.pode.soporte.validador.TipoOde();
					tipoOde.obtenerTipoOde(localizadorNP.getPath() + "/" +MANIFEST_NAME);
					tipo = tipoOde.getTipo();
					if (logger.isDebugEnabled())logger.debug("EN HANDLEPUBLICARPIFCARGA EL TIPO DE ODE ES " + tipo);
					//Hacemos la transformación a scorm 2004	
					try {
						if (tipo !=null) {
							String oriTrans="";
							String destTrans="";
							TransformadorSaxonImpl transformador = new TransformadorSaxonImpl();					
							if (tipo.equals(ConstantesAgrega.SCORM_12)) {
								xsl=SrvPublicacionServiceImpl.getPropertyValue("xslt_scorm_12");
								if (logger.isDebugEnabled()) logger.debug("EN HANDLEPUBLICARPIFCARGA EL ODE ES SCORM 12");
							}else if (tipo.equals(ConstantesAgrega.IMS_CP)) {
								xsl=SrvPublicacionServiceImpl.getPropertyValue("xslt_ims_cp");
								if (logger.isDebugEnabled()) logger.debug("EN HANDLEPUBLICARPIFCARGA EL ODE ES IMSCP");
							}
							//Si xsl es "" es que no es ni scorm_12 ni ims_cp sino scorm2004
							if (!xsl.equals("")) {
								if (logger.isDebugEnabled()) logger.debug("EN HANDLEPUBLICAR EL VALOR DE xsl ES: " + xsl);
								oriTrans = localizadorNP.getPath() +"/" +MANIFEST_NAME;
								destTrans = localizadorNP.getPath() + "/imsmanifestTransformado.xml";
								transformador.transformar(xsl, oriTrans, destTrans);
								// Falta renombrar el imsmanifestTransformado a imsmanifest pq asi está mal!!
								File fichOri = new File(oriTrans);
								File fichDest = new File(destTrans);
								fichDest.renameTo(fichOri);
								//Una vez transformado el manifest a 2004, borramos los esquemas antiguos
								TratamientoODE.borrarEsquemas(localizadorNP.getPath(), tipo);
								if (logger.isDebugEnabled()) logger.debug("HEMOS BORRADO LOS ESQUEMAS DE " + localizadorNP.getPath() + " EL ODE ERA " + tipo);
							}
						}//fin if
					}catch (Exception et) {
						//Excepcion al realizar transformación a Scorm2004
						logger.error("Publicando PIF (de carga): No se pudo llevar a cabo la transformación al importar un pif: ", et);
						logger.debug("Borramos la carpeta "+ficheroZip); //+" si existe y tambien el zip que  esta en el temporal "+fileTemporal);
						UtilesFicheros.eliminar(ficheroZip);
						this.handleEliminar(idODE, idUsuario);
						resultado[i].setIdResultado(ERROR_EN_TRANSFORMACION);
						resultado[i].setDescripcion(I18nModuloPublicacion.getPropertyValueI18n(ERROR_EN_TRANSFORMACION));
						resultado[i].setIdODE(idODE);
						resultado[i].setTamainoODE(0L);
					}
					//copiamos los esquemas
					TratamientoODE.copiarEsquemas(localizadorNP.getPath());
					if (logger.isDebugEnabled()) logger.debug("COPIADOS LOS ESQUEMAS");

				} catch (Exception e1) {
					logger.error("Publicando PIF (de carga): No se pudieron copiar los esquemas al importar un pif: ", e1);
					logger.debug("Borramos la carpeta "+ficheroZip); //+" si existe y tambien el zip que  esta en el temporal "+fileTemporal);
					UtilesFicheros.eliminar(ficheroZip);
					this.handleEliminar(idODE, idUsuario);
					resultado[i].setIdResultado(ERROR_COPIANDO_ESQUEMAS);
					resultado[i].setDescripcion(I18nModuloPublicacion.getPropertyValueI18n(ERROR_COPIANDO_ESQUEMAS));
					resultado[i].setIdODE(idODE);
					resultado[i].setTamainoODE(0L);
					continue;
				}

				// validador
				SrvValidadorService validadorService = this.getSrvValidadorService();
				ValidarLicenciasVO validacionLic=null;
				logger.info("Publicando PIF (de carga):Validando el ODE [" + localizadorNP.getIdentificador() + "]");
				//Se llama a la misma validacion que el publicar normal, para que pueda validar los metadatos en ficheros externos referenciados con adcpl:location
				ValidaVO valid = validadorService.obtenerValidacion(localizadorNP.getPath());
				//ValidaVO valid = validadorService.validarCargaOde(localizadorNP.getPath());
				logger.info("Publicando PIF (de carga): Resultado validacion del ODE [" + localizadorNP.getIdentificador() + "]: "+valid.getEsValidoManifest().booleanValue());

				//Validamos licencias
				validacionLic = this.getSrvGruposLicencias().validarLicencias(localizadorNP.getPath());
				logger.info("Publicando PIF (de carga): Resultado validacion licencias del ODE [" + localizadorNP.getIdentificador() + "]: "+validacionLic.isResultado());
				
				if (valid.getEsValidoManifest().booleanValue() && validacionLic.isResultado()) {
					// proponiendo catalogacion
					logger.info("Publicando PIF (de carga):Proponemos para catalogacion el ODE con identificador[" + idODE + "], idUsuario["
							+ idUsuario + "] y comentarios[" + comentarios + "]");
//						Proponemos para catalogar, pero sin validar ya que ya lo hemos hecho
					this.proponerCatalogacion(idODE, idUsuario, comentarios, titulo, false,false);

					// proponer publicacion
					logger.info("Publicando PIF (de carga):Proponemos para publicacion el ODE con identificador[" + idODE + "], idUsuario["
							+ idUsuario + "] y comentarios[" + comentarios + "]");
//						Proponemos para publicar, pero sin validar ya que ya lo hemos hecho
					this.proponerPublicacion(idODE, idUsuario, comentarios, titulo, false);

					// publicar
					// llamada a un metodo "validadorService.validarMec" que devuelve null si el mec es malo o no existe
					// y devuelve el valor del mec, si éste, es bueno
									
					// 04082015 Se modifica la forma de obtener el mec para que lo obtenga tanto del fichero imsmanifest como 
					// de un fichero externo imslrm.xml si está referenciado. Antes no miraba en los externos.					
					//String mec = validadorService.validarMec(localizadorNP.getPath());
					
					File extraeSubmanifest = new File(localizadorNP.getPath(), MANIFEST_NAME);
					Manifest imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);
					ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
					String identifiadorManifest = manAgrega.getManifest().getIdentifier();
					Lom lom = manAgrega.obtenerLom(identifiadorManifest, null);
					LomAgrega lomAg = new LomAgrega(lom);
									
					String mec = lomAg.getGeneralAgrega().obtenerIdentificadorFormatoMEC(ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.CATALOGO_MEC));
					//Si el mec no es correcto sacamos un error no genera un nuevo mec.
					if (mec == null || mec.isEmpty()) {
						//Si el validador devuelve mec inválido no se recubre con otro y se desecha el ode
						logger.warn("El ode no tiene un id_mec valido");
						this.handleEliminar(idODE, idUsuario);
						logger.warn("Publicando PIF (de carga):El ODE [" + localizadorNP.getPath() + "] con mec[" + mec + "] no es válido.");
						logger.debug("Borramos la carpeta "+ficheroZip);  //+" si existe y tambien el zip que  esta en el temporal "+fileTemporal);
						UtilesFicheros.eliminar(ficheroZip);
						resultado[i].setIdResultado(MEC_ERRONEO);
						resultado[i].setDescripcion(I18nModuloPublicacion.getPropertyValueI18n(MEC_ERRONEO));
						resultado[i].setIdODE(mec);
						resultado[i].setTamainoODE(0L);
						continue;
					}
					IdODECriteria criteria = new IdODECriteria(mec, null, null);
					if (!this.getTransicionDao().buscarEstadoPorCriterioIdODE(criteria).isEmpty()) {			
						logger.info("Publicando PIF (de carga):El ODE [" + localizadorNP.getPath() + "] con mec[" + mec	+ "] ya existia en la plataforma.");
						if (SOBRESCRIBIR_PUBLICADOS_NO.equals(sobrescribir)) {
							// solo necesitamos comprobar la última transición, pq  el mec no cambia nunca
							// Eliminamos todo lo que ha producido el intento de insercion de este ODE 
							// que al final ha resultado repetido
							logger.debug("Borramos la carpeta "+ficheroZip ); //+" si existe y tambien el zip que  esta en el temporal "+fileTemporal);
							UtilesFicheros.eliminar(ficheroZip);
							this.handleEliminar(idODE, idUsuario);
							logger.warn("Publicando PIF (de carga):El ODE [" + localizadorNP.getPath() + "] con mec[" + mec+ "] ya existía y no lo sobrescribimos.");
							resultado[i].setIdResultado(MEC_YA_EXISTE);
							resultado[i].setDescripcion(I18nModuloPublicacion.getPropertyValueI18n(MEC_YA_EXISTE));
							resultado[i].setIdODE(mec);
							resultado[i].setTamainoODE(0L);
							continue;
						} else if (SOBRESCRIBIR_PUBLICADOS_SI.equals(sobrescribir)) {
							logger.info("Publicando PIF (de carga):El ODE [" + localizadorNP.getPath() + "] con mec[" + mec
									+ "] existe. Eliminamos rastro para sobrescribirlo.");
							eliminaODEPublicado(mec);
							sobrescrito=true;
							//Al objeto resultado[i] le asigno el valor de sobrescribir a true pq ya existe otro ode con ese mismo idMec y se va a machacar con el nuevo
						} else {
							logger.error("Publicando PIF (de carga):El mec ya existía y no hay criterio claro de sobrescritura[" + sobrescribir	+ "]");
							logger.debug("Borramos la carpeta "+ficheroZip ); //+" si existe y tambien el zip que  esta en el temporalp "+fileTemporal);
							UtilesFicheros.eliminar(ficheroZip);
							this.handleEliminar(idODE, idUsuario);
							resultado[i].setIdResultado(MEC_YA_EXISTE);
							resultado[i].setDescripcion(I18nModuloPublicacion.getPropertyValueI18n(MEC_YA_EXISTE));
							resultado[i].setIdODE(mec);
							resultado[i].setTamainoODE(0L);
							continue;
						}
					}

					EstadoVO estado = this.obtenEstadoPorIdODE(idODE, LdapUserDetailsUtils.getIdioma());
					logger.info("Publicando PIF (de carga):Publicando ODE con identificador [" + idODE + "] en estado[" + estado.getClave()
							+ "] con usuario[" + idUsuario + "] y comentarios[" + comentarios + "].");
					if (estado.getClave().equals(PROPUESTO)) {
						// los odes que se cargan masivamente (de momento) no tenemos que introducir información de licencias
						resultado[i] = publicar_aux_carga(idODE, mec, idUsuario, comentarios);
						resultado[i].setPathZip(pathZip);
						resultado[i].setNombreZip(nombreZip);
						if(sobrescrito){//En el publicar_aux_carga no tenemos porque saber si se ha sobrescrito o no
							resultado[i].setSobrescrito("s");
							sobrescrito=false;
						}

						if (!SIN_ERRORES.equals(resultado[i].getIdResultado())) {
							// Si la publicacion no ha ido bien, entendemos que ha tenido error y tenemos que borrar 
							// todo lo que el intento de publicacion ha creado en la plataforma
							logger.warn("Publicando PIF (de carga):Error intentando publicar un ODE via PIF[" + resultado[i].getIdResultado() + "] con id[" + idODE + "].");
							logger.debug("Borramos la carpeta "+ficheroZip ); //+" si existe y tambien el zip que  esta en el temporal "+fileTemporal);
							UtilesFicheros.eliminar(ficheroZip);
							this.handleEliminar(idODE, idUsuario);
						}
						else{
							logger.debug("Borramos la carpeta "+ficheroZip ); //+" si existe y tambien el zip que  esta en el temporal "+fileTemporal);
							UtilesFicheros.eliminar(ficheroZip);
							logger.info("Publicando PIF (de carga):Publicado ODE con identificador[" + idODE + "] a mec[" + mec + "] via PIF.");
						}
						continue;
					}
					logger.warn("Publicando PIF (de carga):error no se ha podido continuar ya que el ODE ya [" + mec + "], está creado");
					PublicarException e = new PublicarException("ERROR el ODE[" + mec+ "]ya esta creado en la plataforma");
					logger.warn(e);
					logger.debug("Borramos la carpeta "+ficheroZip ); //+" si existe y tambien el zip que  esta en el temporal "+fileTemporal);
					UtilesFicheros.eliminar(ficheroZip);
					resultado[i].setIdResultado(ODE_YA_CREADO_EN_PLATAFORMA);
					resultado[i].setDescripcion(I18nModuloPublicacion.getPropertyValueI18n(ODE_YA_CREADO_EN_PLATAFORMA));
					resultado[i].setIdODE(mec);
					resultado[i].setTamainoODE(0L);
					continue;
				}// if (valid.getEsValidoManifest().booleanValue())
				logger.warn("Publicando PIF (de carga):ERROR: no se ha validado el ODE [" + localizadorNP.getPath() + "]correctamente, código["
						+ valid.getResultadoValidacion() + "] ");
				this.handleEliminar(localizadorNP.getIdentificador(), idUsuario);			
				logger.debug("Borramos la carpeta "+ficheroZip ); //+" si existe y tambien el zip que  esta en el temporalp "+fileTemporal);
				UtilesFicheros.eliminar(ficheroZip);
				resultado[i].setIdResultado(ERROR_DE_VALIDACION);
				//Quitamos el último ; de la línea de mensajes
				String resValidacion= valid.getResultadoValidacion().substring(0, valid.getResultadoValidacion().length()-1);
				resultado[i].setDescripcion(resValidacion);
				resultado[i].setIdODE(idODE);
				resultado[i].setTamainoODE(0L);
				continue;
				
			}// try
			catch (PublicarException e) {
				logger.error("Publicando PIF (de carga):Se ha producido un error al publicarPIF del tipo PublicarException con ODE path["+resultado[i].getPathZip()+"] e id["+resultado[i].getIdODE()+"]", e);
				resultado[i].setIdResultado(EXCEPCION_INESPERADA);
				resultado[i].setDescripcion(I18nModuloPublicacion.getPropertyValueI18n(EXCEPCION_INESPERADA));
				resultado[i].setIdODE(idODE);
				resultado[i].setTamainoODE(0L);
//				throw e;
			} catch (Exception e) {
				logger.error("Publicando PIF (de carga):Se ha producido un error al publicarPIF del tipo Desconocido con ODE path["+resultado[i].getPathZip()+"] e id["+resultado[i].getIdODE()+"]", e);
				resultado[i].setIdResultado(EXCEPCION_INESPERADA);
				resultado[i].setDescripcion(I18nModuloPublicacion.getPropertyValueI18n(EXCEPCION_INESPERADA));
				resultado[i].setIdODE(idODE);
				resultado[i].setTamainoODE(0L);
//				throw e;
			}
		}
		boolean salida=getSrvCacheService().borrarHitCache();
		if(!salida) logger.debug("Error al borrar la cache.");
		return resultado;
	}

	/**
	 * 
	 * método auxiliar para la publicación masiva, que tiene la funcionalidad de la transición, mover el ode y la indexación.
	 * @throws Exception
	 */
	private ResultadoOperacionCargaVO publicar_aux_carga(java.lang.String idODE, String mec, java.lang.String idUsuario,
			java.lang.String comentarios)throws Exception {
		ResultadoOperacionCargaVO resultado = new ResultadoOperacionCargaVO();
		String titulo = "";
		String nivelAgregacion = "";
		String idioma = "";
		String sobrescrito = "n";
		String[] formatos = null;
		String listaFormatos="";
		String rutasTaxonomicas="";
		StringBuilder similares = new StringBuilder();
		SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
		SrvVocabulariosControladosService vocabulario = this.getSrvVocabulariosControladosService();
		LocalizadorVO localizadorVO = localizadorService.crearLocalizadorPublicado(idODE, mec);
		if (logger.isDebugEnabled())logger.debug("Creamos el nuevo localizador publicado para identificador[" + idODE + "] y MEC[" + mec + "]");
		if (localizadorVO.getMec().equals(""))
			// esto es así pq el mec ahora está en el identifier del VO
		{
			EstadoDao estadoDao = this.getEstadoDao();
			TransicionDao transicionDao = this.getTransicionDao();
			OdePublicadoDao idiomaODEPublicadoDao = this.getOdePublicadoDao();

			LocalizadorVO oldLocal = localizadorService.consultaLocalizador(idODE);
			if (logger.isDebugEnabled()) {
				logger.debug("Movemos el contendio del ODE no publicado con identificador["
						+ oldLocal.getIdentificador() + "] a la localizacion del identificador["+ localizadorVO.getIdentificador() + "] publicado");
				logger.debug("Movemos [" + oldLocal.getPath() + "] -> [" + localizadorVO.getPath() + "]");
			}
			File oldFile = new File(oldLocal.getPath());
			if (oldFile.exists()) {
				// es necesario moverlo primero, pero solo lo borraremos al final.
				File newFile = new File(localizadorVO.getPath());
				Long tamanioODE = UtilesFicheros.moveDir(oldFile, newFile);

				// Extraemos el manifest y modificamos el lom que queremos
				File extraeSubmanifest = new File(localizadorVO.getPath(), MANIFEST_NAME);
				Manifest imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);
				ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
				String identifiadorManifest = manAgrega.getManifest().getIdentifier();
				if(logger.isDebugEnabled())	logger.debug("Publicación de ode en carga masiva, ponemos ambito a universal;");
				Lom lom = manAgrega.obtenerLom(identifiadorManifest, null);
				LomAgrega lomAgrega = new LomAgrega(lom);

				lomAgrega.getRightsAgrega().setAcceso(SrvPublicacionServiceImpl.UNIVERSAL, "");
				// Recogemos todas las variables para el registro de  carga
				// Hacemos el set
				lom.setRights(lomAgrega.getRightsAgrega().getRights());
				if(logger.isDebugEnabled())	logger.debug("introducePublicadorManifest:Re-introduciendo Lom en Manifest");

				manAgrega.setLom(identifiadorManifest, null, lom);
				imsmanifest = manAgrega.getManifest();
				if(logger.isDebugEnabled())	logger.debug("introducePublicadorManifest:Lom modificado en objeto Manifest");

				/*
				 * Extrae la licencia del lomes, consulta con fuentes
				 * taxonomicas el id de licencia segun VDEX y copia el fichero
				 * licencia.txt correspondiente en el ODE
				 */
				if(logger.isDebugEnabled()) logger.debug("Comenzando la identificación de la licencia para copiar licencia.txt");
				if(lom!=null) {
					TratamientoODE.insercionLicenciaOde(localizadorVO, lom, vocabulario);
				}

				// Comprueba si es primera indexacion y en tal caso, introduce MEC en LOMES

				ScormDao scorm = (ScormDao) this.getScormDao();
				// Aniadimos la contribucion del publicador
				TratamientoODE.introducePublicadorManifest(localizadorVO.getPath(), idUsuario, imsmanifest, scorm);

				// Despues de la contribucion, tenemos que meter la localizacion del ODE en a pantalla de busqueda para que la ficha sea publica
				// y accesible en el caso de una busqueda por SQI.
				TratamientoODE.introduceLocalizacionWEB(localizadorVO.getPath(), idUsuario, imsmanifest, scorm, ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.CATALOGO_MEC));
				SrvNodoService nodo = this.getSrvNodoService();
				SrvEstructurasEducativasService estructuras = this.getSrvEstructurasEducativasService();
				SrvTaxonomiaService taxonomia = this.getSrvTaxonomiaService();

				// Aqui se hace el asunto de la imagen
				es.pode.indexador.negocio.servicios.indexado.IdODEVO idOdeVo = null;
				IndexadorVO res[] = null;
				es.pode.indexador.negocio.servicios.indexado.IdODEVO index[] = null;
				try {
					// 11042013 
					// Si en algun momento al realizar esta operacion se pierde la imagen del ode, se debe obtener la imagen actual del indice
					// y pasarsela como último parametro al TratamientoODE.rellenarIdOdeVO para que la mantenga
					// En el método reindexarODE hay un ejemplo que funciona correctamente.
					idOdeVo = TratamientoODE.rellenaIdOdeVO(// Utilizamos el Manifest Agrega como wrapper del manifest del ODE.
							new ManifestAgrega(imsmanifest),
							localizadorVO.getPath(),
							mec,
							"-1",
							new Float(tamanioODE.floatValue()),
							nodo,
							estructuras,
							taxonomia,
							this, 
							this.getSrvPropiedadService());
					index = new es.pode.indexador.negocio.servicios.indexado.IdODEVO[1];
					index[0] = idOdeVo;
					res = this.getSrvIndexadorService().indexarODE(index);
				} catch (Exception ex) {
					// Borramos la info y salimos, no hay que deshacer bbdd
					this.getSrvLocalizadorService().eliminarLocalizador(localizadorVO.getIdentificador());
					logger.error("No se ha podido indexar ODE con id[" + idODE + "] mec[" + mec + "] y usuario["
							+ idUsuario + "], no se copia a la carpeta de publicados: error en indexación",ex);
					return new ResultadoOperacionCargaVO(ERROR_INDEXACION,I18nModuloPublicacion.getPropertyValueI18n(ERROR_INDEXACION),mec,0L,titulo,idioma,nivelAgregacion,listaFormatos,sobrescrito,"","","","");
				}

				if (res == null || !(res.length > 0) || (res[0].getCode() == 1)) {
					this.getSrvLocalizadorService().eliminarLocalizador(localizadorVO.getIdentificador());
					logger.warn("No se ha podido indexar ODE con id[" + idODE + "] mec[" + mec + "] y usuario["+ idUsuario + "], no se copia a la carpeta de publicados.");
					logger.error("No se ha podido indexar ODE con id[" + idODE + "] mec[" + mec + "] y usuario["+ idUsuario + "], no se copia a la carpeta de publicados: error en indexación");
					return new ResultadoOperacionCargaVO(ERROR_INDEXACION,I18nModuloPublicacion.getPropertyValueI18n(ERROR_INDEXACION),mec,0L,titulo,idioma,nivelAgregacion,listaFormatos,sobrescrito,"","","","");
				}
				//Sacamos los nuevos atributos que se van a sacar en el informe de carga
				//Comprobar que todos los valores son correctos. Habrá que asignar estos valores al VO ResultadoOperacionCargaVO
				if(idOdeVo !=null && idOdeVo.getCatalogacionPrimaria()!=null){
					LomESPrimarioVO catalogacion = idOdeVo.getCatalogacionPrimaria();
					if(catalogacion.getTitulo()!=null){
						titulo=catalogacion.getTitulo();
					}
					if(logger.isDebugEnabled())
						logger.debug("titulo del manifest "+titulo);
					resultado.setTituloOde(titulo);
					resultado.setIdODE(mec);
					if(catalogacion.getNivelAgregacion()!=null){
						nivelAgregacion= catalogacion.getNivelAgregacion().toString();
					}
					if(logger.isDebugEnabled())
						logger.debug("nivelAgregacion del manifest "+nivelAgregacion);
					resultado.setNivelAgregacion(nivelAgregacion);
					if(catalogacion.getIdioma()!=null){
						idioma=catalogacion.getIdioma();
					}
					if(logger.isDebugEnabled())
						logger.debug("idioma del manifest "+idioma);
					resultado.setIdioma(idioma);

					//Los formatos se van a separar por comas habría que iterar sobre ellos concatenándolos una coma
					if(catalogacion.getFormatos()!=null && catalogacion.getFormatos().length>0){
						formatos=catalogacion.getFormatos();
					}
					if(formatos != null && formatos.length>0){
						listaFormatos=formatos[0].toString();
						for(int i=1;i<formatos.length;i++){
							listaFormatos=listaFormatos+","+formatos[i].toString();
						}
					}
					if(logger.isDebugEnabled())
						logger.debug("Los formatos del manifest "+listaFormatos);
					resultado.setFormato(listaFormatos);
					SrvTaxonomiaService taxonomias=this.getSrvTaxonomiaService();
					String arbolCurricularVigente = taxonomias.obtenerArbolVigente().getVocabIdentifier();
					if(arbolCurricularVigente.equals(null)){
						logger.error("El arbol curricular vigente es nulo");
						resultado.setRutaTaxonomica("");
					}else{
						if(logger.isDebugEnabled())
							logger.debug("El identificador del arbol curricular vigente es "+arbolCurricularVigente);
						if(catalogacion==null || catalogacion.getTaxonomia()==null || !(catalogacion.getTaxonomia().length>0)){
							resultado.setRutaTaxonomica("");
						}else{
							String[] todasRutas=catalogacion.getTaxonomia();
							if(logger.isDebugEnabled())logger.debug("Obtenemos "+todasRutas.length+" rutas taxonomicas");
							ArrayList<String> ides=new ArrayList<String>();
							for(int i=0;i<todasRutas.length;i++){
								String textoRuta=todasRutas[i];
								String[] ruta = textoRuta.split("/");
								if(ruta[0].equals(arbolCurricularVigente) && ruta.length>1){
									logger.debug("Recogemos el último identificador del arbol vigente");
									int longitud = ruta.length;
									String text=ruta[longitud-1];
									ides.add(text);
								}
							}
							String textoCompleto="";
							if(!(ides.size()>0)){
								resultado.setRutaTaxonomica("");
							}else{
								String[] identificadores=ides.toArray(new String[ides.size()]);
								TaxonPathVO[] rutasTexto = this.getSrvTaxonomiaService().obtenerVariosTaxonPaths(identificadores, taxonomias.obtenerArbolVigente().getVocabName(), "es");
								if(logger.isDebugEnabled())logger.debug("Hemos recibido "+rutasTexto.length+" rutas completas");
								if(rutasTexto!=null && rutasTexto.length>0){
									StringBuilder textoAux= new StringBuilder();
									for(int j=0;j<rutasTexto.length;j++){
										TaxonVO[] taxones=rutasTexto[j].getDatos();
										StringBuilder textoRuta= new StringBuilder("");
										int ultimaPosicion = (taxones.length-1);
										if(ultimaPosicion==0){
											if(logger.isDebugEnabled())logger.debug("La ruta solo tiene un taxón");
											String texto=taxones[0].getValorTax();
											textoAux.append('\"'+texto+'\"'+";");

										}else{
											for(int k=ultimaPosicion;k>=0;k--){
												String texto=taxones[k].getValorTax();
												if(k==ultimaPosicion){
													textoRuta=new StringBuilder('\"'+texto+ "/");
												}else if(k==0){
													textoRuta.append(texto+'\"'+";");
												}else{
													textoRuta.append(texto+"/");
												}
											}
											textoAux.append(textoRuta);
										}
									}
									textoCompleto=textoAux.toString();
								}else{
									logger.warn("No nos devuelven ninguna ruta taxonomica");
									resultado.setRutaTaxonomica("");
								}
								if(textoCompleto!=null && textoCompleto.length()>0){
									int longi=textoCompleto.length();
									rutasTaxonomicas=textoCompleto.substring(0, longi-1);
									if(logger.isDebugEnabled())logger.debug("El texto de los taxones es "+textoCompleto);
									resultado.setRutaTaxonomica(rutasTaxonomicas);
									String textoCorto="";
									if(rutasTaxonomicas.length()>2000){
										textoCorto=rutasTaxonomicas.substring(0, 1995)+"...";
										if(logger.isDebugEnabled())logger.debug("El texto cortado es "+textoCorto);
										resultado.setRutaTaxonomica(textoCorto);
									}
								}else{
									resultado.setRutaTaxonomica("");
								}
							}
						}
					}
				}else{
					logger.warn("No se han podido sacar los datos");
					resultado.setRutaTaxonomica("");
				}
				if (logger.isDebugEnabled())
					logger.debug("Creamos la transicion del identificador[" + localizadorVO.getIdentificador()
							+ "] con estado actual[PUBLICADO]");

				//TODO: revisarlo.
				/*CALCULO LOS DUPLICADOS, DE LOS ODES QUE VOY A PUBLICAR; */
				OdeSimilarVO[] odesSimilares = this.calcularDuplicadosPublicados(idODE);
				for (int i = 0; odesSimilares!= null && i < odesSimilares.length; i++) {
					if(i==(odesSimilares.length-1)){
						similares.append(odesSimilares[i].getIdODE()+"("+odesSimilares[i].getSimilitud()+")");
					}else{
						similares.append(odesSimilares[i].getIdODE()+"("+odesSimilares[i].getSimilitud()+")"+",");
					}
				}
				
				
				// Actualizamos la transicion actual
				Transicion transicionActual = handleObtenerUltimaTransicion(idODE);
				transicionActual.setEstadoTransitado(estadoDao.obtenEstadoPorNombre(PUBLICADO));
				transicionDao.update(transicionActual);
				// Creamos la transicion de ODE publicado
//					Transicion transicionAux = handleObtenerUltimaTransicion(idODE);
				Date fecha = new Date(System.currentTimeMillis());
				transicionDao.create(DateManager.dateToCalendar(new Date(System.currentTimeMillis())), comentarios, mec, idUsuario, idOdeVo
						.getCatalogacionPrimaria().getTitulo(), transicionActual.getIdUsuarioCreacion(),// arrastramos el usuario de creacion del ODE.
						fecha.getTime(),false, null, null, estadoDao.obtenEstadoPorNombre(PUBLICADO));
				// Creamos la nueva entrada del idioma en el que se ha publicado el ode
				idiomaODEPublicadoDao.create(mec, idOdeVo.getCatalogacionPrimaria().getIdioma(), tamanioODE,
						DateManager.dateToCalendar(new Date(System.currentTimeMillis())), idOdeVo.getCatalogacionPrimaria().getTitulo(),
						TratamientoODE.obtenerMD5FromODE(imsmanifest));
				// Borramos la vieja info
				UtilesFicheros.eliminar(oldFile);

			} else {
				logger.warn("ERROR: Intentando publicar ODE con identificador [" + idODE + "]  con usuario["
						+ idUsuario + "] y comentarios[" + comentarios + "]. El fichero original[" + oldFile.getPath()+ "] no existe.");
				return new ResultadoOperacionCargaVO(FICHERO_ORIGINAL_ODE_NO_EXISTE,I18nModuloPublicacion.getPropertyValueI18n(FICHERO_ORIGINAL_ODE_NO_EXISTE),idODE,0L,titulo,idioma,nivelAgregacion,listaFormatos,sobrescrito,"","","","");
			}
		} else {
			logger.warn("ERROR: Intentando publicar ODE con identificador [" + idODE + "]  con usuario[" + idUsuario+ "] y comentarios[" + comentarios
					+ "]. El identificador del ODE tiene una lozalizacion invalida => MEC[" + localizadorVO.getMec()+ "]");
			return new ResultadoOperacionCargaVO(IDENTIFICADOR_ODE_LOCALIZACION_INVALIDA,I18nModuloPublicacion.getPropertyValueI18n(IDENTIFICADOR_ODE_LOCALIZACION_INVALIDA),idODE,0L,titulo,idioma,nivelAgregacion,listaFormatos,sobrescrito,"","","","");
		}
		resultado.setIdResultado(SIN_ERRORES);
		resultado.setNombreZip("");
		resultado.setPathZip("");
		resultado.setSobrescrito(sobrescrito);
		resultado.setTamainoODE(0L);
		resultado.setDescripcion(I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES));
		resultado.setOdesSimilares(similares.toString());
		if(logger.isDebugEnabled())logger.debug("Devolvemos los datos correctos (publicar_aux_carga) , sin errores , la descripcion ["+resultado.getDescripcion()+"] el idioma ["+resultado.getIdioma()+ "] el nivelDeAgregacion ["+resultado.getNivelAgregacion()+"], la listas de formatos ["+resultado.getFormato()+"] y las rutas taxonomicas ["+resultado.getRutaTaxonomica() +"] ode: ["+ resultado.getIdODE() +"] con titulo: [" + resultado.getTituloOde() +"] y odes similares ["+ resultado.getOdesSimilares()+"]");
		return  resultado;
	}

	/**
	 * Elimina los ODEs especificados siempre y cuando estén en estado CREACION,
	 * CATALOGACION o PROPUESTO. Esta operación se realiza en los casos en que
	 * se rechaza un ODE de un usuario que se ha eliminado.
	 * 
	 * @param identificadores Identificadores de los ODEs que se desean eliminar.
	 * @return Bolean con el resultado de la eliminación
	 */
	@Override
	protected Boolean handleEliminarIdODEForzado(String[] identificadores)
	throws Exception {
		boolean resultado=true;
		for(int i=0;i<identificadores.length;i++){
			logger.debug("Obtenemos la trasicion del ode que queremos borrar "+identificadores[i]);
			EstadoVO actual=this.obtenEstadoPorIdODE(identificadores[i], LdapUserDetailsUtils.getIdioma());
			if(actual.getClave().equals(SrvPublicacionServiceImpl.CREACION) ||
					actual.getClave().equals(SrvPublicacionServiceImpl.PROPUESTO_CATALOGACION)||
					actual.getClave().equals(SrvPublicacionServiceImpl.PROPUESTO)){
				logger.debug("El usuario["+LdapUserDetailsUtils.getUsuario()+"] elimina el ode["+identificadores[i]+"]");
				ResultadoOperacionVO vuelta = handleEliminar(identificadores[i], LdapUserDetailsUtils.getUsuario());
				//¿Se puede borrar la carpeta temp?  Si se puede borrar, si la carpeta temp está vacía y la carpeta del usuario está vacío eliminarlo.
				// ¿Se pude borrar la carpeta temp?--> Ver que no hay más elementos y eliminar la  carpeta del usuario
				if(!vuelta.getIdResultado().equals(SIN_ERRORES)){
					resultado=false;
				}
			}else{
				logger.warn("El ode no tiene el estado adecuado para ser borrado, su estado actual es ["+actual.getClave()+"]");
				resultado = false;
			}
		}
		return resultado;
	}

	/**
	 * Elimina los ODEs de los usuarios pasados como parámetro y que estan en
	 * creación o rechazados.
	 * 
	 * @param usuarios
	 *            Identificadores de los usuarios cuyos ODEs se desean eliminar.
	 * @return Bolean con el resultado de la eliminación
	 */
	@Override
	protected Boolean handleEliminarOdesUsuarios(String[] usuarios)
	throws Exception {
		boolean resultado=true;
		for(int i=0;i<usuarios.length;i++){
			TransicionVO[] odesUsuario = SrvPublicacionServiceImpl.obtenerODEsPorDosEstadosUsuario(usuarios[i], SrvPublicacionServiceImpl.CREACION, SrvPublicacionServiceImpl.RECHAZADO, this.getEstadoDao(), this.getTransicionDao());
			logger.debug("El usuario "+ usuarios[i]+" tenia " +odesUsuario.length+" odes en estado de creacion y rechazados");
			ResultadoOperacionVO vueltaCreacion=null;
			logger.info("Vamos a eliminar los odes del usuario "+ usuarios[i]);
			for(int j=0;j<odesUsuario.length;j++){
				logger.debug("Vamos a eliminar el ode "+ odesUsuario[j].getIdODE()+ "en estado de creacion/rechazado");
				vueltaCreacion = handleEliminar(odesUsuario[j].getIdODE(), odesUsuario[j].getIdUsuario());
				if(!vueltaCreacion.getIdResultado().equals(SIN_ERRORES)){
					logger.warn("No se ha podido borrar el ode con identificador "+odesUsuario[j].getIdODE()+" en estado de creacion/rechazado");
					resultado=false;
				}
			}
			//Se debe dejar la carpeta temp, porque el empaquetador lo utiliza; para que no se de el caso de que algún catalogador este modificando el ode
			// y un administrador borre el usuario y se quede sin carpeta temp
		}
		return resultado;
	}

	/**
	 * Este método nos devuelve un array de usuarios que son los creadosres de los identificadores de los odes que le pasamos
	 * 
	 * @param identificadores
	 * 			Identificadores de los odes que queremos saber quien lo creo
	 * 
	 * @return usuarios
	 * 			Array de usuarios que crearon esos odes
	 */
	protected String[] handleObtenerUsuariosCreacionDeIdentificadores(String[] identificadores) throws Exception {
		String[] usuarios=new String[identificadores.length];
		for(int i=0;i<identificadores.length;i++){
			Transicion transicion = obtenerUltimaTransicion(identificadores[i]);
			String usuarioCreacion=transicion.getIdUsuarioCreacion();
			usuarios[i]=usuarioCreacion;
		}
		return usuarios;
	}
	
	
	/**
	 * Este método devuelve la lista de usuarios creadores de los ODEs publicados cuyos identificadores se pasan.
	 * Si el identificador que se le pasa no tiene ODE publicado asociado, se devuelve "" en el nombre del usuario de creación.
	 * Si el ODE se creó de forma masiva, no se devuelve el usuario de creación.
	 * 
	 * @param identificadores
	 * 			Identificadores de los odes publicados que queremos saber quien lo creo
	 * 
	 * @return usuarios
	 * 			Array de usuarios que crearon esos odes
	 */
	protected String[] handleObtenerUsuariosCreacionPublicadosIdentificadores(String[] idOdes) throws Exception {
		
//		TODO: hay que hacer que este metodo haga lo que dice. Ahora hacemos un dummy que devuelve el usuario de creación
		if (idOdes == null || idOdes.length == 0)
		{
			logger.warn("Error realizando consulta de usuarios de creacion de odes publicados. Lista de identificadores vacía["+(idOdes==null?0:null)+"].");
			return new String[0];
		}
		return handleObtenerUsuariosCreacionDeIdentificadores(idOdes);
	}

	/**
	 * Este método publica autonomamente un ODE
	 * @param idODE     				Identificador del ODE.
	 * @param idUsuario  				Identificador del usuario que realiza la operacion
	 * @param titulo     				Titulo del ODE.
	 * 
	 * @return ResultadoOperacionVO Devuelve un VO con el error que se ha producido en el caso
	 *         de detectarse algun problema.
	 * @throws Exception
	 */
	protected ResultadoOperacionVO handlePublicarAutonomo(String idODE, String idUsuario, String titulo, String comentarios) throws Exception {
		try {
			EstadoVO estado = this.obtenEstadoPorIdODE(idODE, LdapUserDetailsUtils.getIdioma());
			if(logger.isDebugEnabled())
				logger.debug("Pasando el  ODE con identificador[" + idODE + "] usuario[" + idUsuario
						+ "]comentarios [" + comentarios+ "] y en estado actual[" + estado + "] al estado Publicado_Autonomo");
			if (estado != null && (estado.getClave().equals(SrvPublicacionServiceImpl.CREACION) || estado.getClave().equals(
					SrvPublicacionServiceImpl.RECHAZADO))) {
				// validador
				SrvValidadorService validadorService = this.getSrvValidadorService();
				SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
				LocalizadorVO localizadorNP = localizadorService.consultaLocalizador(idODE);
//				Hacemos la validacion que nos devuelve el texto de error menos detallado.
				boolean ODEvalido = false;
				ValidaVO valid = null;
				valid = validadorService.obtenerValidacionBin(localizadorNP.getPath());
				ODEvalido = valid.getEsValidoManifest().booleanValue();
				if(logger.isDebugEnabled()) logger.debug("Validando el ODE [" + localizadorNP.getPath() + "]");
				
				if(!(ODEvalido)){
					logger.warn("ERROR: publicando autonomamente el ODE con identificador.[" + idODE	+ "] en estado[" + estado.getClave() + "]. El ode no valida correctamente: "
							+ valid.getResultadoValidacion());
					return new ResultadoOperacionVO(SrvPublicacionServiceImpl.ERROR_DE_VALIDACION, valid.getResultadoValidacion(),idODE,0L);
				}
				//	Desasociamos el ODE del posible albun al que este asociado. Si no esta en ningún album, no se hace nada.
				try{
					this.getSrvAlbumService().desasociarODEAlbum(idODE);		
				}catch(Exception ex){
					logger.error("ERROR: desasociando el ode[" + idODE+ "] del album al que esta asociado.",ex);
				}
				EstadoDao estadoDao = this.getEstadoDao();
				TransicionDao transicionDao = this.getTransicionDao();
				Transicion transicionActual = handleObtenerUltimaTransicion(idODE);
				transicionActual.setEstadoTransitado(estadoDao
						.obtenEstadoPorNombre(SrvPublicacionServiceImpl.PUBLICADO_AUTONOMO));
				transicionDao.update(transicionActual);
				logger.info("Actualizamos estado de ODE con identificador[" + idODE	+ "] a PUBLICADO_AUTONOMO.");
				Date fecha = new Date(System.currentTimeMillis());
				transicionDao.create(DateManager.dateToCalendar(fecha), comentarios, idODE, idUsuario, titulo, transicionActual
						.getIdUsuarioCreacion(),// arrastramos el usuario de 
						fecha.getTime(),false, null, null, estadoDao.obtenEstadoPorNombre(SrvPublicacionServiceImpl.PUBLICADO_AUTONOMO));
				boolean salida=getSrvCacheService().borrarHitCache();
				if(!salida) logger.debug("Error al borrar la cache.");
				return new ResultadoOperacionVO(SIN_ERRORES, I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES),idODE,0L);
			}
			logger.warn("ERROR: publicando autonomamente el ODE con identificador[" + idODE
					+ "]. El ODE en estado[" + estado.getClave()+ "] no se puede pasar a estado PUBLICADO_AUTONOMO.");
			return new ResultadoOperacionVO(TRANSICION_ESTADO_INVALIDA,I18nModuloPublicacion.getPropertyValueI18n(TRANSICION_ESTADO_INVALIDA),idODE,0L);
		} catch (Exception e) {
			Exception propuesto = new Exception(
					"Excepción en la publicación autóma del ODE con identificador", e);
			logger.error("Excepción en la publicacion autonoma del ODE con identificador[" + idODE
					+ "] idUsuario[" + idUsuario +"]comentarios [" + comentarios + "] y titulo[" + titulo + "]",propuesto);
			throw propuesto;
		}
	}
	
	/**
	 * Este metodo devuelve el ODE publicado por el identificador que le pasan.
	 * @param idODE     				Identificador del ODE.
	 * 
	 * @return OdePublicadoVO Devuelve un VO con la información del ODE publicado.
	 * @throws Exception
	 */
	protected OdePublicadoVO handleObtenODEPublicado(String idODE) throws Exception {
		if (idODE == null || idODE.equals(""))
		{
			logger.warn("Error consultando ODE publicado con identificador vacío.");
			throw new Exception("Error consultando ODE publicado con identificador vacío.");
		}
		IdODEPublicadoCriteria criterio = new IdODEPublicadoCriteria(idODE);
		List resultados = this.getOdePublicadoDao().obtenODEsPublicadosPorID(getOdePublicadoDao().TRANSFORM_ODEPUBLICADOVO, criterio);
		if (resultados == null || resultados.size() == 0)
		{
			logger.warn("Error consultando ODE publicado con identificador ["+idODE+"]. El ODE no esta publicado.");
			throw new Exception("Error consultando ODE publicado con identificador ["+idODE+"]. El ODE no esta publcado.");
		}
		return ((OdePublicadoVO[])(resultados.toArray(new OdePublicadoVO[0])))[0];
	}

	/**
	 * Este método pasa a no disponibles la lista de los ODEs que le pasan que tienen que estar en estado publicado. Se comporta como "noDisponible" pero teniendo en cuenta varios ODEs.
	 * @param idODEs[]     	Array de identificadores de ODEs publicados que se quieren eliminar.
	 * @param idUsuario     Identificador del usuario que realiza la operación.
	 * @param comentario    Comentario de la operación.
	 * @return ResultadoOperacionVO[] Devuelve VO's con los resultados de las operaciones.
	 * @throws Exception
	 */
	protected ResultadoOperacionVO[] handleNoDisponibles(String[] idODEs, String idUsuario, String comentario) throws Exception {
		// TODO Hay que terminar de rellenar este método para que elimine los ODEs publicados que le pasan en la lista.
//		 compruebo que la lista no es vacía.
//		 compruebo de la lista los que están realmente publicados.
//	     de los que no están, ya me quejo, y trabajo con una lista con los ODEs que si que estan publicados.
//	     llamo al despublicador unitario para tener el detalle de cada uno de los que despublico o planteamos la despublicación en masa 
//	     lo que puede ser un peligro para tener en cuenta las operaciones transaccionales.
		
		//Higiene de datos
		if(idODEs == null || idODEs.length==0){
			logger.warn("Error invocando al método NoDisponibles, con identificadores nulos.");
			throw new Exception("Error invocando al método NoDisponibles, con identificadores nulos");
		}
		if (idUsuario == null) {
			logger.warn("Error invocando al método NoDisponibles, con idUsuario nulo.");
			throw new Exception("Error invocando al método NoDisponibles, con idUsuario nulo.");
		}
		if (comentario == null) {
			logger.warn("Error invocando al método NoDisponibles, con comentario nulo.");
			throw new Exception("Error invocando al método NoDisponibles, con comentario nulo.");
		}
		//De los identificadores que nos pasan, recojo aquellos que pertenezcan a ODEs publicados.
		IdODEsCriteria criterio = new IdODEsCriteria(idODEs);
		List<OdePublicado> listaODESPublicados = this.getOdePublicadoDao().obtenODEsPublicadosDeConjunto(criterio);
//		Tengo que recorrer la lista de los que me pasan y están publicados para sacar sus id's. Mapeo la lista para sacar sus identificadores.
		ArrayList<String>  idsODESPublicados= new ArrayList<String>(); //id's de los odes q me pasan y estan publicados.
		ArrayList<ResultadoOperacionVO>  listaResultados= new ArrayList<ResultadoOperacionVO>(); //resultados operacion
		
		try{
			for (int i = 0; i < listaODESPublicados.size(); i++) {
				idsODESPublicados.add(listaODESPublicados.get(i).getIdODE());
			}
			if(logger.isDebugEnabled()) logger.debug("De los ["+ idODEs.length +"] odes que me pasan, hay ["+ idsODESPublicados.size()+ "] que estan publicados.");
	//		Con estos idsODESPublicados, lo tramito para despublicar
	//		Los comentarios, valoraciones, y tagging no se eliminan ahora (al despublicar), sino cuando eliminemos el ode.
	//		Desindexamos, para que al buscar alguno de los ODEs, no aparezcan
			if(listaODESPublicados.size()>0){
				IndexadorVO[] resultadoIndexacion = this.getSrvIndexadorService().eliminarODE(idsODESPublicados.toArray(new String[0]));
				if(logger.isDebugEnabled()) logger.debug("He desindexado ["+ resultadoIndexacion.length +"] odes.");
				ArrayList<String> listaNoDesindexados = new ArrayList<String>();
				for (int j = 0; j < resultadoIndexacion.length; j++) {
					if(resultadoIndexacion[j].getCode()!= 0){
						//Ha habido errores 'desindexando', y no sigo.
						listaResultados.add(new ResultadoOperacionVO(ERROR_DESINDEXACION, I18nModuloPublicacion.getPropertyValueI18n(ERROR_DESINDEXACION), idsODESPublicados.get(j), 0L));
						listaNoDesindexados.add(idsODESPublicados.get(j));
						if(logger.isDebugEnabled()) logger.debug("tamaño listaResultados: ["+ listaResultados.size()+"] y la lista de odes desindexados ["+ listaNoDesindexados.size()+ "] ");
					}
				}
				idsODESPublicados.removeAll(listaNoDesindexados); // A la lista de todos los ODEs publicados le quito aquellos que no he podido desindexar.
				
				//Optimizamos el indice, pues al realizar un borrados o reindexado se generan archivos .del que luego hacen que las operaciones
				//con los terminos indexados sean incorrectas o no reales
				if (logger.isDebugEnabled()) logger.debug("Vamos a optimizar el indice ...");
				this.getSrvIndexadorService().optimizarIndice(null);//con null optimizamos todos los subindices
			
//				Si los ODEs han fallado en la reindexación los saco de la lista.
//				La 'desindexacion' ha sido correcta, eliminamos de la tabla de odes publicados
				this.getOdePublicadoDao().eliminaODEsPublicadosPorId(idsODESPublicados.toArray(new String[0]));
//				Si todo ha ido bien, modificamos la última transicion y creamos una nueva.
				if(logger.isDebugEnabled()) logger.debug("He eliminado de la tabla de publicados: ["+ idsODESPublicados.size()+"] odes");
				IdODEsEstadoCriteria idODEsEstadocriterio = new IdODEsEstadoCriteria(idsODESPublicados.toArray(new String[0]),null,this.getEstadoDao().obtenEstadoPorNombre(PUBLICADO));
				List ultimasTransicionesPublicados = this.getTransicionDao().buscarODEsPorCriterioIdODESEstado(idODEsEstadocriterio);
				if(logger.isDebugEnabled()) logger.debug("UltimastransicionesPublicados: ["+ ultimasTransicionesPublicados.size()+"] ");
				List<Transicion> nuevasTransiciones = new ArrayList<Transicion>();
				for (int i = 0; i < ultimasTransicionesPublicados.size(); i++) {
					if(logger.isDebugEnabled()) logger.debug("Vamos a entrar a crear la nueva transicion: ["+ idsODESPublicados.size()+"] odes y esta es la ["+ i +"]");
					((Transicion)ultimasTransicionesPublicados.get(i)).setEstadoTransitado(this.getEstadoDao().obtenEstadoPorNombre(NO_DISPONIBLE));
					TransicionImpl newTran = new TransicionImpl();
					newTran.setAlbum(null);
					newTran.setComentarios(DESPUBLICACION_MASIVA);
					newTran.setCompartido(false);
					newTran.setEstadoActual(this.getEstadoDao().obtenEstadoPorNombre(NO_DISPONIBLE));
					newTran.setEstadoTransitado(null);
					Date fecha = new Date(System.currentTimeMillis());
					newTran.setFecha(DateManager.dateToCalendar(fecha));
					newTran.setMilisegundos(fecha.getTime());
					newTran.setIdODE(((Transicion)ultimasTransicionesPublicados.get(i)).getIdODE());
					newTran.setIdUsuario(idUsuario);
					newTran.setIdUsuarioCreacion(((Transicion)ultimasTransicionesPublicados.get(i)).getIdUsuarioCreacion());
					newTran.setTitulo(((Transicion)ultimasTransicionesPublicados.get(i)).getTitulo());
					nuevasTransiciones.add(newTran); 
				}
//				Modifico la última transición, con estadoTransitado == NO_DISPONIBLE
				this.getTransicionDao().update(ultimasTransicionesPublicados);
				if(logger.isDebugEnabled()) logger.debug("He modificado : ["+ ultimasTransicionesPublicados.size()+"] transiciones");
				//Añado una nueva transición, con el estadoActual == NO_DISPONIBLE y estadoTransitado == NULL;
				this.getTransicionDao().create(nuevasTransiciones);
				if(logger.isDebugEnabled()) logger.debug("He creado : ["+ nuevasTransiciones.size()+"] transiciones");
				//Los odes publicados pueden pertenecer o no al perfil público del usuario. En ese caso los tengo que eliminar.
				//Si la eliminacion de estos ODEs va mal, yo sigo trabajando.
				this.getSrvPerfilPublico().eliminarODEPerfilPublico(idsODESPublicados.toArray(new String[0]));
				
//				Vamos a borrar la carpeta temporal que se genera al hacer una descarga si existe
				try{
					this.getSrvEntregarService().eliminarTemporales(idsODESPublicados.toArray(new String[0]));
					if(logger.isDebugEnabled())logger.debug("Hemos eliminado los ficheros temporales de: ["+ idsODESPublicados.size()+"] identificadores.");
				}catch(Exception ex){
					logger.error("Error eliminando los ficheros temporales de : ["+ idsODESPublicados.size()+"] identificadores al hacer una despublicacion masiva.", ex);
					for(int j=0; j<idsODESPublicados.size(); j++){
						if(logger.isDebugEnabled()) logger.debug("Texto: ["+ ERROR_FICHEROS_TEMPORALES +"] traduccion: ["+I18nModuloPublicacion.getPropertyValueI18n(ERROR_FICHEROS_TEMPORALES)+"] idODEs ["+ idsODESPublicados.get(j) +"]");
						listaResultados.add(new ResultadoOperacionVO(ERROR_FICHEROS_TEMPORALES, I18nModuloPublicacion.getPropertyValueI18n(ERROR_FICHEROS_TEMPORALES),idsODESPublicados.get(j), 0L));
						if(logger.isDebugEnabled()) logger.debug("tamaño listaResultados: ["+ listaResultados.size()+"]");
					}
				}
				
				//Invalido la caché de resultados
				logger.debug("Ahora llamamos a borrar cache");
				getSrvCacheService().borrarHitCache();
				logger.debug("Se llamo a borrar cache");
				//Todo ha ido bien, llamo al servicio de Auditoria para que se actualice el estado de esos registros en las tablas de auditoría
				this.getSrvAuditoriaServicio().despublicarODEs(idsODESPublicados.toArray(new String[0]));
				for (int i = 0; i < idsODESPublicados.size(); i++) {
					if(logger.isDebugEnabled()) logger.debug("Texto: ["+ SIN_ERRORES +"] traduccion: ["+I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES)+"] idODEs ["+ idsODESPublicados.get(i) +"]");
					listaResultados.add(new ResultadoOperacionVO(SIN_ERRORES, I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES), idsODESPublicados.get(i), 0L));
					if(logger.isDebugEnabled()) logger.debug("tamaño listaResultados: ["+ listaResultados.size()+"]");
				}
				
			}
				
			
		}catch(Exception e){
			logger.error("Fallo en la despublicación masiva de ["+idsODESPublicados.size()+"] odes",e);
			throw e;
		}
		//De los identificadores que me han pasado, me quedan unos cuantos odes que no estan publicados. Puede que esten despublicados,
		//eliminados, o no existan en la plataforma.
		
		ArrayList<String> listaNoPublicados = new ArrayList<String>();
		listaNoPublicados.addAll(Arrays.asList(idODEs));
		listaNoPublicados.removeAll(idsODESPublicados);
//		De la lista original, ya no me queda nada que despublicar, pues devuelvo los resultados que tenga.
		if(listaNoPublicados.isEmpty()){
			logger.info("Hemos despublicado masivamente todos los ODES que nos han pasado:["+idODEs.length+"]elementos");
			return (listaResultados.toArray(new ResultadoOperacionVO[0]));
		}
//		En listaNoPublicados deben estar los odes no publicados de la lista de identificadores que me han pasado
		if(logger.isDebugEnabled())logger.debug("Me quedan: ["+ listaNoPublicados.size()+"] identificadores por despublicar.");
		IdODEsEstadoCriteria idODEsNoPublicadoscriterio = new IdODEsEstadoCriteria(listaNoPublicados.toArray(new String[0]),null,this.getEstadoDao().obtenEstadoPorNombre(NO_DISPONIBLE));
		List ultimasTransicionesNoDisponibles = this.getTransicionDao().buscarODEsPorCriterioIdODESEstado(idODEsNoPublicadoscriterio);
		List<String> idsDespublicados = new ArrayList<String>();
		for (int i = 0; i <ultimasTransicionesNoDisponibles.size();  i++) {
			idsDespublicados.add(((Transicion)ultimasTransicionesNoDisponibles.get(i)).getIdODE());
			if(logger.isDebugEnabled()) logger.debug("Texto: ["+ SIN_ERRORES +"] traduccion: ["+I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES)+"] idODEs ["+ idsDespublicados.get(i) +"]");
			listaResultados.add(new ResultadoOperacionVO(SIN_ERRORES, I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES), idsDespublicados.get(i), 0L));
			if(logger.isDebugEnabled()) logger.debug("tamaño listaResultados: ["+ listaResultados.size()+"]");
		}
		listaNoPublicados.removeAll(idsDespublicados);
		if(listaNoPublicados.isEmpty()){
			logger.info("Hemos despublicado masivamente (publicados y despublicados)todos los ODES que nos han pasado: ["+idODEs.length+"] elementos");
			return (listaResultados.toArray(new ResultadoOperacionVO[0]));
		}
//		En listaNoPublicados deben estar los odes eliminados y que no existan en la plataforma.
		if(logger.isDebugEnabled())logger.debug("Me quedan: ["+ listaNoPublicados.size()+"] identificadores por despublicar (ODEs eliminados o que no existen en la plataforma.");
		IdODEsEstadoCriteria idODEsEliminadoscriterio = new IdODEsEstadoCriteria(listaNoPublicados.toArray(new String[0]),null,this.getEstadoDao().obtenEstadoPorNombre(ELIMINADO));
		List ultimasTransicionesEliminadas = this.getTransicionDao().buscarODEsPorCriterioIdODESEstado(idODEsEliminadoscriterio);
		List<String> idsEliminados = new ArrayList<String>();
		for (int i = 0; i <ultimasTransicionesEliminadas.size();  i++) {
			idsEliminados.add(((Transicion)ultimasTransicionesEliminadas.get(i)).getIdODE());
			if(logger.isDebugEnabled()) logger.debug("Texto: ["+ SIN_ERRORES +"] traduccion: ["+I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES)+"] idODEs ["+ idsEliminados.get(i) +"]");
			listaResultados.add(new ResultadoOperacionVO(SIN_ERRORES, I18nModuloPublicacion.getPropertyValueI18n(ODE_ELIMINADO), idsEliminados.get(i), 0L));
			if(logger.isDebugEnabled()) logger.debug("tamaño listaResultados: ["+ listaResultados.size()+"]");
		}
		listaNoPublicados.removeAll(idsEliminados);
		if(listaNoPublicados.isEmpty()){
			logger.info("Hemos despublicado masivamente (publicados, despublicados y eliminados)todos los ODES que nos han pasado: ["+idODEs.length+"] elementos");
			return (listaResultados.toArray(new ResultadoOperacionVO[0]));
		}
		//si la lista no esta vacia, solo quedan odes que no existen en la plataforma.
		List<String> idsNoExisten = new ArrayList<String>();
		idsNoExisten.addAll(listaNoPublicados);
		for (int i = 0; i < listaNoPublicados.size(); i++) {
			listaResultados.add(new ResultadoOperacionVO(ODE_NO_EXISTE, I18nModuloPublicacion.getPropertyValueI18n(ODE_NO_EXISTE), idsNoExisten.get(i), 0L));
			if(logger.isDebugEnabled()) logger.debug("tamaño listaResultados: ["+ listaResultados.size()+"]");
		}
		
		return (listaResultados.toArray(new ResultadoOperacionVO[0]));
	}
	/**
	 * Este metodo devuelve la información de publicación de los ODEs que se han publicado para el usuario suministrado.
	 * @param usuario     Usuario del que queremos saber los ODEs publicados.
	 * @return OdePublicadoVO[] Devuelve VO's con los datos de los ODEs publicados para el usuario.
	 * @throws Exception
	 */
	protected OdePublicadoVO[] handleObtenODEsPublicadoUsuario(String usuario) throws Exception {
		// Limpieza de datos
		if (usuario == null || usuario.equals(""))
		{
			logger.warn("Imposible obtener la información de los ODES publicados de un usuario nulo["+usuario+"]");
			throw new Exception("Imposible obtener la información de los ODES publicados de un usuario nulo[");
		}
		TransicionVO[] transiciones = SrvPublicacionServiceImpl.obtenerODEsPorEstadoUsuario(usuario, SrvPublicacionServiceImpl.PUBLICADO, this.getEstadoDao(), this.getTransicionDao());
		if (transiciones == null || transiciones.length == 0) {
			logger.warn("El usuario["+usuario+"] no tiene ODEs publicados");
			return null;
		}
		String[] ids = new String[transiciones.length];
		for (int i = 0; i < ids.length; i++) {
			ids[i]= transiciones[i].getIdODE();
		}
		IdODEsCriteria criterio = new IdODEsCriteria(ids);
		List resultado = this.getOdePublicadoDao().obtenODEsPublicadosDeConjunto(this.getOdePublicadoDao().TRANSFORM_ODEPUBLICADOVO, criterio);		
		if (resultado == null || resultado.size() == 0)
		{
			logger.warn("Error obteniendo lista de ODEs publicados por el usuario ["+usuario+"]. La lista en la tabla de ODEs publicados es vacía, pero tiene registrados["+transiciones.length+"] odes publicados..");
			throw new Exception("Error obteniendo lista de ODEs publicados por el usuario ["+usuario+"]. La lista en la tabla de ODEs publicados es vacía, pero tiene registrados["+transiciones.length+"] odes publicados..");
		}
		return (OdePublicadoVO[])resultado.toArray(new OdePublicadoVO[0]);
	}

	/**
	 * Este método calcula los ODEs publicados de la plataforma que son potenciales duplicados del ODE que le pasan.
	 * @param idODE  Identificador alfanumérico del ODE.
	 * Tiene que coincidir con un ODE que este en estado publicado.
	 * @return OdeSimilarVO[] Devuelve la lista de objetos publicados que tienen alguna similitud con el objeto suministrado.
	 * @throws Exception
	 */
	protected OdeSimilarVO[] handleCalcularDuplicadosPublicados(String idODE) throws Exception {
		// El calulo de la similitud se basa en dos aproximaciones:
		// - Si el ODE que me pasan tiene el mismo código MD5 de alguno que haya ya publicado => es IGUAL
		// - Si el ODE que me pasan tiene en el campo 7.1 del lom-es "se basa en" identificadores de MEC válidos en agrega y además estos están publicados en agrega en este momento.
		ArrayList<OdeSimilarVO> retorno = new ArrayList<OdeSimilarVO>();
		if (idODE == null || idODE.equals(""))
		{
			logger.warn("Error intentando calcular duplicados de un identificador de ODE vacio.");
			return retorno.toArray(new OdeSimilarVO[0]);
		}
		
//		Para las dos comprobaciones que hay que hacer, hay que parsear el ODE que me pasan
//		sacamos el localizador para atacar al manifest
		LocalizadorVO localizador = this.getSrvLocalizadorService().consultaLocalizador(idODE);
		File fileManifest = new File(localizador.getPath(), MANIFEST_NAME); // fichero del manifest
		Manifest imsmanifest = this.getScormDao().parsearODEEager(fileManifest); //manifiesto parseado
//		---------------------------
//		COMPROBACION MD5
//		---------------------------
//		Calculamos el codigo md5 del ODE que me pasan y comprobamos si existe algun ODE publicado ya que tenga este mismo codigo
		String md5Candidato = TratamientoODE.obtenerMD5FromODE(imsmanifest);
		logger.debug("Hemos obtenido un md5Candidato ["+md5Candidato+"] para el ODE ["+  idODE +"]");
		if(md5Candidato == null || md5Candidato.equals(""))
		{
			logger.warn("Error intentando calcular duplicados de ode con identificador["+idODE+"]. El codigo MD5 calculado del ODE es vacio.");
			return retorno.toArray(new OdeSimilarVO[0]);
		}
//		Comprobamos que el codigo MD5 del ODE que me pasan no esta ya en la platafoma.
		MD5Criteria criterioMD5 = new MD5Criteria();
		criterioMD5.setMd5(md5Candidato);
		List<OdePublicado> listaMD5 = this.getOdePublicadoDao().obtenODEsPublicadosPorMD5(criterioMD5);
//		Tenemos igualdades de tipo MD5
		if (listaMD5 != null && !listaMD5.isEmpty())
		{
			logger.info("Añadimos ["+listaMD5.size()+"] similitudes por concepto de MD5["+md5Candidato+"] al ODE con id["+idODE+"]");
//			Las añado
			retorno.addAll(mapeaSimilares(listaMD5,SIMILITUD_MD5));
		}
//		---------------------------
//		COMPROBACION "SE BASA EN"
//		---------------------------
//		Tenemos que sacar todos los MECs validos del campo 7.1 del lomes del ODE que estamos tratando y buscarlos en AGREGA como publicados
//		Si estan, son similares por "se basa en"
		ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
		String identifiadorManifest = manAgrega.getManifest().getIdentifier();
		Lom lom = manAgrega.obtenerLom(identifiadorManifest, null);
		LomAgrega lomAgrega = new LomAgrega(lom);
		ArrayList relacionesAgrega = lomAgrega.getRelacionesSeBasaEnMEC(ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.CATALOGO_MEC));
//		Miro a ver si tiene relaciones, y si es asi, luego tengo que ver si estan en la plataforma
		if (relacionesAgrega != null && relacionesAgrega.size() >0)
		{
			logger.debug("El ODE con id["+idODE+"] tiene ["+relacionesAgrega.size()+"] relaciones \"se basa en\"");
//			Saco los identificadores de las relaciones "se basa en" y las busco entre los ODEs publicados
			ArrayList<String> idsRelacionados = new ArrayList<String>();
			RelacionAgrega[] relaciones = (RelacionAgrega[])relacionesAgrega.toArray(new RelacionAgrega[0]);
			for (int i = 0; i < relaciones.length; i++) {//solo me interesan los id mecs del objeto relacion
				idsRelacionados.add(relaciones[i].getEntradaId());
			}
			IdODEsCriteria criterioODEs = new IdODEsCriteria(idsRelacionados.toArray(new String[0]));
//			Hago la consulta y miro si los ODEs que estan relacionados estan publicados en AGREGA
			List<OdePublicado> listaSeBasaEn = this.getOdePublicadoDao().obtenODEsPublicadosDeConjunto(criterioODEs);
//			Tenemos igualdades de tipo se basa en
			if (listaSeBasaEn != null && !listaSeBasaEn.isEmpty())
			{
				logger.info("Añadimos ["+listaSeBasaEn.size()+"] similitudes por concepto de \"Se basa en\"al ODE con id["+idODE+"]");
//				Las añado
				retorno.addAll(mapeaSimilares(listaSeBasaEn,SIMILITUD_SE_BASA_EN));
			}
		}
//		---------------------------
		return retorno.toArray(new OdeSimilarVO[0]);
	}
	
	/*
	 * Este metodo privado mapea listas de ODEs publicados a 
	 * */
	private List<OdeSimilarVO> mapeaSimilares(List<OdePublicado> listaSimilares, String tiraSimilitud)
	{
		OdeSimilarVO[] lista = new OdeSimilarVO[listaSimilares.size()];
		for (int i = 0; i < lista.length; i++) {
			lista[i]= new OdeSimilarVO(	listaSimilares.get(i).getIdODE(), //id del ODE
										I18nModuloPublicacion.getPropertyValueI18n(tiraSimilitud),//similitud traducida al idioma de navegación del usuario
										listaSimilares.get(i).getIdioma(),
										listaSimilares.get(i).getTitulo()); //idioma del ODE.
		}
		return Arrays.asList(lista);
	}

	/**
	 * Un método como crearPif, sólo que toma en cuenta el tamaño del ODE a la hora de validar.
	 * Este metodo crea un ODE en estado CREADO a partir de un fichero PIF teniendo en cuenta el espacio consumido por el usuario (CREADOS más RECHAZADOS) y la cuota de espacio del usuario.
	 * Si el ODE no cabe en la cuota del usuario, se devuelve un error en consecuencia. Si hay espacio suficiente se crea el ODE.
	 * 
	 * @param pif
	 *            Fichero en formato PIF.
	 * @param idUsuario
	 *            Identificador del usuario.
	 * @param comentarios
	 *            Comentarios que pueden ir asociados a la acción realizada.
	 * @param titulo
	 *            Titulo del ODE.
	 * @param idioma
	 *            Idioma del ODE.
	 * @param id Identificador del ODE, si es null se asignará uno
	 * @return se retorna un VO con el error que se ha producido en el caso de
	 *         detectarse algun problema.
	 * @throws Exception
	 * 
	 */
	protected ResultadoOperacionVO handleCrearPifConCuotaID(DataHandler pif,
			String idUsuario, String comentarios, String titulo, String idioma,
			String id) throws Exception {
		return crearPIFAux(pif, idUsuario, comentarios, titulo, idioma, true, id,null);
	}

	@Override
	protected void handleGuardarImagenPorDefecto(DataHandler imagenFile)
			throws Exception {
	
//		fichero.createNewFile();
		
		//Guardamos en sistema de ficheros
		File fichero = File.createTempFile("img", "temporal");
		FileOutputStream fos = new FileOutputStream(fichero);
		
		try {
			imagenFile.writeTo(fos);
		} catch (Exception e) {
			throw e;
		} finally {
			// liberamos recursos
			fos.close();
		}
		//Escalamos 1a imágen
		//Tamaño grande, jpeg, 800x600
		AgregaProperties properties =AgregaPropertiesImpl.getInstance(); 
		String ruta=properties.getProperty(AgregaProperties.PATH_IMAGEN_DEFECTO);
		File destino = new File(ruta);
		destino.createNewFile();
		UtilesImagenes.escala(fichero, destino, 800, 600, "jpeg");
		//Tamaño mediano, jpeg, 200X125
		ruta=properties.getProperty(AgregaProperties.PATH_IMAGEN_DEFECTO_MEDIA);
		destino= new File(ruta);
		destino.createNewFile();
		UtilesImagenes.escala(fichero, destino, 200, 125, "jpeg");
		//Tamaño pequeño, png, 100x100
		ruta=properties.getProperty(AgregaProperties.PATH_IMAGEN_DEFECTO_PEQUE);
		destino= new File(ruta);
		destino.createNewFile();
		UtilesImagenes.escala(fichero, destino, 100, 100, "png");
	}

	private SrvPropiedadService ObtieneSrvPropiedad() throws Exception {
		if (this.prop==null)
		{
			prop = this.getSrvPropiedadService();
		}
		return prop;
	}
	
    private void copiarFichero(File src, File dst) throws IOException { 
        InputStream in = new FileInputStream(src); 
        OutputStream out = new FileOutputStream(dst); 
         
         
        byte[] buf = new byte[1024]; 
        int len; 
        while ((len = in.read(buf)) > 0) { 
            out.write(buf, 0, len); 
        } 
        in.close(); 
        out.close(); 
    }

    
	@Override
	protected TransicionVO[] handleObtenODEsPublicadosPorTitulo(String titulo)
			throws Exception {
		
		TransicionVO[] result = null;
		try {
			logger.debug("Obtencion de ODEs publicados por titulo: " + titulo);
			titulo = this.concatenarBusqueda(titulo);
			logger.debug("El filtro de búsqueda es: " + titulo);
			TransicionDao transicion = this.getTransicionDao();
			EstadoTituloCriteria criterio = new EstadoTituloCriteria();
			criterio.setEstadoTransitado(null);
			criterio.setEstadoActual(this.getEstadoDao().obtenEstadoPorNombre(PUBLICADO));
			criterio.setTitulo(titulo);
			List transiciones = transicion.buscarOdesPorEstadoTitulo(TransicionDao.TRANSFORM_TRANSICIONVO,criterio);
			logger.info("Obtenidos " + transiciones.size() + " odes en estado " + PUBLICADO +" y con titulo '" + titulo + "'");
			result = (TransicionVO[]) transiciones.toArray(new TransicionVO[transiciones.size()]);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		return result;
	}

	
	@Override
	protected TransicionVO[] handleObtenODEsPublicadosPorTituloYUsuario(
			String titulo, String[] idsUsuario) throws Exception {
		
		TransicionVO[] result = null;
		try {
			logger.debug("Obtencion de ODEs publicados por titulo: " + titulo);
			titulo = this.concatenarBusqueda(titulo);
			logger.debug("El filtro de búsqueda es: " + titulo);
			TransicionDao transicion = this.getTransicionDao();
			EstadoUsuariosTituloCriteria criterio = new EstadoUsuariosTituloCriteria();
			criterio.setEstadoTransitado(null);
			criterio.setEstadoActual(this.getEstadoDao().obtenEstadoPorNombre(PUBLICADO));
			criterio.setTitulo(titulo);
			
			if(idsUsuario!=null && idsUsuario.length>0) 
				criterio.setIdsUsuarios(idsUsuario);
			else 
				criterio.setIdsUsuarios(null);
			
			List transiciones = transicion.buscarOdesPorEstadoUsuariosTitulo(TransicionDao.TRANSFORM_TRANSICIONVO,criterio);
			logger.info("Obtenidos " + transiciones.size() + " odes en estado " + PUBLICADO +" y con titulo '" + titulo + "' para la lista de usuarios "+ Arrays.toString(idsUsuario));
			result = (TransicionVO[]) transiciones.toArray(new TransicionVO[transiciones.size()]);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
		return result;
	}

	
	@Override
	protected ResultadoOperacionVO handleCrearPifConCuotaYEstado(
			DataHandler pif, String idUsuario, String comentarios,
			String titulo, String idioma, String estadoTransicion)
			throws Exception {
//		Invocamos al metodo auxiliar indicandole que queremos tener en cuenta la cuota de usuario.
		return crearPIFAux(pif, idUsuario, comentarios, titulo, idioma, true, null,estadoTransicion);
		
	}

	
	@Override
	protected TransicionVO[] handleObtenODEsCreadosPorUsuarioPorTitulo(
			String idUsuario, String titulo) throws Exception {
		return SrvPublicacionServiceImpl.obtenerODEsPorTresEstadosUsuarioTitulo(titulo, idUsuario, SrvPublicacionServiceImpl.CREACION,
				SrvPublicacionServiceImpl.RECHAZADO, SrvPublicacionServiceImpl.PUBLICADO_VERSIONANDOSE, this.getEstadoDao(), this.getTransicionDao());
	}

	
	// Este método inserta una relación isversionof en el manifest del ODE indicando que el ODE se trata de una nueva versión de otro ODE
	private void insertarRelacionEsVersion(String idODE)
	{
		if (logger.isDebugEnabled())
			logger.debug("Vamos a insertar la relación EsVersionDe para identificador[" + idODE + "]");

		try
		{
			// Extraemos el manifest y modificamos el lom que queremos
			SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
			LocalizadorVO localizadorVO = localizadorService.consultaLocalizador(idODE);
			
			File extraeSubmanifest = new File(localizadorVO.getPath(), MANIFEST_NAME);
			Manifest imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);			
			
			ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
			
			String identifiadorManifest = manAgrega.getManifest().getIdentifier();						
			
			Lom lom = manAgrega.obtenerLom(idODE, null);
			LomAgrega lomAgrega = new LomAgrega(lom);				
	
			if (lom == null) return;						
			String catalogo=ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.CATALOGO_MEC);
			lomAgrega.crearRelacionVersionDe(catalogo);
			manAgrega.setLom(identifiadorManifest, null, lom);
			imsmanifest = manAgrega.getManifest();
			
			// guardamos el manifest modificado
			File fManifest = new File(localizadorVO.getPath(), SrvPublicacionServiceImpl.MANIFEST_NAME);
			this.getScormDao().escribirODE(imsmanifest, fManifest);

		} catch (Exception e) {
			logger.error("ERROR al insertarRelacion :" + e.getMessage());
		}
		if(logger.isDebugEnabled())	logger.debug("Se ha añadido la relación esVersionDe");
	}

	
	// Este método elimina la relación isversionof que se inserta automáticamente si antes se ha propuesto como versión de otro ODE 
	private void eliminarRelacionEsVersionAutomatica(String idODE)
	{
		if (logger.isDebugEnabled())
			logger.debug("Vamos a eliminar la relación automática EsVersionDe para identificador[" + idODE + "]");

		try
		{	
			// Extraemos el manifest y modificamos el lom que queremos
			SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
			LocalizadorVO localizadorVO = localizadorService.consultaLocalizador(idODE);
			
			File extraeSubmanifest = new File(localizadorVO.getPath(), MANIFEST_NAME);
			Manifest imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);			
			
			ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
			
			String identifiadorManifest = manAgrega.getManifest().getIdentifier();						
			
			Lom lom = manAgrega.obtenerLom(idODE, null);					
	
			if (lom == null) return;						
			LomAgrega lomAgrega = new LomAgrega(lom);		
			lomAgrega.eliminarRelacionVersionDe();					
			manAgrega.setLom(identifiadorManifest, null, lom);
			imsmanifest = manAgrega.getManifest();
			
			// guardamos el manifest modificado
			File fManifest = new File(localizadorVO.getPath(), SrvPublicacionServiceImpl.MANIFEST_NAME);
			this.getScormDao().escribirODE(imsmanifest, fManifest);

		} catch (Exception e) {
			logger.error("ERROR al insertarRelacion :" + e.getMessage());
		}
		if(logger.isDebugEnabled())	logger.debug("Se ha añadido la relación esVersionDe");
	}
	
	
	@Override
	protected ResultadoOperacionVO handleProponerCatalogacionNuevaVersion(
			String idODE, String idUsuario, String comentarios, String titulo,
			Boolean solicitaNuevaVersion) throws Exception {
		return proponerCatalogacion(idODE, idUsuario, comentarios,	titulo, true,solicitaNuevaVersion);
	}

	
	@Override
	protected ResultadoOperacionVO handleActualizarVersionODE(DataHandler pif,
			String idUsuario, String comentarios, String titulo, String idioma,
			String idOdeOriginal) throws Exception {
		String id_localizadorNP = "";
		try {
			 
			// utilizar un id generado por la clase de soporte que genera un uuid
			String idODE = es.pode.soporte.uuid.PodeUUIDGenerator.getOdeUUID(String.valueOf(System.currentTimeMillis()));
			// creacion
			this.getSrvLocalizadorService().crearLocalizadorNoPublicado(idUsuario, idODE);
			logger.debug("Creando PIF utilizando identificador[" + idODE + "], idUsuario[" + idUsuario+ "], comentarios[" + comentarios + "] y titulo[" + titulo + "]");

			SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
			LocalizadorVO localizadorNP = localizadorService.consultaLocalizador(idODE);
			id_localizadorNP = localizadorNP.getIdentificador();
			if(logger.isDebugEnabled())
				logger.debug("Encontramos localizador con identificador[" + localizadorNP.getIdentificador() + "] usuario["
						+ localizadorNP.getIdUsuario() + "] MEC[" + localizadorNP.getMec() + "] path["+ localizadorNP.getPath() + "] URL[" + localizadorNP.getUrl() + "]");

			// empezamos a descomprimir el pif y guardar lo que nos devuelve en un directorio temporal
			if(logger.isDebugEnabled())	logger.debug("Comenzamos a descomprimir el PIF y guardar en un dir temporal.");

			String pathtemp = "";
			//Escapamos caracteres raros de titulo
			titulo=titulo.replaceAll("\\[", "_");
			titulo=titulo.replaceAll("\\]", "_");
			
			pathtemp = localizadorNP.getPath() + SrvPublicacionServiceImpl.getPropertyValue("carpeta.temporal") + "/" + titulo;
			String pathLocal = localizadorNP.getPath();
			int position = pathLocal.lastIndexOf("/");
			String pathPadre=pathLocal.substring(0, position);
			String pathTemporal=pathPadre+ "/" +SrvPublicacionServiceImpl.getPropertyValue("tempDir") + "/" + titulo ;//El .zip lo tiene el titulo
			logger.debug("El pathtemp es " + pathtemp + "El path donde se encuentra el zip es "+pathTemporal);
			
			File ficheroZip = new File(pathtemp);
			(ficheroZip.getParentFile()).mkdirs();
			ficheroZip.createNewFile();
			ficheroZip.deleteOnExit(); 
			FileOutputStream fos = new FileOutputStream(ficheroZip);
			pif.writeTo(fos);
			String pathtempDestino = localizadorNP.getPath();
			File destinoTemporal = new File(pathtempDestino);
			logger.debug("El destinoTemporal es "+destinoTemporal);
			destinoTemporal.mkdirs();
			destinoTemporal.deleteOnExit();
			fos.close();


			// La comprobacion se delega en el empaquetador para hacer un 'importar inteligente'. El atributo
			// 'titulo' es el nombre del fichero proporcionado por la aplicación cliente
			String tipoFichero =null;
			try {
				AnalizaArchivoVO resultadoAnalisis = this.getSrvFachadaAgregarService().analizarArchivo(pathtemp); 
				tipoFichero = resultadoAnalisis.getTipoArchivo();
				if(logger.isDebugEnabled()) logger.debug("El fichero importado " + titulo + " es de tipo " + tipoFichero);
				// Si el tipo de fichero es comprimido, lo descomprimimos en la localización definitiva
				if(ConstantesAgrega.FICHERO.equals(tipoFichero)) {
					UtilesFicheros.copiar(ficheroZip, destinoTemporal);
				}else if(ConstantesAgrega.ODE_NO_VALIDO.equals(tipoFichero)) {
					logger.warn("El archivo " + titulo + " es un ODE no valido. Se rechaza su importacion");
					this.getSrvLocalizadorService().eliminarLocalizador(id_localizadorNP);
					String mensajeConcatenado="";
					for(int i=0;i<resultadoAnalisis.getMensajes().length;i++){
						if(i==0){
							mensajeConcatenado=resultadoAnalisis.getMensajes()[0]+";";
						}else{
							mensajeConcatenado=mensajeConcatenado+resultadoAnalisis.getMensajes()[i]+";";
						}
					}
					return new ResultadoOperacionVO(ERROR_DE_VALIDACION,mensajeConcatenado,idODE,0L);
				} else {					
					getZipDao().descomprimir(ficheroZip.getPath(), pathtempDestino);
					if(logger.isDebugEnabled())
						logger.debug("DESCOMPRIMIENDO FICHERO ZIP");
				}
			} catch (Exception e) {
//				borramos todo lo que hayamos hecho hasta ahora
				this.getSrvLocalizadorService().eliminarLocalizador(id_localizadorNP);
				logger.error("Error analizando fichero [" + pathtemp + "], con nombre[" + titulo + "]", e);
				//Vamos a borrar la carpeta temporal
				logger.debug("Borramos la carpeta "+ficheroZip); //+" si existe y tambien el zip que  esta en el temporal "+fileTemporal);
				UtilesFicheros.eliminar(ficheroZip);
				return new ResultadoOperacionVO(ERROR_DESCOMPRIMIENDO_FICHERO_ZIP,
						I18nModuloPublicacion.getPropertyValueI18n(ERROR_DESCOMPRIMIENDO_FICHERO_ZIP),idODE,0L);
			} 

			String tipo="";
//			UNA VEZ DESCOMPRIMIDO, SACAMOS EL TIPO DE ODE Q SE ESTA IMPORTANDO: SCORM 1.2, IMS CP O SCORM 2004
			es.pode.soporte.validador.TipoOde tipoOde = new es.pode.soporte.validador.TipoOde();
			tipoOde.obtenerTipoOde(localizadorNP.getPath() + "/" +MANIFEST_NAME);
			tipo = tipoOde.getTipo();
			if(logger.isDebugEnabled())
				logger.debug("PUBLICACION ANTES DE ANALIZARARCHIVO, EL TIPO DE ODE ES : " + tipo);

			// En funcion del tipo de archivo hay que generar un imsmanifest.xml que recubra el contenido.
			if(ConstantesAgrega.FICHERO.equals(tipoFichero)) {
				this.getSrvFachadaAgregarService().generarManifest(localizadorNP.getIdentificador(), new String[]{titulo}, titulo, idioma);
			} else if(ConstantesAgrega.ARCHIVO.equals(tipoFichero)) {
				Collection<File> ficheros = FileUtils.listFiles(destinoTemporal, null, true);
				if(ficheros!=null ) {
					java.io.File[] ficherosArray = new java.io.File[ficheros.size()];
					if(logger.isDebugEnabled()) logger.debug("Se han encontrado " + ficheros.size() + "dentro del zip " + titulo);
					ficherosArray = ficheros.toArray(ficherosArray);
					String[] rutas = new String[ficheros.size()];
					String rutaAReemplazar = destinoTemporal.getPath().replaceAll("\\\\", "/");
					if(logger.isDebugEnabled()) logger.debug("Ruta a reemplazar con / : " + rutaAReemplazar);
					for(int i=0;i<ficherosArray.length;i++) {
						String ruta = ficherosArray[i].getPath().replaceAll(destinoTemporal.getPath(), "");
						if(ruta.startsWith("/")) ruta = ruta.substring(1);
						if(logger.isDebugEnabled()) logger.debug("Ruta " + ficherosArray[i].getPath() + " cambiada a " +ruta);
						rutas[i]=ruta;
					}
					getSrvFachadaAgregarService().generarManifest(localizadorNP.getIdentificador(), rutas, rutas[0], idioma);

				} else {
					logger.error("Se ha intentado importar un ZIP vacio");
				}
			} else if(ConstantesAgrega.RCP.equals(tipoFichero) && (tipo.equals(ConstantesAgrega.SCORM_04))) {
				if(logger.isDebugEnabled())
					logger.debug("PUBLICACION ENTRA EN OPCION RCP Y 2004!!!!");
				this.getSrvFachadaAgregarService().generarManifestRCP(localizadorNP.getIdentificador(), idioma);
			} // PARA ConstantesAgrega.CA no hace falta hacer nada
			
			String xsl="";
			// copiar los esquemas por si acaso no los trajese
			try {
				
				//aqui debemos chequear q tipo de ode es y hacer la conversion y copiar los esquemas correspondientes
				//Hacemos la transformación a scorm 2004	
				try {
					if (tipo !=null) {
						String oriTrans="";
						String destTrans="";
						TransformadorSaxonImpl transformador = new TransformadorSaxonImpl();					
						if (tipo.equals(ConstantesAgrega.SCORM_12)) {
							xsl=SrvPublicacionServiceImpl.getPropertyValue("xslt_scorm_12");
							if (logger.isDebugEnabled())
								logger.debug("EL ODE ES SCORM 12");

						}else if (tipo.equals(ConstantesAgrega.IMS_CP)) {
							xsl=SrvPublicacionServiceImpl.getPropertyValue("xslt_ims_cp");
							if (logger.isDebugEnabled())
								logger.debug("EL ODE ES IMSCP");
						}
						//Si xsl es "" es que no es ni scorm_12 ni ims_cp sino scorm2004
						if (!xsl.equals("")) {
							//TODO No transformamos submanifiestos... Tienen mismo tipo que manifiesto ppal
							if (logger.isDebugEnabled())
								logger.debug("EL VALOR DE XSL ES : " +  xsl);
							oriTrans = localizadorNP.getPath() +"/"+MANIFEST_NAME;
							destTrans = localizadorNP.getPath() + "/imsmanifestTransformado.xml";
							transformador.transformar(xsl, oriTrans, destTrans);
							// Falta renombrar el imsmanifestTransformado a imsmanifest pq asi está mal!!
							File fichOri = new File(oriTrans);

							boolean deleteOri = fichOri.delete();
							if(!deleteOri) logger.warn("No se ha podido borrar " + fichOri);
							File fichDest = new File(destTrans);
							fichDest.renameTo(fichOri);

							if (logger.isDebugEnabled())logger.debug("HEMOS TRANSFORMADO DE " + oriTrans + " " + destTrans + " Y EL TIPOFICHERO ERA: " + tipoFichero);
//							como no era 2004, se ha hecho la transformacion y si era un rcp generamos el manifest
							if (logger.isDebugEnabled())
								logger.debug("LLAMAMOS A BORRARESQUEMAS SI SE HA HECHO TRANSFORMACION...... ");
							TratamientoODE.borrarEsquemas(pathtempDestino, tipo);

							if(ConstantesAgrega.RCP.equals(tipoFichero)) {
								//NECESITAMOS LOS ESQUEMAS DE 2004
								TratamientoODE.copiarEsquemas(pathtempDestino);
								if (logger.isDebugEnabled())logger.debug("HEMOS COPIADO LOS ESQUEMAS A " + pathtempDestino + "Y VAMOS A HACER LA VALIDACION LIGERA");
								this.getSrvValidadorService().obtenervalidacionLigera(pathtempDestino,	ConstantesAgrega.RCP);
								this.getSrvFachadaAgregarService().generarManifestRCP(localizadorNP.getIdentificador(), idioma);
								if (logger.isDebugEnabled())logger.debug("DESPUES DE generarManifestRCP");
							}
						}
					}
				}catch (Exception et) {
					logger.error("Error al realizar la transformación a Scorm 2004 ", et);
//					borramos todo lo que hayamos hecho hasta ahora
					this.getSrvLocalizadorService().eliminarLocalizador(id_localizadorNP);
					//Vamos a borrar la carpeta temporal
					logger.debug("Borramos la carpeta "+ficheroZip + "si existe."); //+"y tambien el zip que  esta en el temporal "+fileTemporal);
					UtilesFicheros.eliminar(ficheroZip);
					return new ResultadoOperacionVO(ERROR_EN_TRANSFORMACION, I18nModuloPublicacion.getPropertyValueI18n(ERROR_EN_TRANSFORMACION),idODE,0L);
				}
//				Una vez transformado el manifest a 2004, copiamos los esquemas
				TratamientoODE.copiarEsquemas(pathtempDestino);
				if (logger.isDebugEnabled()) logger.debug("HEMOS COPIADO LOS ESQUEMAS A " + pathtempDestino);

			} catch (Exception e1) {
				logger.error("No se pudieron copiar los esquemas al importar un pif: ", e1);
				// borramos todo lo que hayamos hecho hasta ahora
				this.getSrvLocalizadorService().eliminarLocalizador(id_localizadorNP);
				//Vamos a borrar la carpeta temporal
				logger.debug("Borramos la carpeta "+ficheroZip+" si existe.");  // y tambien el zip que  esta en el temporal "+fileTemporal);
				UtilesFicheros.eliminar(ficheroZip);
				return new ResultadoOperacionVO(ERROR_COPIANDO_ESQUEMAS, I18nModuloPublicacion.getPropertyValueI18n(ERROR_COPIANDO_ESQUEMAS),idODE,0L);
			}

			Long consumoODE = 0L;
			SrvLocalizadorService localizador = this.getSrvLocalizadorService();

			//Antes de seguir vemos si el ODE no supera el espacio de su cuota. Ya tenemos descomprimido el ODE en disco y podemos ver si se ha pasado
				consumoODE = localizador.consultaEspacioLocalizador(id_localizadorNP);
				long cuotaConsumida = TratamientoCuota.calculaCuotaConsumidaUsuario(localizador, idUsuario, this.handleObtenODEsCreadosPorUsuario(idUsuario));
				long cuotaUsuario = LdapUserDetailsUtils.getCuota().longValue();
				if(logger.isDebugEnabled()) logger.debug("El espacio consumido en disco por el ODE["+idODE+"] con localizador["+localizadorNP.getPath()+"]del usuario["+idUsuario+"] es de ["+consumoODE+"] bytes");
				if(logger.isDebugEnabled()) logger.debug("Los ODEs del usuario["+idUsuario+"] ocupan un total de ["+cuotaConsumida+"] y tiene una cuota de ["+cuotaUsuario+"] bytes");
				if ((cuotaConsumida + consumoODE) > cuotaUsuario) // nos hemos pasado de cuota, le damos la vuelta al asunto y salimos
				{
					logger.warn("Error creando ODE. La cuota del usuario["+idUsuario+"]["+cuotaUsuario+"]bytes se ha superado con el ODE["+idODE+"]["+consumoODE+"]bytes en ["+(cuotaConsumida - cuotaUsuario)+"]bytes");
					// borramos todo lo que hayamos hecho hasta ahora
					localizador.eliminarLocalizador(id_localizadorNP);
					//Vamos a borrar la carpeta temporal
					logger.debug("Borramos la carpeta "+ficheroZip+" si existe "); //y tambien el zip que  esta en el temporal "+fileTemporal);
					UtilesFicheros.eliminar(ficheroZip);
					return new ResultadoOperacionVO(ERROR_EXCEDER,I18nModuloPublicacion.getPropertyValueI18n(ERROR_EXCEDER) ,idODE,new Long(consumoODE));
				}
				logger.info("Creando ODE desde PIF con id["+idODE+"] tamanio["+consumoODE+"] para usuario["+idUsuario+"] con cuota consumida["+cuotaConsumida+"] y cuota total["+cuotaUsuario+"]");			
			
			// 20130829	Verificamos si el ODE que nos pasan es una versión del que se quiere actualizar
			// 			Para ello debe tener el mismo idMEC
				
			LocalizadorVO  localizadorODEOriginal = getSrvLocalizadorService().consultaLocalizador(idOdeOriginal);			
				
			File extraeSubmanifest = new File(localizadorODEOriginal.getPath(), MANIFEST_NAME);
			Manifest imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);
			ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
			String identifiadorManifest = manAgrega.getManifest().getIdentifier();
			Lom lom = manAgrega.obtenerLom(identifiadorManifest, null);
			LomAgrega lomAg = new LomAgrega(lom);
							
			String idMECAGREGA = lomAg.getGeneralAgrega().obtenerIdentificadorFormatoMEC(ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.CATALOGO_MEC));							
				
			 extraeSubmanifest = new File(localizadorNP.getPath(), MANIFEST_NAME);
			 imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);
			 manAgrega = new ManifestAgrega(imsmanifest);
			 identifiadorManifest = manAgrega.getManifest().getIdentifier();

			 lom = manAgrega.obtenerLom(identifiadorManifest, null);			
			 lomAg = new LomAgrega(lom);
			
			String idMECNuevaVersion = lomAg.getGeneralAgrega().obtenerIdentificadorFormatoMEC(ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.CATALOGO_MEC));
			if (logger.isDebugEnabled())
				logger.debug("El idMEC de la nueva version es : "  + idMECNuevaVersion);
			
			// Si el idMec no coincide no es una versión del ODE y se lanza el error
			if (!idMECAGREGA.equals(idMECNuevaVersion))
			{
				logger.error("No se puede actualizar porque no coinciden los idMEC");
				return new ResultadoOperacionVO(ERROR_ACTUALIZAR_VERSION_INCORRECTA, I18nModuloPublicacion.getPropertyValueI18n(ERROR_ACTUALIZAR_VERSION_INCORRECTA),idODE,0L);				
				
			}	
							
			// validador
			SrvValidadorService validadorService = this.getSrvValidadorService();
			// reaalizamos una validacion ligera en lugar de carga ode.
			ValidaVO valid = validadorService.obtenervalidacionLigera(localizadorNP.getPath(), "CA");
			logger.info("Validando el ODE a importar[" + localizadorNP.getPath() + "] : Valido["
					+ valid.getEsValidoManifest().booleanValue() + "]");

			// la hora de testear cuidado con el validador
			if (valid.getEsValidoManifest().booleanValue()) {
				// Extraemos el manifest del ODE.
				//File extraeSubmanifest = new File(localizadorNP.getPath(), MANIFEST_NAME);
				if (logger.isDebugEnabled()) logger.debug("ES VALIDO Y VA A HACER EL PARSEO");
				//Manifest imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);
				if(logger.isDebugEnabled()) logger.debug("Validado el ODE [" + localizadorNP.getPath() + "]!!");

				String resultadoVocab = this.comprobarVocabulariosYFechas(imsmanifest);
				//Metemos la información de validación ligera 
				if ((valid.getResultadoValidacion()!=null) && (!valid.getResultadoValidacion().equals(""))) {
					resultadoVocab = new StringBuffer(resultadoVocab).append(valid.getResultadoValidacion()).toString();					
				}
				//Metemos la información sobre el tipo de transformación que se ha realizado
				if (!xsl.equals("") && tipo!=null && !tipo.equals("")) {
					if (logger.isDebugEnabled())logger.debug("SE HA REALIZADO LA TRANSFORMACIÓN DE " + tipo + " A SCORM2004 idioma " + idioma);
					resultadoVocab = new StringBuffer(resultadoVocab).append(I18nModuloPublicacion.getPropertyValueI18n("publicacion.origenTransformacion", idioma))
					.append(tipo).append(" ").append(I18nModuloPublicacion.getPropertyValueI18n("publicacion.transforma2004", idioma)).toString();
					if (logger.isDebugEnabled())logger.debug("SACAMOS EL VALOR DE RESULTADO de resultadoVocab " + resultadoVocab);					
				}
				// Por ultimo, en caso de que todo haya ido bien, tenemos que recubrir el MEC y el identificador del 
				// manifest que tenga este ODE con el identificador UUID que utilizamos en la plataforma, ya que este
				// ODE esta en estado CREADO => esta en el taller. Para ello vamos a utilizar el mismo metodo que ya 
				// existe y hace todo esto con el identificador MEC en el momento de publicar, pero le vamos a pasar
				// el identificador de taller (el UUID)

				ScormDao scorm = (ScormDao) this.getScormDao();
				TratamientoODE.cambiaUUIDxMEC(idODE, localizadorNP.getPath(), imsmanifest, ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.CATALOGO_AGREGA), scorm); 
				// utilizamos el catalogo agrega imsmanifest
				// si el ode tiene lomes el título lo cambiamos al que trajese con el lomes

				// manifest en la posicion exacta
				//ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
				//String identifiadorManifest = manAgrega.getManifest().getIdentifier();
				//Lom lom = manAgrega.obtenerLom(identifiadorManifest, null);
				if (lom != null) {
					LomAgrega lomAgrega = new LomAgrega(lom);
					idioma=lomAgrega.getMetaMetadataAgrega().getIdioma();
					titulo = lomAgrega.getGeneralAgrega().getTitulo(idioma);
				} else {
					logger.warn("El Lom del manifest " + idODE+ " no tiene objeto general, el nombre del fichero será el titulo del ODE");
				}
								
				//Eliminamos caracteres extraños de los recursos y del titulo para evitar problemas
				//y para que el manifest del ODE pueda validarse
				eliminarCaracteresExtranosDeRecursos(idODE);				
				eliminarCaracteresExtranosDelTituloYOrganizaciones(localizadorNP.getPath());	
				
				// Creamos la transicion
				EstadoDao estadoDao = this.getEstadoDao();
				TransicionDao transicionDao = this.getTransicionDao();
				Date fecha = new Date(System.currentTimeMillis());
									
				transicionDao.create(DateManager.dateToCalendar(fecha), comentarios, idODE, idUsuario, titulo, idUsuario,// el usuario de creacion es el mismo que me pasan
						fecha.getTime(),false, null, null, estadoDao.obtenEstadoPorNombre(PUBLICADO_VERSIONANDOSE));
				//Vamos a borrar la carpeta temporal
				//	logger.debug("Borramos la carpeta "+ficheroZip+" si existe y tambien el zip que  esta en el temporal "+fileTemporal);
				UtilesFicheros.eliminar(ficheroZip);
			
				// 20130830 Todo ha ido bien, por lo que tenemos que borrar la versión original del ODE				
				handleEliminar(idOdeOriginal, idUsuario);
				
				if(resultadoVocab.equals(""))
					return new ResultadoOperacionVO(SIN_ERRORES, I18nModuloPublicacion.getPropertyValueI18n(SIN_ERRORES),idODE,consumoODE);
				return new ResultadoOperacionVO(VOCAB_BORRADOS, resultadoVocab,idODE,consumoODE);
			}// si no es válido rollback
			// borramos todo lo que hayamos hecho hasta ahora
			this.getSrvLocalizadorService().eliminarLocalizador(id_localizadorNP);
			logger.warn("Atención: no se ha validado el ODE [" + localizadorNP.getPath()
					+ "]correctamente. No se ha creado el Pif, error: " + valid.getResultadoValidacion() + "]");
			//Vamos a borrar la carpeta temporal
			//	logger.debug("Borramos la carpeta "+ficheroZip+" si existe y tambien el zip que  esta en el temporal "+fileTemporal);
			UtilesFicheros.eliminar(ficheroZip);
			//UtilesFicheros.eliminar(fileTemporal);
			return new ResultadoOperacionVO(SrvPublicacionServiceImpl.ERROR_DE_VALIDACION, valid.getResultadoValidacion(),idODE,0L);			
		
		}// try
		catch (Exception e) {
			logger.error("Fallo creando PIF con fichero[" + titulo + "] idusuario[" + idUsuario + "] comentarios["+ comentarios + "] titulo[" + titulo + "] )", e);
			// borramos todo lo que hayamos hecho hasta ahora
			this.getSrvLocalizadorService().eliminarLocalizador(id_localizadorNP);
			return new ResultadoOperacionVO(SrvPublicacionServiceImpl.ERROR_GENERICO, I18nModuloPublicacion.getPropertyValueI18n(ERROR_GENERICO),idOdeOriginal,0L);
		}

	}

	
	@Override
	protected Boolean handleHaEstadoVersionado(String idODE) throws Exception {				
		Long idEstadoVersionado = this.getEstadoDao().obtenEstadoPorNombre(SrvPublicacionServiceImpl.PUBLICADO_VERSIONANDOSE).getId().longValue();
		List<Transicion> historial = this.getTransicionDao().buscarHistorialPorIdODE(idODE);
		logger.debug("Historial de idODE["+idODE+"]["+ ((historial!=null)?historial.size():0)+"]transiciones.");
		if (historial == null || historial.size() == 0) 
			return false;
		for(int i=0; i<historial.size(); i++) 
			if(historial.get(i).getEstadoActual().getId().longValue()==idEstadoVersionado)
				return true;
		return false;
	}

	
	/*
	 * Devuelve una lista con los ODEs que estan en estado eliminado o despublicado entre dos fechas dadas.
	 * Metodo que devuelve los ODEs despublicados de un nodo, devuelve aquellas transiciones que tienen 
	 * como estado actual NO_DISPONIBLE o ELIMINADO y cuyo estado transitado es siempre null, en otras 
	 * palabras, devolverá aquellos ODEs que han sido eliminados (como los versionados) o aquellos que 
	 * su estado actual es NO_DISPONIBLE.
	 */
	@Override
	protected TransicionVO[] handleObtenODEsDespublicadosPorFecha(String fechaInicio, String fechaFin)
			throws Exception {
		
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		Date dateIni = null;
		Date dateFin = null;
		List<TransicionVO> odesNoDisponibles = null;
		List<TransicionVO> odesEliminados = null;
		List<TransicionVO> listaCompleta = new ArrayList<TransicionVO>();
			
		if (fechaInicio == null || fechaInicio.isEmpty() ||
				fechaFin == null || fechaFin.isEmpty()) {
			logger.warn("obteniendo la lista de ODEs no disponibles. Las fechas no son correctas.");
			return null;
		}
		/*
		if (fechaInicio == null || fechaInicio.isEmpty())
			dateIni = new Date(0); // si la fecha de inicio es vacia, cogemos 1970 como inicio.
		else
		*/
			dateIni = formatoDelTexto.parse(fechaInicio);
		/*	
		if (fechaFin == null || fechaFin.isEmpty())
			dateFin = new Date(); // si la fecha de fin es vacia, utilzamos hoy como tope.
		else
		*/
			dateFin = formatoDelTexto.parse(fechaFin);
			
		if (dateIni.after(dateFin)) // hay una inconsistencia con las fechas, no seguimos
		{
			logger.warn("Error listando de odes eliminados. Fechas desde [" + fechaInicio
					+ "] y hasta [" + fechaFin + "] inconsistentes.");
			return null;
		}

        Calendar inicioDiaActual = Calendar.getInstance();
        inicioDiaActual.setTime(dateIni);
        inicioDiaActual.set(Calendar.AM_PM, 0);
        inicioDiaActual.set(Calendar.HOUR, 0);
        inicioDiaActual.set(Calendar.MINUTE, 0);
        inicioDiaActual.set(Calendar.SECOND, 0);
        
        Calendar finDiaActual = Calendar.getInstance();
        finDiaActual.setTime(dateFin);
        finDiaActual.set(Calendar.AM_PM, 1);
        finDiaActual.set(Calendar.HOUR, 11);
        finDiaActual.set(Calendar.MINUTE, 59);
        finDiaActual.set(Calendar.SECOND, 59);
		
		EstadoDesdeHastaCriteria criterio = new EstadoDesdeHastaCriteria();
		criterio.setFechaDesde(inicioDiaActual);
		criterio.setFechaHasta(finDiaActual);
		criterio.setEstadoTransitado(null);
		
		criterio.setEstadoActual(getEstadoDao().obtenEstadoPorNombre(NO_DISPONIBLE));
		if(logger.isDebugEnabled()) logger.debug("Buscamos ODEs no disponibles desde [" + fechaInicio + "] hasta [" + fechaFin + "]");
		try {
			odesNoDisponibles = getTransicionDao().buscarODEsPorCriterioEstadoDesdeHasta(TransicionDao.TRANSFORM_TRANSICIONVO, criterio);
		} catch (Exception e) {
			String error="Error obteniendo la lista de ODEs no disponibles para toda la plataforma desde["
					+ fechaInicio + "] hasta[" + fechaFin + "][" + e.getCause() + "]";
			logger.error(error);
			return null;
		}
		if(logger.isDebugEnabled()) logger.debug("Encontrados "+odesNoDisponibles.size()+" ODEs no disponibles desde [" + fechaInicio + "] hasta [" + fechaFin + "]");
		
		criterio.setEstadoActual(getEstadoDao().obtenEstadoPorNombre(ELIMINADO));
		if(logger.isDebugEnabled()) logger.debug("Buscamos ODEs eliminados desde [" + fechaInicio + "] hasta [" + fechaFin + "]");
		try {
			odesEliminados = getTransicionDao().buscarODEsPorCriterioEstadoDesdeHasta(TransicionDao.TRANSFORM_TRANSICIONVO, criterio);
		} catch (Exception e) {
			String error="Error obteniendo la lista de ODEs eliminados para toda la plataforma desde["
					+ fechaInicio + "] hasta[" + fechaFin + "][" + e.getCause() + "]";
			logger.error(error);
			return null;
		}
		if(logger.isDebugEnabled()) logger.debug("Encontrados "+odesEliminados.size()+" ODEs eliminados desde [" + fechaInicio + "] hasta [" + fechaFin + "]");
		
		for(int i=0; i<odesNoDisponibles.size(); i++)
			listaCompleta.add(odesNoDisponibles.get(i));
		for(int i=0; i<odesEliminados.size(); i++)
			listaCompleta.add(odesEliminados.get(i));
		
		return listaCompleta.toArray(new TransicionVO[listaCompleta.size()]);
	}

	
	@Override
	protected Boolean handleInsertarOdesFederadosDespublicados(
			TransicionVO[] odes, String idNodo) throws Exception {
		
		if(odes==null) return true;
		if(idNodo==null || idNodo.equals(""))
			throw (new Exception ("Error; el server id del nodo no puede ser vacio"));
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		for(int i=0; i<odes.length; i++) {		
			String estado = odes[i].getEstadoActual().getClave();
			String fechaOde = df.format(odes[i].getFecha().getTime());
			
			//Buscamos si ya esta dado de alta la despublicacion de el ODE a esa hora, en caso de estarlo no lo registramos
			OdeFederadoDespublicadoFecha criterio = new OdeFederadoDespublicadoFecha(odes[i].getFecha(), odes[i].getIdODE());
			List l = this.getOdesFederadosDespublicadosDao().obtenerOdeFederadoDespublicadoPorFecha(criterio);
			
			if(l==null || l.size()==0) {
				if(!estado.equals(NO_DISPONIBLE) && !estado.equals(ELIMINADO)) 
					logger.error("Error: Se ha intentado registrar en la tabla de ODEs federados despublicados un ode con estado "+estado+" -> idODE:"+odes[i].getIdODE()+", fecha despublicacion:"+fechaOde+", nodo:"+idNodo);			
				else
					this.getOdesFederadosDespublicadosDao().create(odes[i].getIdODE(), odes[i].getFecha(), idNodo);
			} else {
				logger.warn("Se ha intentado registrar como despublicado federado en el nodo "+idNodo+" un ODE ("+odes[i].getIdODE()+") que ya teniamos registrado con fecha de despublicacion "+fechaOde);
			}
		}
		return true;
	}

	
	@Override
	protected OdesFederadosDespublicadosVO[] handleObtenODEsDespublicadosFederadosPorFecha(
			String fechaInicio, String fechaFin) throws Exception {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date dateIni = null;
		Date dateFin = null;
		List<OdesFederadosDespublicadosVO> odesNoDisponibles = null;
			
		try {
			if (fechaInicio == null || fechaInicio.isEmpty() ||
					fechaFin == null || fechaFin.isEmpty()) {
				logger.warn("obteniendo la lista de ODEs no disponibles federados. Las fechas no son correctas.");
				return null;
			}
			/*
			if (fechaInicio == null || fechaInicio.isEmpty())
				dateIni = new Date(0); // si la fecha de inicio es vacia, cogemos 1970 como inicio.
			else
			*/
				dateIni = df.parse(fechaInicio);
			/*	
			if (fechaFin == null || fechaFin.isEmpty())
				dateFin = new Date(); // si la fecha de fin es vacia, utilzamos hoy como tope.
			else
			*/
				dateFin = df.parse(fechaFin);
				
			if (dateIni.after(dateFin)) // hay una inconsistencia con las fechas, no seguimos
			{
				logger.warn("Error listando de odes eliminados federados. Fechas desde [" + fechaInicio
						+ "] y hasta [" + fechaFin + "] inconsistentes.");
				return null;
			}

            Calendar inicioDiaActual = Calendar.getInstance();
            inicioDiaActual.setTime(dateIni);
            inicioDiaActual.set(Calendar.AM_PM, 0);
            inicioDiaActual.set(Calendar.HOUR, 0);
            inicioDiaActual.set(Calendar.MINUTE, 0);
            inicioDiaActual.set(Calendar.SECOND, 0);
            
            Calendar finDiaActual = Calendar.getInstance();
            finDiaActual.setTime(dateFin);
            finDiaActual.set(Calendar.AM_PM, 1);
            finDiaActual.set(Calendar.HOUR, 11);
            finDiaActual.set(Calendar.MINUTE, 59);
            finDiaActual.set(Calendar.SECOND, 59);
			
			OdesFederadosDespublicadosFechaCriteria criterio = new OdesFederadosDespublicadosFechaCriteria();
			criterio.setFechaDesde(inicioDiaActual);
			criterio.setFechaHasta(finDiaActual);
			if(logger.isDebugEnabled()) logger.debug("Buscamos ODEs despublicados federados [" + fechaInicio + "] hasta [" + fechaFin + "]");
			odesNoDisponibles = this.getOdesFederadosDespublicadosDao().obtenerOdesFederadosDespublicadosPorFecha(OdesFederadosDespublicadosDao.TRANSFORM_ODESFEDERADOSDESPUBLICADOSVO, criterio);
			
		} catch (Exception e) {
			String error="Error obteniendo la lista de ODEs despublicados federados desde["
					+ fechaInicio + "] hasta[" + fechaFin + "][" + e.getCause() + "]";
			logger.error(error);
			return null;
		}
		if(logger.isDebugEnabled()) logger.debug("Encontrados "+odesNoDisponibles.size()+" ODEs no disponibles desde [" + fechaInicio + "] hasta [" + fechaFin + "]");
		return odesNoDisponibles.toArray(new OdesFederadosDespublicadosVO[odesNoDisponibles.size()]);
	}

	
	@Override
	protected ResultadoOperacionVO handleDespublicacionExterna(String idODE,
			String usuario) throws Exception {
		ResultadoOperacionVO resultado = new ResultadoOperacionVO();

		try
		{
			// Validación campos de entrada
			if (idODE==null || idODE.equals("")) {
				logger.error("handleDespublicarWebSemantica. No se ha informado el ODE a despublicar");
				resultado.setDescripcion(I18nModuloPublicacion.getPropertyValueI18n(ERROR_ID_ODE_OBLIGATORIO,"es"));
				resultado.setIdODE(idODE);
				resultado.setIdResultado(ERROR_ID_ODE_OBLIGATORIO);
				return resultado;
			}
			
			if (usuario==null || usuario.equals("")) {
				logger.error("handleDespublicarWebSemantica. No se ha informado el usuario que despublica el ODE");
				resultado.setDescripcion(I18nModuloPublicacion.getPropertyValueI18n(ERROR_USUARIO_DESPUB_OBLIGATORIO,"es"));
				resultado.setIdODE(idODE);
				resultado.setIdResultado(ERROR_USUARIO_DESPUB_OBLIGATORIO);
				return resultado;
			}
			
			if (logger.isDebugEnabled())
				logger.debug("handleDespublicarWebSemantica. El usuario : " + usuario + " ha solicitado despublicar el ODE : " + idODE);

			//Comprobar que el ODE existe
			LocalizadorVO localizadorP = null;			
			try {		
				localizadorP = getSrvLocalizadorService().consultaLocalizador(idODE);			
			} catch (Exception e) {
				logger.error("handleDespublicarWebSemantica. No se ha localizado el ODE : ", e);
				resultado.setDescripcion(I18nModuloPublicacion.getPropertyValueI18n(ODE_NO_EXISTE,"es"));
				resultado.setIdODE(idODE);
				resultado.setIdResultado(ODE_NO_EXISTE);
				return resultado;
			}
			
			//Validar que el usuario puede despublicar ese ODE
			EditoresOdesExternosIdOdeCriteria criterio = new EditoresOdesExternosIdOdeCriteria();
			criterio.setIdOde(idODE);
			List<EditoresOdesExternosVO> listaEditoresDelOdeVO = null;
			listaEditoresDelOdeVO = this.getEditoresOdesExternosDao().buscarEditoresOdesExternosPorIdOde(EditoresOdesExternosDao.TRANSFORM_EDITORESODESEXTERNOSVO, criterio);
			
			boolean usuarioAutorizado = false;

			if(listaEditoresDelOdeVO!=null && !listaEditoresDelOdeVO.isEmpty()) {
				for(int i=0; i<listaEditoresDelOdeVO.size(); i++) {
					if(listaEditoresDelOdeVO.get(i).getIdUsuarioEditor().contentEquals(usuario)) {
						usuarioAutorizado = true;
						break;
					}
				}
			}
			
			if (!usuarioAutorizado) {
				logger.error("handleDespublicarWebSemantica. El usuario : " + usuario + " no tiene permisos para despublicar el ODE : " + idODE);
				resultado.setDescripcion(I18nModuloPublicacion.getPropertyValueI18n(ERROR_USUARIO_NO_PERMISO_ODE,"es"));
				resultado.setIdODE(idODE);
				resultado.setIdResultado(ERROR_USUARIO_NO_PERMISO_ODE);
				return resultado;
			}

			File extraeSubmanifest = new File(localizadorP.getPath(), MANIFEST_NAME);
			Manifest imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);
			ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
			String identifiadorManifest = manAgrega.getManifest().getIdentifier();
			Lom lom = manAgrega.obtenerLom(identifiadorManifest, null);

			LomAgrega lomAgrega = new LomAgrega(lom);
/*
			List<EntidadAgrega> listaAutores = (List<EntidadAgrega>) lomAgrega.getLifeCycleAgrega().getAutores();
			List<EntidadAgrega> listaPublicadores = (List<EntidadAgrega>) lomAgrega.getLifeCycleAgrega().getPublicadores();

			boolean bUsuarioAutorizado = false;

			for (Iterator<EntidadAgrega> iterator = listaAutores.iterator(); iterator.hasNext();) {
				EntidadAgrega entidadAgrega =  iterator.next();
				logger.info("Identificador autor :" + entidadAgrega.getNombre() + " Correo : " + entidadAgrega.getCorreo());

				if (usuario.equalsIgnoreCase(entidadAgrega.getNombre())||usuario.equalsIgnoreCase(entidadAgrega.getCorreo()))
				{
					bUsuarioAutorizado=true;
					break;
				}

			}

			for (Iterator<EntidadAgrega> iterator = listaPublicadores.iterator(); iterator.hasNext();) {
				EntidadAgrega entidadAgrega =  iterator.next();
				logger.info("Identificador publicador :" + entidadAgrega.getNombre()+ " Correo : " + entidadAgrega.getCorreo());

				if (usuario.equalsIgnoreCase(entidadAgrega.getNombre())||usuario.equalsIgnoreCase(entidadAgrega.getCorreo()))
				{
					bUsuarioAutorizado=true;
					break;
				}

			}
			if (!bUsuarioAutorizado)
			{
				logger.error("handleDespublicarWebSemantica. El usuario : " + usuario + " no tiene permisos para despublicar el ODE : " + idODE);
				resultado.setDescripcion(I18nModuloPublicacion.getPropertyValueI18n(ERROR_USUARIO_NO_PERMISO_ODE,"es"));
				resultado.setIdODE(idODE);
				resultado.setIdResultado(ERROR_USUARIO_NO_PERMISO_ODE);
				return resultado;
			}
*/

			//Despublicamos el ODE 		
			String tituloODE = (String) lomAgrega.getGeneralAgrega().getTitulos().get(0);
			resultado = noDisponible(idODE, usuario, "Se pasa a no disponible el ODE a petición de Web Semántica",tituloODE);
			// Actualizamos la descripción porque al no estar "logado" no puede detectar el idioma y por lo tanto obtener 
			// la descripción del error/éxito
			if (resultado!=null)
				resultado.setDescripcion(I18nModuloPublicacion.getPropertyValueI18n(resultado.getIdResultado(),"es"));
			
			//Des-registramos los editores del ODE
			List<EditoresOdesExternos> listaEditoresDelOde = null;
			listaEditoresDelOde = this.getEditoresOdesExternosDao().buscarEditoresOdesExternosPorIdOde(criterio);
			
			if(listaEditoresDelOde!=null && !listaEditoresDelOde.isEmpty()) 
				this.getEditoresOdesExternosDao().remove(listaEditoresDelOde);
			
		} catch (Exception e) {
			logger.error("handleDespublicarWebSemantica. Error genérico al despublicar el ODE ", e);
			resultado.setDescripcion(I18nModuloPublicacion.getPropertyValueI18n(ERROR_GENERICO,"es"));
			resultado.setIdODE(idODE);
			resultado.setIdResultado(ERROR_GENERICO);
		}
		return resultado;
	}
    
    /*
     * Devuelve todos los ids de los nodos dados de alta en agrega separados por comas
     */
    private String darIdsNodosAgrega() throws Exception {
		//Añadimos el nodo local al resto de nodos
		NodoVO nodoLocal = new NodoVO();
		nodoLocal.setIdNodo(this.getSrvPropiedadService().getValorPropiedad(AgregaProperties.SERVER_ID));
 		NodoVO[] otrosNodos = this.getSrvNodoService().listarNodos();
 		ArrayList<NodoVO> nodos = new ArrayList<NodoVO>(Arrays.asList(otrosNodos));
 		nodos.add(nodoLocal);
 		
 		StringBuffer comunidades=new StringBuffer();
 		for(int i=0; i<nodos.size(); i++) {
 			if(i+1==nodos.size())
 				comunidades.append(nodos.get(i).getIdNodo());
 			else comunidades.append(nodos.get(i).getIdNodo()+",");
 		}
 		return comunidades.toString();
    }
    
    
    /*
     * Este metodo esta basado en el codigo de PublicarControllerImpl
     */
    private String obtenerTextoLicenciaOde (String idODE, String identificadorLicencia) throws Exception, Exception {
	    
    	String textoLicencia ="";
			
		SrvLocalizadorService localizadorService = this.getSrvLocalizadorService();
		LocalizadorVO localizar=localizadorService.consultaLocalizador(idODE);
		String pathLocalizador = localizar.getPath();
		logger.debug("El localizador del ode es "+ pathLocalizador);
		String localizador=pathLocalizador+LICENCIA_NAME;
		File fileLicencia=new File(localizador);

		try{
			if(fileLicencia.exists()) {
				logger.debug("Existe licencia.txt en el ode");
				textoLicencia=UtilesFicheros.obtenerContenidoDeFichero(localizador);
					
			} else {
				//Traduccion al inglés del identificador de licencia;
				TerminoYPadreVO[] typ = this.getSrvVocabulariosControladosService().crearTraduccionAIngles(new String[] {identificadorLicencia});
				String idLicencia=typ[0].getIdTermino();
				
				logger.debug("El archivo licencia.txt no existe para el identificador "+idLicencia);
				
				if(idLicencia.equals("6.2.5")) {
					textoLicencia="Introduzca la descripción";
					
				} else {
					String urlLicencias=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.URL_LICENCIAS);
					String urlEntero=urlLicencias+File.separator+idLicencia+File.separator+LICENCIA_NAME;
			    	
					textoLicencia=UtilesFicheros.obtenerContenidoDeFichero(urlEntero);
					logger.debug("El texto del identificador que el ode traía es "+textoLicencia);
				}
			}
		} catch(Exception ex) {
			logger.error("Error obteniendo el texto de la licencia del ODE "+ idODE+ " " +ex);
		}
		return textoLicencia;
    }
    

    /*
     * Este metodo esta basado en el codigo de PublicarControllerImpl
     */
    private String obtenerIdLicenciaOde (String idODE, String idioma) throws RemoteException, Exception {
    	
		LicenciaVO licenciaVO = handleObtenLicenciaODE(idODE, idioma);
	
		TerminoYPadreVO TerminoYPadreVOida = new TerminoYPadreVO();
		TerminoYPadreVOida.setIdiomaTermino("en");
		TerminoYPadreVOida.setNomTermino(licenciaVO.getTipoLicencia());
		TerminoYPadreVOida.setIdVocabulario("6.2");
		TerminoYPadreVOida.setIdTermino("");
	
		TerminoYPadreVO[] terminoYPadreTitulosVuelta = this.getSrvVocabulariosControladosService()
				.obtenerIdTermino(new TerminoYPadreVO[] { TerminoYPadreVOida });
	
		return terminoYPadreTitulosVuelta[0].getIdTermino();    
    }
    
    
    /**
     * Metodo que devuelve on ResultadoPublicacionVO generado a partis de un ResultadoOperacionVO
     * y unos datos mas que se le pasan como parametros. Este metodo solo lo usa handlePublicarWebSemantica
     * @param resultado
     * @param pathImagen
     * @param pathRepositorio
     * @return
     * @throws Exception
     */
    private ResultadoPublicacionVO ResultadoOperacion2ResultadoPublicacion(ResultadoOperacionVO resultado,
    		String pathImagen, String pathRepositorio) throws Exception {
    	
    	ResultadoPublicacionVO r = new ResultadoPublicacionVO();
    	r.setIdODE(resultado.getIdODE());
    	r.setIdResultado(resultado.getIdResultado());
    	r.setDescripcion(resultado.getDescripcion());
    	r.setTamainoODE(resultado.getTamainoODE());
    	r.setPathImagen(pathImagen);
    	r.setPathRepositorio(pathRepositorio);

    	//r.setNodoPublicacion(this.getSrvPropiedadService().getValorPropiedad(AgregaProperties.SERVER_ID));
		String url = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.HOST);
		String subdominio = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SUBDOMINIO);
		if(subdominio.isEmpty() || url.endsWith("/") || subdominio.startsWith("/"))
			r.setNodoPublicacion(url+subdominio);
		else
			r.setNodoPublicacion(url+"/"+subdominio);
			
    	return r;
    }
    
    
	@Override
	protected ResultadoPublicacionVO handlePublicacionExterna(byte[] zipODE,
			byte[] catalogacionReducida, String correoUsuario, String titulo,
			String[] listaUsuarioEditores, boolean esNuevaVersion, String tipoPublicacion)
			throws Exception {
		
		String comentarios=MARCA_ODE_PUBLICADO_POR_WEB_SEMANTICA+" por el usuario "+correoUsuario;
		String idioma="es";
		String ambitoPublicacion=UNIVERSAL;
		String comunidades=darIdsNodosAgrega();
		String idUsuario="";
		
		ResultadoOperacionVO resultado = new ResultadoOperacionVO();
		ResultadoOperacionVO resultadoRollback = new ResultadoOperacionVO();
	
		String nombreFicheroZip = "ficheroODE.zip";
		// Validamos que el usuario exista en Agrega
		// Obtenemos el usuario que ha creado el ODE para incluirlo en la lista.
		// Esto se hace para aquellos casos en los que no se ha incluido como una contribución de Autor cuando se creo
		try
		{
			if (logger.isDebugEnabled())
				logger.debug("handlePublicarWebSemantica.Verificamos que el usuario exista en Agrega : " + idUsuario);

			UsuarioVO usuarioVO = getSrvAdminUsuariosService().obtenerDatosUsuarioPorEmail(correoUsuario);
			
			if (usuarioVO==null)
			{
				resultado.setDescripcion("El usuario no se ha podido verificar");
				resultado.setIdResultado(ERROR_USUARIO_DESPUB_OBLIGATORIO);
				return ResultadoOperacion2ResultadoPublicacion(resultado,"","");
			}else
				idUsuario=usuarioVO.getUsuario();
			
		} catch (Exception e) {
			logger.error("No se ha podido verificar el usuario : ", e);
			resultado.setDescripcion("El usuario no se ha podido verificar");
			resultado.setIdResultado(ERROR_USUARIO_DESPUB_OBLIGATORIO);
			return ResultadoOperacion2ResultadoPublicacion(resultado,"","");
		}
			
		
		//Pasamos los ficheros que nos llegan en byte[] codificados en base64 a DataHadlers
		DataSource dataSourceZipODE = new ByteArrayDataSource(decode(zipODE), "application/zip");
		DataHandler dataHandlerZipODE = new DataHandler(dataSourceZipODE);

		DataSource dataSourceCatalogacionReducida = null;
		DataHandler dataHandlerCatalogacionReducida = null;
		if (catalogacionReducida!=null && catalogacionReducida.length > 0)
		{			
			dataSourceCatalogacionReducida = new ByteArrayDataSource(decode(catalogacionReducida), "application/xml");
			dataHandlerCatalogacionReducida = new DataHandler(dataSourceCatalogacionReducida);
		}
		
		//El id del ODE lo generamos de la misma forma que lo hace en la primera orden de handleCrearPifConCuota
		String idODE=es.pode.soporte.uuid.PodeUUIDGenerator.getOdeUUID(String.valueOf(System.currentTimeMillis()));
		
		//Con este metodo importamos a la carpeta del usuario el ODE que nos pasen
		//El idioma podemos ponerlo fijo ya que se usara para crear un manifest que lo recubra
		//solo en caso que lo que nos pasen no sea un ODE.
		//resultado=handleCrearPifConCuotaID(zipODE, idUsuario, comentarios, titulo, idioma, idODE);		
		resultado=crearPIFAux(dataHandlerZipODE, idUsuario, comentarios, nombreFicheroZip, idioma, true, idODE,null);

		if(!resultado.getIdResultado().equals(SIN_ERRORES) && !resultado.getIdResultado().equals(VOCAB_BORRADOS)) {
			logger.error("Error al importar desde web semantica el ODE "+idODE+" a la carpeta del usuario "+idUsuario);
			return ResultadoOperacion2ResultadoPublicacion(resultado,"","");
		}
		
		// Dependiendo del tipo de publicación que solicita Web Semantica podemos necesitar hacer más cosas
		// ODE_COMPLETO. No hace falta hacer nada más
		// ODE_CATALOGACION_MINIMA. Hay que crear catalogación LOMES y actualizarla con los datos que pasa Web Semántica
		// ZIP_FICHEROS. Hay que crear catalogación LOMES básica
	
		if (tipoPublicacion.equalsIgnoreCase(ZIP_FICHEROS) || tipoPublicacion.equalsIgnoreCase(ODE_CATALOGACION_MINIMA))
			actualizarManifestCatalogacionWebSemantica(idODE, idUsuario, idioma, titulo, dataHandlerCatalogacionReducida);

		// Gestión de contribuciones de autor
		// Modificamos el ciclo de vida, le introducimos una contribución de tipo autor por cada uno de los que nos solicitan
		if (listaUsuarioEditores!=null && listaUsuarioEditores.length>0)
		{
			insertarContribucionesAutoresODE(idODE,listaUsuarioEditores);
		}	
		
		//Con este metodo se propone el ODE para que sea catalogado
		resultado=handleProponerCatalogacionNuevaVersion(idODE, idUsuario, comentarios, titulo,	esNuevaVersion);
		if(!resultado.getIdResultado().equals(SIN_ERRORES)) {
			logger.error("Error al porponer para catalogacion desde web semantica el ODE "+idODE+" del usuario "+idUsuario);
			//Rollback si no es nueva version. De esta forma no perderemos el ODE original
			if(!esNuevaVersion) {
				resultadoRollback = handleEliminar(idODE, idUsuario);
				if(!resultadoRollback.getIdResultado().equals(SIN_ERRORES)) {
					logger.error("Error al hacer rollback de una publicacion desde web semantica");
					logger.error("Error al eliminar el ODE "+idODE+" de la carpeta del usuario "+idUsuario+": "+ resultadoRollback.getDescripcion());
				}
			}
			return ResultadoOperacion2ResultadoPublicacion(resultado,"","");
		}
				
		//Con este metodo se propone el ODE para que sea publicado
		resultado=handleProponerPublicacion(idODE, idUsuario, comentarios, titulo);
		if(!resultado.getIdResultado().equals(SIN_ERRORES)) {
			logger.error("Error al porponer para publicacion desde web semantica el ODE "+idODE+" del usuario "+idUsuario);
			//Rollback si no es nueva version. De esta forma no perderemos el ODE original
			if(!esNuevaVersion) {
				resultadoRollback = handleEliminar(idODE, idUsuario);
				if(!resultadoRollback.getIdResultado().equals(SIN_ERRORES)) {
					logger.error("Error al hacer rollback de una publicacion desde web semantica");
					logger.error("Error al eliminar el ODE "+idODE+" de la carpeta del usuario "+idUsuario+": "+ resultadoRollback.getDescripcion());
				}
			}
			return ResultadoOperacion2ResultadoPublicacion(resultado,"","");
		}

		//Ejemplo identificadorLicencia: 'creative commons: attribution'
		String identificadorLicencia=obtenerIdLicenciaOde(idODE, idioma);
		String textoLicencia=obtenerTextoLicenciaOde(idODE, identificadorLicencia);
		
		LicenciaVO licenciaVO = handleObtenLicenciaODE(idODE, idioma);
		
		resultado=handlePublicar(idODE, idUsuario, comentarios, titulo, comunidades, ambitoPublicacion, licenciaVO.getTipoLicencia(), textoLicencia);
		if(!resultado.getIdResultado().equals(SIN_ERRORES)) {
			logger.error("Error al publicar desde web semantica el ODE "+idODE+" del usuario "+idUsuario);
			//Rollback si no es nueva version. De esta forma no perderemos el ODE original
			if(!esNuevaVersion) {
				resultadoRollback = handleEliminar(idODE, idUsuario);
				if(!resultadoRollback.getIdResultado().equals(SIN_ERRORES)) {
					logger.error("Error al hacer rollback de una publicacion desde web semantica");
					logger.error("Error al eliminar el ODE "+idODE+" de la carpeta del usuario "+idUsuario+": "+ resultadoRollback.getDescripcion());
				}
			}
			return ResultadoOperacion2ResultadoPublicacion(resultado,"","");
		}
		
		//Registramos los editores del ODE
		/*
		this.getEditoresOdesExternosDao().create(correoUsuario, resultado.getIdODE(), 1);
		if(listaUsuarioEditores!=null) {
			for(int i=0; i<listaUsuarioEditores.length; i++)
				this.getEditoresOdesExternosDao().create(listaUsuarioEditores[i], resultado.getIdODE(), 0);
		}
		*/
		registrarEditoresOde(resultado.getIdODE(), listaUsuarioEditores, correoUsuario);
		
		DocVO30 datosOde = this.getSrvBuscadorService().busquedaMEC(resultado.getIdODE(), "");
		String pathImagen = datosOde.getImagen();
		LocalizadorVO l = this.getSrvLocalizadorService().consultaLocalizador(resultado.getIdODE());
		String pathRepositorio = l.getPath().replace("uploads", "");
		
		return ResultadoOperacion2ResultadoPublicacion(resultado, pathImagen, pathRepositorio);
	}
	
	
	private void registrarEditoresOde(String idOde, String[] listaUsuarioEditores, String emailPublicador) {

		try {
			UsuarioVO usuario = getSrvAdminUsuariosService().obtenerDatosUsuarioPorEmail(emailPublicador);
			if(usuario==null) {
				logger.error("No se encontraron los datos del email usuario publicador externo "+emailPublicador);
				return;
			} else 					
				this.getEditoresOdesExternosDao().create(usuario.getUsuario(), idOde, 1);
		} catch (Exception e) {
			logger.error("Error al obtener datos del email usuario "+emailPublicador);
			return;
		}
		
		if(listaUsuarioEditores!=null) {
			for(int i=0; i<listaUsuarioEditores.length; i++) {
				
				try {
					UsuarioVO usuario = getSrvAdminUsuariosService().obtenerDatosUsuarioPorEmail(listaUsuarioEditores[i]);
					if(usuario==null)
						logger.warn("No se encontraron los datos del email usuario externo "+listaUsuarioEditores[i]);
					else 					
						this.getEditoresOdesExternosDao().create(usuario.getUsuario(), idOde, 0);
					
				} catch (Exception e) {
					logger.error("Error al obtener datos del email usuario "+listaUsuarioEditores[i]);
				}
			}
		}
	}

	
	@Override
	protected String[] handleObtenerEditoresOdeExterno(String idODE)
			throws Exception {

		List<String> listaEditores = new ArrayList<String>();	

		try	{ 
			//Obtenemos el publicador del ODE
			EditoresOdesExternosIdOdeEsPublicadorOdeCriteria criterio = new EditoresOdesExternosIdOdeEsPublicadorOdeCriteria();
			criterio.setIdOde(idODE);
			criterio.setEsPublicadorOde(1);
			List<EditoresOdesExternosVO> listaPublicadoresDelOde = null;
			listaPublicadoresDelOde = this.getEditoresOdesExternosDao().buscarEditoresOdesExternosPorIdOdeEsPublicadorOde(EditoresOdesExternosDao.TRANSFORM_EDITORESODESEXTERNOSVO, criterio);
			
			if(listaPublicadoresDelOde==null || listaPublicadoresDelOde.isEmpty()) {
				logger.error("No se encontro publicador para el ODE externo "+idODE);
				return null;
				
			} else if(listaPublicadoresDelOde!=null && listaPublicadoresDelOde.size()>1) {
				logger.error("Se encontro mas de un publicador para el ODE externo "+idODE);
				return null;
			}
			
			listaEditores.add(listaPublicadoresDelOde.get(0).getIdUsuarioEditor());

		} catch (Exception e) {
			logger.error("Error al obtener el publicador del ODE externo "+idODE+": ", e);		
			return null;
		}
		
		try	{ 
			//Obtenemos los editores del ODE
			EditoresOdesExternosIdOdeEsPublicadorOdeCriteria criterio = new EditoresOdesExternosIdOdeEsPublicadorOdeCriteria();
			criterio.setIdOde(idODE);
			criterio.setEsPublicadorOde(0);
			List<EditoresOdesExternosVO> listaEditoresDelOde = null;
			listaEditoresDelOde = this.getEditoresOdesExternosDao().buscarEditoresOdesExternosPorIdOdeEsPublicadorOde(EditoresOdesExternosDao.TRANSFORM_EDITORESODESEXTERNOSVO, criterio);

			if(listaEditoresDelOde!=null && !listaEditoresDelOde.isEmpty()) 
				for(int i=0; i<listaEditoresDelOde.size(); i++)
					listaEditores.add(listaEditoresDelOde.get(i).getIdUsuarioEditor());
			
			String[] listaEditoresMetodoAntiguo = handleObtenerEditoresOdeExternoAntiguo(idODE);

			//Hacemos merge con la lista que obtendria el metodo antiguo
			for(int a=0; a<listaEditoresMetodoAntiguo.length; a++) {
				boolean usuarioEncontrado = false;
				for(int i=0; i<listaEditoresDelOde.size(); i++) {
					if(listaEditoresMetodoAntiguo[a].contentEquals(listaEditores.get(i))) {
						usuarioEncontrado = true;
						break;
					}
				}
				if(!usuarioEncontrado)
					listaEditores.add(listaEditoresMetodoAntiguo[a]);
			}
			
		} catch (Exception e) {
			logger.error("Error al obtener los editores del ODE externo "+idODE+": ", e);		
			return null;
		}
		
		return listaEditores.toArray(new String[listaEditores.size()]);
	}
	

	private String[] handleObtenerEditoresOdeExternoAntiguo(String idODE)
			throws Exception {
		
		String[] resultado = null;		
		LocalizadorVO localizadorP = null;

		if (logger.isDebugEnabled())
			logger.debug("handleObtenerEditoresOdeWebSemantica. Se ha solicitado los editores del ODE : " + idODE);	
		
		try
		{		
			localizadorP = getSrvLocalizadorService().consultaLocalizador(idODE);			
		}catch (Exception e) {
			logger.error("No se ha localizado el ODE : ", e);		
			return resultado;
		}

		// Obtenemos el usuario que ha creado el ODE para incluirlo en la lista.
		// Esto se hace para aquellos casos en los que no se ha incluido como una contribución de Autor cuando se creo
		String usuarioCreacion="";
		try
		{
			//Obtenemos el creador de la primera transicion; en otras palabras el creador del ODE
			Transicion t = obtenerPrimeraTransicion(idODE);
			usuarioCreacion = t.getIdUsuarioCreacion();
			
			if (logger.isDebugEnabled())
				logger.debug("Correo del usuario de creación en Agrega : " +usuarioCreacion);
			
		} catch (Exception e) {
			logger.error("No se ha podido obtener la última transición : ", e);		
			return resultado;
		}
					
		// Obtenemos los usuarios de las contribuciones con rol autor en el manifest
		File extraeSubmanifest = new File(localizadorP.getPath(), MANIFEST_NAME);
		Manifest imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);
		ManifestAgrega manAgrega = new ManifestAgrega(imsmanifest);
		String identifiadorManifest = manAgrega.getManifest().getIdentifier();
		Lom lom = manAgrega.obtenerLom(identifiadorManifest, null);

		LomAgrega lomAgrega = new LomAgrega(lom);

		List<EntidadAgrega> listaAutores = (List<EntidadAgrega>) lomAgrega.getLifeCycleAgrega().getAutores();

		if (listaAutores!=null && listaAutores.size()>0)
		{		
			List<String> listaUsuarios = new ArrayList<String>();
      
      // 09072015 Insertamos el usuario de creación en la primera posición para devolverlo siempre en una posición fija
			listaUsuarios.add(usuarioCreacion);
			
			if (logger.isDebugEnabled())
				logger.debug("handleObtenerEditoresOdeWebSemantica. Obtiene editores del ODE : " + listaAutores.size());	

			for (Iterator<EntidadAgrega> iterator = listaAutores.iterator(); iterator.hasNext();) {
				EntidadAgrega entidadAgrega =  iterator.next();					
				listaUsuarios.add(entidadAgrega.getNombre());			
			}
// 09072015 Movemos este código para devolver siempre el usuario que crea el ODE en primera posición       
			// Controlamos si el usuario de creación en Agrega ya se incluye por estar en contribución con rol autor
//			if (!listaUsuarios.contains(usuarioCreacion))
//				listaUsuarios.add(usuarioCreacion);
			
			resultado = listaUsuarios.toArray(new String[0]);
						
		} else {
			resultado = new String[1];
			resultado[0]=usuarioCreacion;
		}
		return resultado;
	}	
	

	private String idUsuario2NombreApellidos(String idUsuario) {
		
		UsuarioVO usuario=null;
		try {
			usuario = this.getSrvAdminUsuariosService().obtenerDatosUsuario(idUsuario);
		} catch (Exception e) {
			logger.error("Error al obtener datos del idUsuario "+idUsuario);
			return "";
		}
		if(usuario==null) {
			logger.warn("No se encontraron los datos del idUsuario externo "+idUsuario);
			return "";
		}
		if(usuario.getApellido2()!=null && !usuario.getApellido2().isEmpty())
			return usuario.getNombre() + " " + usuario.getApellido1() + " " + usuario.getApellido2();
		if(usuario.getApellido1()!=null && !usuario.getApellido1().isEmpty())
			return usuario.getNombre() + " " + usuario.getApellido1();
		return usuario.getNombre();
	}
	

	/**
	 * Metodo que dado un email de usuario en agrega, devuelve su nobre y apellidos
	 */
	private String emailUsuario2NombreApellidos(String email) {
		
		UsuarioVO usuario=null;
		try {
			usuario = getSrvAdminUsuariosService().obtenerDatosUsuarioPorEmail(email);
		} catch (Exception e) {
			logger.error("Error al obtener datos del email usuario "+email);
			return "";
		}
		if(usuario==null) {
			logger.warn("No se encontraron los datos del email usuario externo "+email);
			return "";
		}
		if(usuario.getApellido2()!=null && !usuario.getApellido2().isEmpty())
			return usuario.getNombre() + " " + usuario.getApellido1() + " " + usuario.getApellido2();
		if(usuario.getApellido1()!=null && !usuario.getApellido1().isEmpty())
			return usuario.getNombre() + " " + usuario.getApellido1();
		return usuario.getNombre();
	}
	
	
	/**
	 * Metodo que dados una lista de emails de usuario en agrega, devuelve su equivalencia en nobre y apellidos
	 * Eliminara eauqllos usuarios que no estan registrados en Agrega
	 */
	private String[] emailsUsuarios2NombresApellidos(String[] emails) {
		
		if(emails==null)
			return null;
		
		ArrayList<String> nombresApellidos = new ArrayList<String>();
		
		for(int i=0; i<emails.length; i++) {
			String tmp = emailUsuario2NombreApellidos(emails[i]);
			if(tmp!=null && !tmp.isEmpty())
				nombresApellidos.add(tmp);
		}
		return nombresApellidos.toArray(new String[nombresApellidos.size()]);
	}

	
	private boolean insertarContribucionesAutoresODE(String idODE, String[] correosUsuarios)
	{

		boolean resultado = false;
		LocalizadorVO localizadorP = null;

		if (logger.isDebugEnabled())
			logger.debug("insertarContribucionesAutoresODE. Va a insertar contribuciones al ODE : " + idODE);	

		try
		{			
			localizadorP = getSrvLocalizadorService().consultaLocalizador(idODE);
			File extraeSubmanifest = new File(localizadorP.getPath(), MANIFEST_NAME);
			Manifest imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);

			String[] nombresApellidosUsuarios = emailsUsuarios2NombresApellidos(correosUsuarios);
			TratamientoODE.introduceAutorWebSemanticaManifest(localizadorP.getPath(), nombresApellidosUsuarios, imsmanifest, (ScormDao) this.getScormDao());	
						
			resultado=true;			
		}catch (Exception e) {
			logger.error("No se ha podido insertar contribuciones al ODE : ", e);		
			return resultado;
		}
		return resultado;
	}
	
    /*
     * Actualiza el imsmanifest del ODE importado al publicar desde Web Semántica.
     * Crea una catalogación básica LOMes y si se ha pasado la catalogación reducida desde Web Semántica, amplia la información.
     * También actualiza en el imsmanifest la estructura de recursos y organización.
     */
	private boolean actualizarManifestCatalogacionWebSemantica(String identificador, String usuario, String idioma, String titulo, DataHandler ficCatalogacion) throws Exception
	{

		boolean resultado = false;
		
		LocalizadorVO localizadorP = null;

		File ficTemp = null;
		File dirTemporal = null;
			
		if (logger.isDebugEnabled())
			logger.debug("actualizarManifestCatalogacionWebSemantica. Va a actualizar  : " + identificador);	

		try
		{	
			
			// Obtenemos el imsmanifest del ODE importado
			localizadorP = getSrvLocalizadorService().consultaLocalizador(identificador);			
			File extraeSubmanifest = new File(localizadorP.getPath(), MANIFEST_NAME);
			Manifest imsmanifest = this.getScormDao().parsearODEEager(extraeSubmanifest);
			
			LomAgrega lomAgregaCatReducida = null;
			// Si pasan catalogacion reducida la parseamos para respetar lo que cataloga Web Semántica
			// Este caso es el que en Web Semántica nos pasan un ODE no catalogado internamente y una catalogación reducida
			// Hay que crear una catalogación básica, parsear la catalogacion reducida y completar la básica
			if (ficCatalogacion!=null)
			{			 
				// Creamos un directorio en el temporal de la máquina con el currentTimeMilis para que pueda parsearse la catalogación de WS 
				String nombreDirTemporal = System.getProperty("java.io.tmpdir")+ System.getProperty("file.separator") + System.currentTimeMillis();									
				dirTemporal = new File(nombreDirTemporal);			
				dirTemporal.mkdir();
				
				if (logger.isDebugEnabled())
					logger.debug("actualizarManifestCatalogacionWebSemantica. Se ha creado el directorio temporal" + nombreDirTemporal);
				
							
				// Volcamos el imsmanifest que nos pasan al directorio temporal. 
				ficTemp = new File(nombreDirTemporal + System.getProperty("file.separator") +"imsmanifest.xml");							
	
				if (logger.isDebugEnabled())
					logger.debug("actualizarManifestCatalogacionWebSemantica. Se ha creado el fichero temporal" + ficTemp.getAbsolutePath());
	
				OutputStream os = new FileOutputStream(ficTemp);					
				ficCatalogacion.writeTo(os);
				
				// 07082014 Pasamos el proceso que limpiar los caracteres extraños del imsmanifest y de los ficheros físicos.
				eliminarCaracteresExtranosDeManifest(localizadorP.getPath(),nombreDirTemporal);
				eliminarCaracteresExtranosDelTituloYOrganizaciones(localizadorP.getPath());
				
				// Parseamos la catalogación que nos pasa WS
				Manifest imsmanifestReducido = this.getScormDao().parsearODEEager(ficTemp);
				ManifestAgrega manAgregaReducido = new ManifestAgrega(imsmanifestReducido);				
				Lom lomReducido = manAgregaReducido.obtenerLom(manAgregaReducido.getManifest().getIdentifier(), null);
				// Cargamos el objeto para usarlo posteriormente
				lomAgregaCatReducida = new LomAgrega(lomReducido);	
				
				// Obtenemos la estructura de los recursos y la organización que nos pasa Web Semántica
				imsmanifest.setOrganizations(imsmanifestReducido.getOrganizations());
				imsmanifest.setResources(imsmanifestReducido.getResources());

				if (logger.isDebugEnabled())
					logger.debug("actualizarManifestCatalogacionWebSemantica. Se ha actualizado la organización y los recursos del imsmanifest");

			}			
		
			imsmanifest.getMetadata().setGrp_any(new Grp_any());
			imsmanifest.getMetadata().getGrp_any().addAnyObject(generarLomInicial(titulo, idioma, imsmanifest.getIdentifier(),lomAgregaCatReducida));	    							

			if (logger.isDebugEnabled())
				logger.debug("actualizarManifestCatalogacionWebSemantica. Se ha actualizado la catalogación LOMes");

			// guardamos el manifest modificado
			File fManifest = new File(localizadorP.getPath(), SrvPublicacionServiceImpl.MANIFEST_NAME);
			this.getScormDao().escribirODE(imsmanifest, fManifest);
			
			if (logger.isDebugEnabled())
				logger.debug("actualizarManifestCatalogacionWebSemantica. Se ha volcado a disco el imsmanifest");

						
			resultado=true;			
		}catch (Exception e) {
			logger.error("generaLOMesODEBasicoWebSemantica: ", e);		
			
		}
		finally
		{
			try
			{
				ficTemp.delete();
				dirTemporal.delete();
			}catch (Exception e) {
				ficTemp=null;
				dirTemporal=null;
			}
		}
		return resultado;
	}
	
	
    /*
     * Genera una catalogación LOMes básica.
     * Por defecto incluye la misma información que si se catalogase desde el catalogador básico de Agrega
     * Si le pasan la catalogación reducida desde Web Semántica, esta prevalece.
     */
	private Lom generarLomInicial(String titulo, String idioma, String identificador, LomAgrega lomAgregaCatReducida) throws Exception {
		
		Lom lom = new Lom();
		LomAgrega la = new LomAgrega(lom);		
		
		// LOM 1 - General
		GeneralAgrega ga = new GeneralAgrega(new General());
		// Titulo		
		ga.addTituloIdioma(idioma, titulo);
		// Identificador
		IdentificadorAgrega id = new IdentificadorAgrega();
		id.setCatalogo(ObtieneSrvPropiedad().getValorPropiedad(AgregaProperties.CATALOGO_AGREGA));
		id.setEntrada(identificador);
		ArrayList<IdentificadorAgrega> lista = new ArrayList<IdentificadorAgrega>();
		lista.add(id);
		ga.setIdentificadoresAv(lista);
		// Idioma
		ga.setIdioma(0, idioma);
		// Descripcion
		ga.setDescripcion(0, "Creación automática desde Web Semántica", idioma);
		// Nivel agregación
		ga.setNivelDeAgregacion("1");		
		
		// Si Web Semántica los informa, actualizamos los valores por defecto por los informados en Web Semantica
		if (lomAgregaCatReducida!=null)
		{
			String idiomaCatRed = lomAgregaCatReducida.getGeneralAgrega().getIdioma(0);					
			ga.setTitulo(lomAgregaCatReducida.getGeneralAgrega().getTitulo(idiomaCatRed),idiomaCatRed);
			ga.setIdioma(0, lomAgregaCatReducida.getGeneralAgrega().getIdioma(0));
			ga.setDescripcion(0,lomAgregaCatReducida.getGeneralAgrega().getDescripcion(0, idiomaCatRed),idiomaCatRed);
			ga.setNivelDeAgregacion(lomAgregaCatReducida.getGeneralAgrega().getNivelDeAgregacion());
		}
		
		la.setGeneralAgrega(ga);
		
		// LOM 2 - Ciclo de vida
		// Se actualiza con los que envía Web Semantica
		la.setLifeCycleAgrega(lomAgregaCatReducida.getLifeCycleAgrega());
		
		// LOM 3 - metaMetadata
		// Por defecto
		MetaMetadataAgrega metaA = new MetaMetadataAgrega(new MetaMetadata());
		String schema = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ESQUEMA_LOMES);
		if(logger.isDebugEnabled()) logger.debug("Esquema de metadatos : " + schema);
		metaA.addEsquemaDeMetadatos(schema);
		// Idioma del metadato = idioma del usuario que cataloga
		metaA.setIdioma(idioma);
		la.setMetaMetadataAgrega(metaA);
		
		// LOM 5 - Educational
		// Si Web Semántica los informa, actualizamos los valores por defecto por los informados en Web Semantica
		if (lomAgregaCatReducida!=null)
		{			
			for (Iterator<EducationalAgrega> iterator = lomAgregaCatReducida.getEducationalsAgrega().iterator(); iterator.hasNext();) {
				EducationalAgrega eduAg = iterator.next();
				la.addEducationalAgrega(eduAg);	
				
			}			
		}
		else
		{
			EducationalAgrega edu = new EducationalAgrega(new Educational());
			edu.setTipoDeRecurso(0, TIPO_RECURSO_DEFECTO);
			edu.setIdiomaDestinatario(0, idioma);
			EducationalAgrega[] eduAgrega = new EducationalAgrega[1];				
			eduAgrega[0]=edu;
			
			la.setEducationalsAgrega(eduAgrega);
		}
		
		// LOM 6 - Rights		
		// Si Web Semántica los informa, actualizamos los valores por defecto por los informados en Web Semantica
		if (lomAgregaCatReducida!=null)
		{
			la.setRightsAgrega(lomAgregaCatReducida.getRightsAgrega());
		}		
		else
		{
			RightsAgrega rights= new RightsAgrega(new Rights());
			rights.setDerechosDeAutor(DERECHOS_AUTOR_DEFECTO);
			rights.setAcceso(TIPO_ACCESO_UNIVERSAL, DESC_TIPO_ACCESO);
			la.setRightsAgrega(rights);
		}
		
		// LOM 9 - Classification
		// Si Web Semántica los informa, actualizamos los valores por defecto por los informados en Web Semantica
		if (lomAgregaCatReducida!=null)
		{
			for (Iterator<ClassificationAgrega> iterator = lomAgregaCatReducida.getClassificationsAgrega().iterator(); iterator.hasNext();) {
				ClassificationAgrega claAgrega = iterator.next();
				la.addClassificationAgrega(claAgrega);
				
			}
		}		
		
		return la.getLom();
	}

	@Override
	protected String handleSubirFichero(byte[] fichero, String nombreFic) throws Exception {
		
		byte[] desco = decode(fichero);
		
		FileOutputStream fos = new FileOutputStream("uploads/export/" + nombreFic);
		fos.write(desco);
		fos.close();
		
		String sUrl = "http://agrega-2hapre.pntic.mec.es/export/" + nombreFic;
		     
		return sUrl;
		
	}	
	
	public static byte[] decode(byte[] b) throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(b);
		InputStream b64is = MimeUtility.decode(bais, "base64");
		byte[] tmp = new byte[b.length];
		int n = b64is.read(tmp);
		byte[] res = new byte[n];
		System.arraycopy(tmp, 0, res, 0, n);
		return res;
	}
	 
	private boolean esUnMail (String mail) {
		if(mail==null || mail.isEmpty()) return false;
		if(mail.contains("@")) {
			String[] email = mail.split("@");
			if(email.length!=2) return false;
			if(!email[1].contains(".")) return false;
			String[] emailDomain = email[1].split("\\.");
			if(emailDomain.length<2) return false;
			return true;
		}
		return false;
	}  
	

	@Override
	protected String[] handleObtenerOdesEditablesUsuarioExterno(
			String usuario) throws Exception {

		List<String> listaIdsOdes = new ArrayList<String>();

		try	{ 
			EditoresOdesExternosIdUsuarioEditorCriteria criterio = new EditoresOdesExternosIdUsuarioEditorCriteria();
			criterio.setIdUsuarioEditor(usuario);
			List<EditoresOdesExternosVO> listaOdes = null;
			listaOdes = this.getEditoresOdesExternosDao().buscarEditoresOdesExternosPorIdUsuarioEditor(EditoresOdesExternosDao.TRANSFORM_EDITORESODESEXTERNOSVO, criterio);
			
			if(listaOdes==null || listaOdes.isEmpty()) 
				return listaIdsOdes.toArray(new String[listaIdsOdes.size()]);
			
			for(int i=0; i<listaOdes.size(); i++)
				listaIdsOdes.add(listaOdes.get(i).getIdOde());
			
			String[] listaOdesMetodoAntiguo = handleObtenerOdesEditablesUsuarioExternoAntiguo(usuario);

			//Hacemos merge con la lista que obtendria el metodo antiguo
			for(int a=0; a<listaOdesMetodoAntiguo.length; a++) {
				boolean odeEncontrado = false;
				for(int i=0; i<listaIdsOdes.size(); i++) {
					if(listaOdesMetodoAntiguo[a].contentEquals(listaIdsOdes.get(i))) {
						odeEncontrado = true;
						break;
					}
				}
				if(!odeEncontrado)
					listaIdsOdes.add(listaOdesMetodoAntiguo[a]);
			}				

		} catch (Exception e) {
			logger.error("Error al obtener odes editables por el usuario externo "+usuario+": ", e);		
			return null;
		}
		
		return listaIdsOdes.toArray(new String[listaIdsOdes.size()]);
	}
	
	
	private String[] handleObtenerOdesEditablesUsuarioExternoAntiguo(
			String usuario) throws Exception {

		boolean usuarioRegistrado=false;
		
		UsuarioVO usuarioVO = this.getSrvAdminUsuariosService().obtenerDatosUsuario(usuario);
		if (usuarioVO!=null && usuarioVO.getUsuario()!=null) {
			usuario=usuarioVO.getUsuario();
			usuarioRegistrado=true;
		}		
		
		ArrayList<String> idODEsEditables=new ArrayList<String>();
		
		if(usuarioRegistrado) {
			//Obtengo los ODEs que ha publicado el usuario
			TransicionVO[] odesPublicados = handleObtenODEsPublicadosPorUsuario(usuario);
			for(int i=0; i<odesPublicados.length; i++)
				idODEsEditables.add(odesPublicados[i].getIdODE());
		}
		
		//Obtengo los ODEs en los que el usuario ha contribuido como publicador o como autor
		String[] idODEsContribuidos = this.getSrvIndexadorService().obtenerOdesConUsuarioEnContribucion(usuario);
		
		for(int i=0; i<idODEsContribuidos.length; i++) {
			
			//Obtenemos todas las transiciones
			TransicionVO[] t = handleObtenHistorialPorIdODE(idODEsContribuidos[i]);
			EstadoVO e = handleObtenEstadoPorIdODE(idODEsContribuidos[i],"");
			
			//Solo nos interesan los publicados que hayan sido alguna vez publicados por web semantica
			if (e!=null && e.getClave().equals(PUBLICADO)) {
				//Ahora revisamos si alguna vez fue publicado por web semantica
				for(int j=0; j<t.length; j++) {
					if(t[j]!=null && t[j].getComentarios()!=null) {
						if(t[j].getComentarios().startsWith(MARCA_ODE_PUBLICADO_POR_WEB_SEMANTICA)) {
							idODEsEditables.add(idODEsContribuidos[i]);
							break;
						}
					}
				}
			}
			
		}					
		return (String[])idODEsEditables.toArray(new String[0]);
	}

	
	/**
	 * Metodo que obtiene la lista de ODEs despublicados por usuario que lo despublico, titulo del ODE, y
	 * fecha de despublicacion
	 */
	@Override
	protected TransicionVO[] handleObtenODEsDespublicadosPorTituloUsuarioDespublicadorFecha(String idUsuario, String fechaInicio,
			String fechaFin, String titulo) throws Exception {	
		
		//SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
		Date dateIni = null;
		Date dateFin = null;
		
		// Comprobamos y ajustamos las fechas
		if (fechaInicio==null || fechaInicio.isEmpty())
			dateIni = new Date(0); // si la fecha de inicio es vacia, cogemos 1970 como inicio.
		else 
			dateIni = formatoDelTexto.parse(fechaInicio);
		
		if (fechaFin==null || fechaFin.isEmpty())
			dateFin = new Date(); // si la fecha de fin es vacia, utilzamos hoy como tope.
		else
			dateFin = formatoDelTexto.parse(fechaFin);
			
		if (dateIni.after(dateFin)) // hay una inconsistencia con las fechas, no seguimos
		{
			String msg = "Error invocando la eliminacion de odes. Fechas desde[" + fechaInicio.toString()
			+ "] y hasta[" + fechaFin.toString() + "] inconsistentes.";
			logger.warn(msg);
			throw new Exception(msg);
		}
		
		//Afinamos las fechas
        Calendar inicio = Calendar.getInstance();
        inicio.setTime(dateIni);
        inicio.set(Calendar.AM_PM, 0);
        inicio.set(Calendar.HOUR, 0);
        inicio.set(Calendar.MINUTE, 0);
        inicio.set(Calendar.SECOND, 0);
        
        Calendar fin = Calendar.getInstance();
        fin.setTime(dateFin);
        fin.set(Calendar.AM_PM, 1);
        fin.set(Calendar.HOUR, 11);
        fin.set(Calendar.MINUTE, 59);
        fin.set(Calendar.SECOND, 59);
		
		try {
			List<TransicionVO> transiciones = null;
			Estado estadoNoDisponible = getEstadoDao().obtenEstadoPorNombre(NO_DISPONIBLE);

			String msg = "Obteniendo la lista de ODEs Despublicados con titulo [" + titulo + "], usuario [" + idUsuario + "], desde ["
					+ fechaInicio.toString() + "], hasta [" + fechaFin.toString() + "]";
			
			logger.debug(msg);
			
			//Si solo recibo el usuario busco por fecha y usuario
			if(idUsuario!=null && !idUsuario.isEmpty() && (titulo==null || titulo.isEmpty()) ) {
				
				EstadoUsuarioDespublicadorDesdeHastaCriteria criterio = new EstadoUsuarioDespublicadorDesdeHastaCriteria();
				criterio.setFechaDesde(inicio);
				criterio.setFechaHasta(fin);
				criterio.setEstadoActual(estadoNoDisponible);
				criterio.setEstadoTransitado(null);
				criterio.setIdUsuario(idUsuario);
				
				transiciones = getTransicionDao().buscarODEsPorEstadoUsuarioDespublicadorDesdeHasta(TransicionDao.TRANSFORM_TRANSICIONVO, criterio);
			
			//Si solo recibo el titulo busco por titulo y fecha
			} else if(titulo!=null && !titulo.isEmpty() && (idUsuario==null || idUsuario.isEmpty()) ) {

				EstadoTituloDesdeHastaCriteria criterio = new EstadoTituloDesdeHastaCriteria();
				criterio.setFechaDesde(inicio);
				criterio.setFechaHasta(fin);
				criterio.setEstadoActual(estadoNoDisponible);
				criterio.setEstadoTransitado(null);
				criterio.setTitulo("%"+titulo.toLowerCase()+"%");
				
				transiciones = getTransicionDao().buscarODEsPorEstadoTituloDesdeHasta(TransicionDao.TRANSFORM_TRANSICIONVO, criterio);
			
			//Si recibo titulo y usuario busco por todo
			} else if(idUsuario!=null && !idUsuario.isEmpty() && titulo!=null && !titulo.isEmpty()) {

				EstadoUsuarioDespublicadorTituloDesdeHastaCriteria criterio = new EstadoUsuarioDespublicadorTituloDesdeHastaCriteria();
				//criterio.setFechaDesde(DateManager.dateToCalendar(dateIni));
				//criterio.setFechaHasta(DateManager.dateToCalendar(dateFin));
				criterio.setFechaDesde(inicio);
				criterio.setFechaHasta(fin);
				criterio.setEstadoActual(estadoNoDisponible);
				criterio.setEstadoTransitado(null);
				criterio.setIdUsuario(idUsuario);
				criterio.setTitulo("%"+titulo.toLowerCase()+"%");
				
				transiciones = getTransicionDao().buscarODEsPorEstadoUsuarioDespublicadorTituloDesdeHasta(TransicionDao.TRANSFORM_TRANSICIONVO, criterio);
				
			//Si no recibo ni titulo ni usuario busco solo despublicados por fecha
			} else if( (idUsuario==null || idUsuario.isEmpty()) && (titulo==null || titulo.isEmpty()) ) {

				EstadoDesdeHastaCriteria criterio = new EstadoDesdeHastaCriteria();
				criterio.setFechaDesde(inicio);
				criterio.setFechaHasta(fin);
				criterio.setEstadoActual(estadoNoDisponible);
				criterio.setEstadoTransitado(null);
				
				transiciones = getTransicionDao().buscarODEsPorCriterioEstadoDesdeHasta(TransicionDao.TRANSFORM_TRANSICIONVO, criterio);
			}
			
			if(transiciones!=null) {
				logger.debug("Encontradas "+transiciones.size()+" transiciones");
				return transiciones.toArray(new TransicionVO[transiciones.size()]);
			} else {
				logger.debug("Encontradas null transiciones");
				return null;
			}
			
		} catch (Exception e) {
			String msg = "Error obteniendo la lista de ODEs Despublicados con titulo [" + titulo + "], usuario [" + idUsuario + "], desde ["
					+ fechaInicio.toString() + "], hasta [" + fechaFin.toString() + "][" + e.getCause() + "]";
			logger.error(msg);
			throw new Exception(msg, e);
		}
	}
	

	@Override
	protected TransicionVO[] handleObtenODEsDespublicadosPorTituloUsuarioDespublicadorUsuariosCreadorFecha(
			String idUsuarioDespublicador, String fechaInicio, String fechaFin, String titulo,
			String[] idsUsuariosCreador) throws Exception {
		
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
		Date dateIni = null;
		Date dateFin = null;
		
		// Comprobamos y ajustamos las fechas
		if (fechaInicio==null || fechaInicio.isEmpty())
			dateIni = new Date(0); // si la fecha de inicio es vacia, cogemos 1970 como inicio.
		else 
			dateIni = formatoDelTexto.parse(fechaInicio);
		
		if (fechaFin==null || fechaFin.isEmpty())
			dateFin = new Date(); // si la fecha de fin es vacia, utilzamos hoy como tope.
		else
			dateFin = formatoDelTexto.parse(fechaFin);
			
		if (dateIni.after(dateFin)) // hay una inconsistencia con las fechas, no seguimos
		{
			String msg = "Error invocando la eliminacion de odes. Fechas desde[" + fechaInicio.toString()
			+ "] y hasta[" + fechaFin.toString() + "] inconsistentes.";
			logger.warn(msg);
			throw new Exception(msg);
		}
		
		//Afinamos las fechas
        Calendar inicio = Calendar.getInstance();
        inicio.setTime(dateIni);
        inicio.set(Calendar.AM_PM, 0);
        inicio.set(Calendar.HOUR, 0);
        inicio.set(Calendar.MINUTE, 0);
        inicio.set(Calendar.SECOND, 0);
        
        Calendar fin = Calendar.getInstance();
        fin.setTime(dateFin);
        fin.set(Calendar.AM_PM, 1);
        fin.set(Calendar.HOUR, 11);
        fin.set(Calendar.MINUTE, 59);
        fin.set(Calendar.SECOND, 59);
		
		try {
			List<TransicionVO> transiciones = null;
			Estado estadoNoDisponible = getEstadoDao().obtenEstadoPorNombre(NO_DISPONIBLE);

			String msg = "Obteniendo la lista de ODEs Despublicados con titulo [" + titulo + "], usuario [" + idUsuarioDespublicador + "], desde ["
					+ fechaInicio.toString() + "], hasta [" + fechaFin.toString() + "]";
			
			logger.debug(msg);
			
			//Si solo recibo el usuario busco por fecha y usuario
			if(idUsuarioDespublicador!=null && !idUsuarioDespublicador.isEmpty() && (titulo==null || titulo.isEmpty()) ) {
				
				EstadoUsuariosCreacionUsuarioDespublicadorDesdeHastaCriteria criterio = new EstadoUsuariosCreacionUsuarioDespublicadorDesdeHastaCriteria();
				criterio.setFechaDesde(inicio);
				criterio.setFechaHasta(fin);
				criterio.setEstadoActual(estadoNoDisponible);
				criterio.setEstadoTransitado(null);
				criterio.setIdsUsuariosCreacion(idsUsuariosCreador);
				criterio.setIdUsuarioDespublicador(idUsuarioDespublicador);
				
				transiciones = getTransicionDao().buscarODEsPorEstadoUsuariosCreacionUsuarioDespublicadorDesdeHasta(TransicionDao.TRANSFORM_TRANSICIONVO, criterio);
			
			//Si solo recibo el titulo busco por titulo y fecha
			} else if(titulo!=null && !titulo.isEmpty() && (idUsuarioDespublicador==null || idUsuarioDespublicador.isEmpty()) ) {

				EstadoUsuariosCreacionTituloDesdeHastaCriteria criterio = new EstadoUsuariosCreacionTituloDesdeHastaCriteria();
				criterio.setFechaDesde(inicio);
				criterio.setFechaHasta(fin);
				criterio.setEstadoActual(estadoNoDisponible);
				criterio.setEstadoTransitado(null);
				criterio.setIdsUsuariosCreacion(idsUsuariosCreador);
				criterio.setTitulo("%"+titulo.toLowerCase()+"%");
				
				transiciones = getTransicionDao().buscarODEsPorEstadoUsuariosCreacionTituloDesdeHasta(TransicionDao.TRANSFORM_TRANSICIONVO, criterio);
			
			//Si recibo titulo y usuario busco por todo
			} else if(idUsuarioDespublicador!=null && !idUsuarioDespublicador.isEmpty() && titulo!=null && !titulo.isEmpty()) {

				EstadoUsuariosCreacionUsuarioDespublicadorTituloDesdeHastaCriteria criterio = new EstadoUsuariosCreacionUsuarioDespublicadorTituloDesdeHastaCriteria();
				criterio.setFechaDesde(inicio);
				criterio.setFechaHasta(fin);
				criterio.setEstadoActual(estadoNoDisponible);
				criterio.setEstadoTransitado(null);
				criterio.setIdsUsuariosCreacion(idsUsuariosCreador);
				criterio.setIdUsuarioDespublicador(idUsuarioDespublicador);
				criterio.setTitulo("%"+titulo.toLowerCase()+"%");
				
				transiciones = getTransicionDao().buscarODEsPorEstadoUsuariosCreacionUsuarioDespublicadorTituloDesdeHasta(TransicionDao.TRANSFORM_TRANSICIONVO, criterio);
				
			//Si no recibo ni titulo ni usuario busco solo despublicados por fecha
			} else if( (idUsuarioDespublicador==null || idUsuarioDespublicador.isEmpty()) && (titulo==null || titulo.isEmpty()) ) {

				EstadoUsuariosCreacionDesdeHastaCriteria criterio = new EstadoUsuariosCreacionDesdeHastaCriteria();
				criterio.setFechaDesde(inicio);
				criterio.setFechaHasta(fin);
				criterio.setEstadoActual(estadoNoDisponible);
				criterio.setEstadoTransitado(null);
				criterio.setIdsUsuariosCreacion(idsUsuariosCreador);
				
				transiciones = getTransicionDao().buscarODEsPorEstadoUsuariosCreacionDesdeHasta(TransicionDao.TRANSFORM_TRANSICIONVO, criterio);
			}
			
			if(transiciones!=null) {
				logger.debug("Encontradas "+transiciones.size()+" transiciones");
				return transiciones.toArray(new TransicionVO[transiciones.size()]);
			} else {
				logger.debug("Encontradas null transiciones");
				return null;
			}
			
		} catch (Exception e) {
			String msg = "Error obteniendo la lista de ODEs Despublicados con titulo [" + titulo + "], usuario [" + idUsuarioDespublicador + "], desde ["
					+ fechaInicio.toString() + "], hasta [" + fechaFin.toString() + "][" + e.getCause() + "]";
			logger.error(msg);
			throw new Exception(msg, e);
		}
	}
}