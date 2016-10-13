// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.publicacion.negocio.servicios;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.activation.DataHandler;

import org.apache.log4j.Logger;

import es.pode.entregar.negocio.servicios.PaquetePifVO;
import es.pode.entregar.negocio.servicios.TipoPifVO;
import es.pode.localizador.negocio.servicios.LocalizadorVO;
import es.pode.soporte.seguridad.encriptacion.Autenticar;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.utiles.ficheros.UtilesFicheros;


/**
 * @see es.pode.publicacion.negocio.servicios.SrvSyncService
 */

public class SrvSyncServiceImpl
    extends es.pode.publicacion.negocio.servicios.SrvSyncServiceBase
{
	public static final String FILE_NAME_OFFLINE = "/sync.properties";
	private static Properties props = null;
	private static Logger logger = Logger.getLogger(SrvSyncServiceImpl.class);
	private final static String ROLE_DOCENTE = "ROLE_DOCENTE";
	private final static String ROLE_ADMINISTRADOR = "ROLE_ADMINISTRADOR";
	private final static String ROLE_CATALOGADOR = "ROLE_CATALOGADOR";
	private final static String ROLE_PUBLICADOR = "ROLE_PUBLICADOR";
	
	@Override
	protected String[] handleDescargarODEs(ODESyncVO[] odes, String clave,
			String usuario) throws Exception {
		String[] urls=null;
		Autenticar.cargarContextoSeguridad(usuario);
		
		if (odes!=null&&odes.length>0) {
			urls = new String[odes.length];
			for (int i = 0; i < odes.length; i++) {
				TipoPifVO tipo = new TipoPifVO(odes[i].getIdODE(),"SCORM_2004",LdapUserDetailsUtils.getIdioma());
				PaquetePifVO paquete=getSrvEntregarService().generarPaquetePIFTipoPIF(tipo);
				urls[i]=paquete.getHref();
			}
		}
		return urls;
	}

	@Override
	protected ODESyncVO[] handleObtenerODEs(String usuario, String clave)
			throws Exception {
		ODESyncVO[] odes = null;
//		logger.debug("Intento de logar usuario: "+usuario+" con clave: "+clave);
//		try {
//			logarUsuario(usuario, clave);
//		} catch (Exception e) {
//		}	
//		logger.debug("Usuario logado correctamente");
		TransicionVO[] transiciones=getSrvPublicacionService().obtenODEsCreadosPorUsuario(usuario);
		if(transiciones!=null&&transiciones.length>0) {
			logger.debug("Recibidas "+transiciones.length+" transacciones.");
			odes=new ODESyncVO[transiciones.length];
			for (int i = 0; i < transiciones.length; i++) {
				//De momento no usamos el campo md5, pero lo dejamos en el VO para no romper la interfaz en el futuro
				odes[i]=new ODESyncVO(transiciones[i].getTitulo(),"",transiciones[i].getIdODE());
//				logger.debug("Transición "+i+" mapeada.");
			}
		}
		return odes;
	}

//	private void logarUsuario(String usuario, String clave) throws Exception {
////		Locale locale=new Locale(LdapUserDetailsUtils.getIdioma());		
////		//Comprobamos si existe el usuario
////		logger.debug("Comprobamos si existe el usuario con rol docente.");
////		String[] roles=new String[] {"ROLE_DOCENTE"};
////		Integer resultadoAutenticacion = Autenticar.validarUsuarioRoles(usuario, clave, roles);
////		logger.debug("El resultado de la autenticación ha sido "+resultadoAutenticacion);
////		if(!resultadoAutenticacion.equals(0)) {
////			ResourceBundle i18n = ResourceBundle.getBundle(FILE_NAME_OFFLINE,locale);
////			logger.error("Estamos en el servicio lanzando el error de autenticacion"+i18n.getString("seguridad."+resultadoAutenticacion.toString()));
////			throw new Exception(i18n.getString("seguridad."+resultadoAutenticacion.toString()));
////		}
////		//Usuario existe y tiene el rol adecuado, luego autenticamos
////		logger.debug("Usuario existe y tiene rol adecuado");
////		Autenticar.anadirSeguridad(usuario,clave);
//		Autenticar.cargarContextoSeguridad(usuario);
//		logger.debug("Creado contexto de seguridad");
//	}

	@Override
	protected ResultadoOperacionVO handleSubirODE(String usuario, String clave, DataHandler pif, String titulo, String idioma, String identificador) throws Exception {
		logger.debug("Entrando en SUBIRODES");
		ResultadoOperacionVO res;
		
		boolean valido=Autenticar.validarUsuarioClaveLdap(usuario, clave);
////		Comprobamos si existe el usuario
//		String[] roles=new String[1];
//		roles[0]=ROLE_DOCENTE;
//		logger.debug("Entramos en añadir seguridad con los roles necesarios para docente" +roles.length);
//		Integer vueltaValidacion=Autenticar.validarUsuarioRoles(usuario,clave,roles);
//		boolean valido=true;
//		if (!vueltaValidacion.equals(1) && vueltaValidacion.equals(2))
//		{
//			String[] rolesAdmin=new String[1];
//			rolesAdmin[0]=ROLE_ADMINISTRADOR;
//			logger.debug("Entramos en añadir seguridad con los roles necesarios para administrador puesto que la validación con docente ha sido" +vueltaValidacion);
//			Integer vueltaValidacionAdministrador=Autenticar.validarUsuarioRoles(usuario,clave,rolesAdmin);
//			if (vueltaValidacionAdministrador.equals(2))//No nos vale que sea DOCENTE y ADMINISTRADOR, es docente O administrador
//			{
//				
//				valido=false;
//				logger.debug("Error "+ vueltaValidacionAdministrador+" al hacer la validación de administrador");
//				
//			}
//		}else if(vueltaValidacion.equals(1)){
//			
//			logger.debug("Error "+ vueltaValidacion+" al hacer la validación de docente");
//			valido=false;
//			
//		}
		
		
		if (valido) {
			boolean esNuevo = true;
			TransicionVO[] transiciones = getSrvPublicacionService()
					.obtenODEsCreadosPorUsuario(usuario);
			logger.debug("En subirODE de ResultadoOperacionVO tenemos "
					+ transiciones.length + " transiciones");
			for (int i = 0; i < transiciones.length; i++) {

				logger.debug("Vamos a comparar el ide: "
						+ transiciones[i].getIdODE()
						+ " del ode subido al ide: " + identificador
						+ " del que queremos subir");
				if (transiciones[i].getIdODE().equals(identificador)) {
					esNuevo = false;
				}
			}
			logger.debug("Es nuevo?" + esNuevo);
			try {
				Autenticar.cargarContextoSeguridad(usuario);
			} catch (Exception e) {
				logger.error("FALLO AL CARGAR CONTEXTO SEGURIDAD:" + e);
			}
			logger.debug("Se creo contexto de seguridad");
			if (esNuevo) {
				//ODE nuevo
				res = this.getSrvPublicacionService().crearPifConCuotaID(pif,
						usuario, "Sincronizado desde herramienta Offline",
						titulo, idioma, identificador);

				logger.debug("Subido ODE " + identificador + " con ide="
						+ res.getIdODE());
				
				// vamos a probar la actualización forzada+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				//ODE ya existente
				logger.debug("Preguntamos por localizador");
				LocalizadorVO localizador = getSrvLocalizadorService()
						.consultaLocalizador(identificador);
				logger
						.debug("Localizador es "
								+ localizador.getIdentificador());
				//Generamos backup
				String path = localizador.getPath();
				logger.debug("ODE en ruta " + path);
				String carpetaTemporal = getPropertyValue("carpeta.backups");
				//			String carpetaTemporal= "/../backup";
				logger.debug("Carpeta temporal es " + carpetaTemporal);

				File temp = new File(path + "/" + carpetaTemporal);

				if (!temp.exists()) {
					logger
							.debug("No existe la carpeta backup y la voy a crear");
					temp.mkdir();

				}
				logger.debug("existe la carpeta temp ruta;" + temp.getPath());
				// 20081021: Cambiamos backup en ZIP por backup descomprimido. El uso de 
				// truezip produce problemas de rendimiento
				String zipName = path + "/" + carpetaTemporal + "/"
						+ localizador.getIdentificador();
				File zipFile = new File(zipName);
				if (zipFile.exists()) {
					logger.debug("Borrando el viejo backup");
					UtilesFicheros.eliminar(zipFile);
				}
				logger.debug("voy a copiar el fichero: " + path + " en: "
						+ zipFile.getPath());
				if (!zipFile.exists())
					zipFile.mkdirs();
				UtilesFicheros.copiar(new File(path), zipFile);
				//Backup generado
				//Copiamos pif a temporal
				File ficheroZip = new File(temp + "/temp.zip");
				ficheroZip.createNewFile();
				ficheroZip.deleteOnExit();
				FileOutputStream fos = new FileOutputStream(ficheroZip);
				pif.writeTo(fos);
				fos.close();
				//Descomprimimos
				getZipDao().descomprimir(ficheroZip.getPath(), path);
				logger.debug("Fichero descomprimido");

				//Actualizamos transiciones para que se actualicen datos en carpeta personal
				res = getSrvPublicacionService().modificaODECreado(
						identificador, usuario, titulo, null);
				//hasta aquí llega la actualización forzada+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			} else {
				//ODE ya existente
				logger.debug("Preguntamos por localizador");
				LocalizadorVO localizador = getSrvLocalizadorService()
						.consultaLocalizador(identificador);
				logger
						.debug("Localizador es "
								+ localizador.getIdentificador());
				//Generamos backup
				String path = localizador.getPath();
				logger.debug("ODE en ruta " + path);
				String carpetaTemporal = getPropertyValue("carpeta.backups");
				//			String carpetaTemporal= "/../backup";
				logger.debug("Carpeta temporal es " + carpetaTemporal);

				File temp = new File(path + "/" + carpetaTemporal);

				if (!temp.exists()) {
					logger
							.debug("No existe la carpeta backup y la voy a crear");
					temp.mkdir();

				}
				logger.debug("existe la carpeta temp ruta;" + temp.getPath());
				// 20081021: Cambiamos backup en ZIP por backup descomprimido. El uso de 
				// truezip produce problemas de rendimiento
				String zipName = path + "/" + carpetaTemporal + "/"
						+ localizador.getIdentificador();
				File zipFile = new File(zipName);
				if (zipFile.exists()) {
					logger.debug("Borrando el viejo backup");
					UtilesFicheros.eliminar(zipFile);
				}
				logger.debug("voy a copiar el fichero: " + path + " en: "
						+ zipFile.getPath());
				if (!zipFile.exists())
					zipFile.mkdirs();
				UtilesFicheros.copiar(new File(path), zipFile);
				//Backup generado
				//Copiamos pif a temporal
				File ficheroZip = new File(temp + "/temp.zip");
				ficheroZip.createNewFile();
				ficheroZip.deleteOnExit();
				FileOutputStream fos = new FileOutputStream(ficheroZip);
				pif.writeTo(fos);
				fos.close();
				//Descomprimimos
				getZipDao().descomprimir(ficheroZip.getPath(), path);
				logger.debug("Fichero descomprimido");

				//Actualizamos transiciones para que se actualicen datos en carpeta personal
				res = getSrvPublicacionService().modificaODECreado(
						identificador, usuario, titulo, null);

				//			res= new ResultadoOperacionVO("0.0","",identificador,getSrvLocalizadorService().actualizaEspacioLocalizador(identificador));
			}
		} else {
			res = new ResultadoOperacionVO();
			res.setIdResultado("FALLO EN AUTENTICACION");
		}
		return res;
	}

	/** obtener el property* */
	private String getPropertyValue(String sKey) {
		String sReturn = "";
		InputStream fIsSpringProperties = null;
		try {
			if (props == null) {
				fIsSpringProperties = SrvSyncServiceImpl.class.getResourceAsStream(FILE_NAME_OFFLINE);
				Properties properties = new java.util.Properties();
				properties.load(fIsSpringProperties);
				props=properties;
			}
			sReturn = props.getProperty(sKey);
			logger.debug("propiedad obtenida: " + sReturn.toString());
		} catch (IOException e) {
			logger.error(e);
		} finally {
			try {
				if (fIsSpringProperties!=null) {
					fIsSpringProperties.close();
				}
			} catch (IOException e) {
				logger.error("No se pudo cerrar stream");
			}
		}
		// devolvemos la propiedad
		return sReturn;
	}
}