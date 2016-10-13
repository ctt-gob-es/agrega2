/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
package es.pode.adminusuarios.negocio.servicios;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;
import es.pode.adminusuarios.negocio.comun.LDAPContactDAO;
import es.pode.adminusuarios.negocio.comun.Person;
import es.pode.adminusuarios.negocio.dominio.Grupo;
import es.pode.adminusuarios.negocio.dominio.GrupoDao;
import es.pode.adminusuarios.negocio.dominio.GrupoTrabajo;
import es.pode.adminusuarios.negocio.dominio.GrupoTrabajoDao;
import es.pode.adminusuarios.negocio.dominio.Rol;
import es.pode.adminusuarios.negocio.dominio.RolDao;
import es.pode.adminusuarios.negocio.dominio.Usuario;
import es.pode.adminusuarios.negocio.dominio.UsuarioDao;
import es.pode.configuracionPlataforma.servicios.SrvPropiedadService;
import es.pode.gestorCorreo.negocio.servicios.CorreoUsuarioVO;
import es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO;
import es.pode.gestorCorreo.negocio.servicios.SrvCorreo;
import es.pode.soporte.i18n.I18n;
import es.pode.soporte.url.ImagenesAgrega;
import es.pode.soporte.utiles.base64.Base64Coder;

/**
 * @see es.pode.adminusuarios.negocio.servicios.SrvAdminUsuariosService
 */

public class SrvAdminUsuariosServiceImpl extends es.pode.adminusuarios.negocio.servicios.SrvAdminUsuariosServiceBase
{
	
	private SrvPropiedadService prop = null;
	private static Logger log = Logger.getLogger(SrvAdminUsuariosServiceImpl.class);

	private java.util.Properties pAdminUsuariosProperties = null;
	private java.util.Properties pAgregaProperties = null;
	private static String caracteres = "aábcdeéfghiíjklmnñoópqrstuúvwxyzAÁBCDEÉFGHIÍJKLMNÑOÓPQRSTUÚVWXYZ1234567890.,;:{}[]";
	
	private static String SERVER_ID_INTEF ="es_cnice_20080623";
	
	private static String PASSWORD_BAJA_ESPECIAL ="{SHA}4U4x79ttbdhiEcjO/j29LWkJu/c=";
	
	/* Codigos de operacion web semantica */
	private static String OK_WS 							="0";
	private static String ERROR_GENERICO_WS 				="1";
	private static String ERROR_PARAMETROS_WS 				="2";
	private static String ERROR_USUARIO_NO_ENCONTRADO_WS 	="3";
	private static String ERROR_USUARIO_NO_ACTIVO_WS	 	="4";
	private static String ERROR_USUARIO_YA_EXISTENTE_WS 	="5";
	private static String ERROR_ALTA_WS						="6";
	private static String ERROR_ALTA_EN_AGREGA_WS			="7";
	private static String ERROR_CAMPO_INCORRECTO_WS			="8";
	private static String ERROR_VALOR_YA_UTILIZADO_WS		="9";

	private static String ERROR_CALCULO_PASS_WS				="15";
	private static String ERROR_ACCESO_LDAP_WS				="16";

	
	private static String ERROR_LISTAR_GRUPOS_ALTA_WS		="10";
	private static String ERROR_LISTAR_ROLES_ALTA_WS		="11";
	private static String ERROR_LISTAR_GRUPOS_TRABAJO_ALTA_WS="12";
	private static String ERROR_OBTENER_ROL_DOCENTE_WS		="13";
	private static String ERROR_COMPROBAR_USUARIO_TRABAJO_ALTA_WS="14";
	private static String ERROR_OBTENER_DATOS_USUARIO_WS	="17";
	private static String ERROR_BAJA_USUARIO_AGREGA_WS 		="18";;	
	private static String ERROR_MODIFICAR_USUARIO_AGREGA_WS	="21";
	
	private static String ERROR_GENERICO_ALTA_WS			="30";
	private static String ERROR_GENERICO_BAJA_USUARIO_WS	="31";	
	private static String ERROR_GENERICO_MODIFICAR_USUARIO_WS="32";
	private static String ERROR_GENERICO_AUTENTIFICAR_USUARIO_WS="33";	
	
	private static String CAMPO_DOCUMENTO_IDENTIDAD	="documentoIdentidad";
	private static String CAMPO_EMAIL				="email";
	private static String CAMPO_IDENTIFICADOR 		="identificador";
	
	static char[] carr = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	/**
	 * Modifica los datos de un grupo
	 * @param grupo GrupoVO grupo que se quiere modificar
	 * @throws Exception
	 */
	protected void handleModificarGrupo(es.pode.adminusuarios.negocio.servicios.GrupoVO grupo) throws java.lang.Exception
	{
		try
		{

			Long id = grupo.getId();
			Grupo grupoEntity = this.getGrupoDao().load(id);
			this.getGrupoDao().fromGrupoVO(grupo, grupoEntity);
			this.getGrupoDao().update(grupoEntity);

		} catch (Exception e)
		{
			log.error("Se ha producido la siguiente excepcion " + e);
			ModificacionGrupoException exception = new ModificacionGrupoException("No se ha podido modificar el grupo", e);
			throw exception;
		}
	}

	/**
	 * Elimina de BD un conjunto de grupos
	 * 
	 * @param ids array con los identificadores de los grupos que se
	 * quieren eliminar
	 * @return ValidaBajaVO VO que contiene el numero de grupos que se han
	 * eliminado y una descripcion de la operacion
	 * @throws Exception
	 */
	protected ValidaBajaGrupoVO handleBajaGrupo(java.lang.Long[] ids) throws java.lang.Exception
	{

		ValidaBajaGrupoVO validaBajaVO = new ValidaBajaGrupoVO();
		int grupoDeleted = 0;
		List itemsDeleted = new ArrayList();
		
		try
		{
			grupoDeleted = 0;
			for (int j = 0; j < ids.length; j++)
			{
				Long identificador = ids[j];
				if (!(this.obtenerUsuariosGrupo(identificador)).booleanValue())
				{
					Grupo grupo = (this.getGrupoDao()).load(identificador);
					Grupo grupoAux = grupo;
					this.getGrupoDao().remove(grupo);
					grupoDeleted = grupoDeleted + 1;
					itemsDeleted.add(grupoAux.getDescripcion());
				}
			}
			if(log.isDebugEnabled())log.debug("despues de borrar todos los grupos");
			////validaBajaVO.setItemsDeleted((GrupoVO[]) itemsDeleted.toArray(new GrupoVO[0]));
			validaBajaVO.setItemsDeleted((String[]) itemsDeleted.toArray(new String[0]));
			if(log.isDebugEnabled())log.debug("despues de validaBajaVO.setItemsDeleted");
			if (grupoDeleted == ids.length)
			{
				validaBajaVO.setDescripcion("ok.borrarGrupo");
			} else
			{
				validaBajaVO.setDescripcion("errors.borrarGrupo.algunosGrupos");
			}

		} catch (Exception e)
		{
			log.error("Se ha producido la siguiente excepcion " + e);
			BajaGrupoException exception = new BajaGrupoException("Error al eliminar el grupo ", e);

			validaBajaVO.setDescripcion("errors.borrarGrupo");
			throw exception;
		}

		// }

		return validaBajaVO;
	}

	/**
	 * Obtiene una lista con todos los grupos registrados en BD
	 * @return GrupoVO[] array de gruposVO
	 * @throws Exception
	 */
	protected es.pode.adminusuarios.negocio.servicios.GrupoVO[] handleListarGrupos() throws java.lang.Exception
	{
		GrupoVO[] grupoV = null;

		try
		{
			// Collection grupos =
			// this.getGrupoDao().loadAll(GrupoDao.TRANSFORM_GRUPOVO);
			Collection grupos = this.getGrupoDao().getUsuarios();
			this.getGrupoDao().toGrupoVOCollection(grupos);
			if(log.isDebugEnabled())log.debug("los grupos obtenidos son " + grupos.size());
			grupoV = (GrupoVO[]) grupos.toArray(new GrupoVO[0]);

		} catch (Exception e)
		{
			log.error("Error: " + e);
			throw e;
		}
		return grupoV;
	}

	/**
	 * Obtiene un GrupoVO a partir de un identificador
	 * @param id
	 * @return GrupoVO
	 * @throws Exception
	 */
	protected es.pode.adminusuarios.negocio.servicios.GrupoVO handleDescripcionGrupo(java.lang.Long id) throws java.lang.Exception
	{
		GrupoVO grupoVO = null;
		grupoVO = (GrupoVO) this.getGrupoDao().load(GrupoDao.TRANSFORM_GRUPOVO, id);
		return grupoVO;
	}

	/**
	 * Genera una nueva clave y se la envía por correo al usuario junto con
	 * su usuario (login). Puesto que la actualizacion de la clave se hace
	 * automáticamente (sin intermediación de un administrador) el from del
	 * correo que se enviará será del primer administrador registrado en el
	 * sistema, o también se podría poner una cuenta genérica
	 * @param emailNIF UsuarioVO
	 * @return resultado si ha ido bien(true) o mal(false)
	 * @throws Exception
	 */
	protected java.lang.Boolean handleNuevaClave(es.pode.adminusuarios.negocio.servicios.UsuarioVO emailNIF) throws java.lang.Exception
	{
		Boolean resultado = Boolean.FALSE;
		String nuevaClave = null;
		String claveOld = emailNIF.getClave();
		CorreoUsuarioVO correoUsuarioVO= new CorreoUsuarioVO();
		SrvCorreo correoService=  this.getSrvCorreo();
		ResultadoEnvioCorreoVO correoVO2 = new ResultadoEnvioCorreoVO();
		String setTo[]=new String [1];
		try
		{

			nuevaClave = getPassword();
			log.info("Recuerdo de contrasenia ");
			//Enviamos el correo al usuario y al administrador del ldap externo en el caso de que lo hubiera.
			//en el caso de que el correo se haya enviado correctamente se modificará la clave en el ldap
			
			//Envio del correo al usuario
			setTo[0]=emailNIF.getEmail();
			correoUsuarioVO.setTo(setTo);
			correoUsuarioVO.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
			correoUsuarioVO.setHrefLogo(ImagenesAgrega.urlImagenLogoAgrega());
			correoUsuarioVO.setUrlImagenLogo(ImagenesAgrega.urlImagenLogoAgrega());
			correoUsuarioVO.setEmailUsuario(emailNIF.getEmail());
			correoUsuarioVO.setLdapExterno(false);
			correoUsuarioVO.setNodoTaller(false);
			correoUsuarioVO.setNuevaClave(nuevaClave);
			correoUsuarioVO.setIdiomaCorreo(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
			correoUsuarioVO.setUsuario(emailNIF.getUsuario());
			correoVO2=correoService.nuevaClave(correoUsuarioVO);
			if(log.isDebugEnabled())log.debug("El resultado del envio del correo de nueva clave:");
			if(correoVO2.getResultado().equalsIgnoreCase("OK"))
			{
				Long id = emailNIF.getId();
				Usuario usuarioEntity = this.getUsuarioDao().load(id);
				this.getBeanMapper().map(emailNIF, usuarioEntity, "MAPEO_USUARIOVO_USUARIO");
				this.getUsuarioDao().update(usuarioEntity);
				if(!(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.LDAPEXTERNO)).equalsIgnoreCase("true"))
				{
					this.getLdapHandler().modifyUser(emailNIF.getUsuario(), this.getHashPassword(nuevaClave), emailNIF.getNombre() + " " + emailNIF.getApellido1() + " " + emailNIF.getApellido2());
					if(log.isDebugEnabled())log.debug("Actualizamos la entrada de ldap");
				}else
				{
					log.info("Se envia un correo al administrador del ldap para que modifique la clave del usuario");
					correoUsuarioVO = new CorreoUsuarioVO();
					setTo[0]= AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ADMINLDAPEXTERNO);
					correoUsuarioVO.setTo(setTo);
					correoUsuarioVO.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
					correoUsuarioVO.setHrefLogo(ImagenesAgrega.urlImagenLogoAgrega());
					correoUsuarioVO.setUrlImagenLogo(ImagenesAgrega.urlImagenLogoAgrega());
					correoUsuarioVO.setEmailUsuario(emailNIF.getEmail());
					correoUsuarioVO.setLdapExterno(true);
					correoUsuarioVO.setNodoTaller(false);
					correoUsuarioVO.setNuevaClave(nuevaClave);
					correoUsuarioVO.setIdiomaCorreo(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
					correoUsuarioVO.setUsuario(emailNIF.getUsuario());
					correoVO2=correoService.nuevaClave(correoUsuarioVO);
					log.info("Resultado del envio del correo al administrador del ldap "+correoVO2.getResultado());
				}
				resultado = Boolean.TRUE;
				
			}else
			{
				log.info("No se ha podido enviar el correo al nuevo usuario con la nueva clave no se hace nada");
				resultado = Boolean.FALSE;
			}
			
		
			

		} catch (org.springframework.ldap.UncategorizedLdapException exception)
		{
			log.error("Se produce un error en ldap al actualizar la clave del usuario: UncategorizedLdapException ");

			emailNIF.setClave(claveOld);
			this.modificarUserTransaccion(emailNIF);

			if(log.isDebugEnabled())log.debug("Rollback en la BD al fallar la insercion en ldap");
			resultado = Boolean.FALSE;

		} catch (org.springframework.ldap.EntryNotFoundException entryNotFound)
		{
			log.error("Se produce un error en ldap al actualizar la clave del usuario :EntryNotFoundException ");

			emailNIF.setClave(claveOld);
			this.modificarUserTransaccion(emailNIF);

			if(log.isDebugEnabled())log.debug("Rollback en la BD al fallar la insercion en ldap");
			resultado = Boolean.FALSE;
		} catch (org.springframework.dao.DataRetrievalFailureException comunicationException)
		{
			log.error("Se produce un error en ldap al actualizar la clave del usuario : DataRetrievalFailureException ");

			emailNIF.setClave(claveOld);
			this.modificarUserTransaccion(emailNIF);
			if(log.isDebugEnabled())log.debug("Rollback en la BD al fallar la insercion en ldap");
			resultado = Boolean.FALSE;
		} catch (Exception e)
		{
			log.error("Exception al generar una nueva clave ", e);
			resultado = Boolean.FALSE;
		}
		return resultado;
	}

	
	private String modificarUsuarioAgrega(es.pode.adminusuarios.negocio.servicios.UsuarioVO usuario) throws java.lang.Exception
	{
		String resultado = "";
		 //Comprobamos primero que el usuario que se pasa como parámetro existe en la BD
		if(!this.estaActivo(usuario.getUsuario()))
		{
			log.error("El usuario no se encuentra en la plataforma");
			throw new Exception("El usuario que se desea modificar no se encuentra en la plataforma");
		}
		try
		{
			Usuario usuarioEntity = this.getUsuarioDao().load(usuario.getId());
		
			boolean emailLdapExt = (!(usuario.getClave() == null) && (!(usuario.getClave().equalsIgnoreCase(""))));
			String claveNueva = usuario.getClave();
			usuario.setClave(null);
			
			//En el caso de que el usuario tenga perfil público dozer no realiza el mapeo completo de todos los VO que forman el usuarioPublico
			//por lo que a la hora de modificar se esta intentando insertar en BD un Entity en el que aparecen VO.
			//Chequeamos si el UsuarioVO tiene UsuarioPublicoVO
			
			getBeanMapper().map(usuario, usuarioEntity, "DEF_MAPPING_USUARIO_USUARIOVO");
			log.debug("despues del mapeo específico de UsuarioVO a Usuario");
			
			this.getUsuarioDao().update(usuarioEntity);
			if(log.isDebugEnabled())log.debug("modificamos la entrada en el servidor ldap");
			
			if ((claveNueva == null) || (claveNueva.equalsIgnoreCase("")))
			{
				if(log.isDebugEnabled())log.debug("No se modifica la clave en ldap");

			} else
			{
//				if(ChequeoClave.chequea(claveNueva)!=0) return "FALLO.MODIFICARUSUARIO";
				
				if (ObtieneSrvPropiedad().get (AgregaProperties.CHECK_PASSWORD).equalsIgnoreCase("true")) {
					//Chequeamos la clave
					if (ChequeoClave.chequea(claveNueva) != 0)
						return "FALLO.MODIFICARUSUARIO";
				}
					
				if (!((AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.LDAPEXTERNO)).equalsIgnoreCase("true")))
				{
					if(log.isDebugEnabled())log.debug("Se modifica la entrada de ldap");
					this.getLdapHandler().modifyUser(usuario.getUsuario(), this.getHashPassword(claveNueva), usuario.getNombre() + " " + usuario.getApellido1() + " " + usuario.getApellido2());
				}
			}
			resultado = "OK.MODIFICARUSUARIO";
			
		} catch (org.springframework.dao.DataRetrievalFailureException comunicationException)
		{
			log.error("Se produce una excepcion en ldap damos marcha atras : DataRetrievalFailureException");

//			this.modificarUserTransaccion(usuarioVoOld);
			resultado = "FALLO.MODIFICARUSUARIO";
		} catch (org.springframework.ldap.UncategorizedLdapException exception)
		{
			log.error("Se produce una excepcion en ldap damos marcha atras : UncategorizedLdapException");
//			this.modificarUserTransaccion(usuarioVoOld);
			resultado = "FALLO.MODIFICARUSUARIO";

		} catch (Exception e)
		{
			log.error("Se ha producido la siguiente excepcion " + e);
			resultado = "FALLO.MODIFICARUSUARIO";
		}
		return resultado;
	}
	
	
	/**
	 * Modifica en BD y en Ldap los datos del usuario
	 * @param usuario 
	 * UsuarioVO que contiene los datos modificados del usuario
	 * @return resultado indica si la modificacion ha ido bien o no
	 * @throws Exception
	 */
	protected String handleModificarUsuario(es.pode.adminusuarios.negocio.servicios.UsuarioVO usuario) throws java.lang.Exception
	{
		Person datosLdapUsuario=null;
		boolean existeLdapWebSemantica=ldapWebSemanticaActiva();
		String ldapExterno=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.LDAPEXTERNO);
		
		// Pasamos a mayúsculas para que todos los docmentos de identidad se inserten en mayúsculas en bd
	    if (usuario.getNIF()!=null)
	    	usuario.setNIF(usuario.getNIF().toUpperCase());

	    // Pasamos a minúsculas para que todos los correos se inserten en minúsculas en bd
	    if (usuario.getEmail()!=null)
	    	usuario.setEmail(usuario.getEmail().toLowerCase());
	    	
	    //Modificamos los datos del usuario en la rama LDAP de la web semantica
		if(existeLdapWebSemantica && ldapExterno.equalsIgnoreCase("false")) {	
			//Primero almacenamos los datos actuales por si hay que hacer rollback
			datosLdapUsuario=this.getLdapWebSemanticaHandler().getUserData(usuario.getUsuario());
			
			// Obtenemos la clave. 
			// Si viene en el usuarioVO se cambia, si no se deja la que tenía en ldap
			String claveUsuarioHash="";
			if (usuario.getClave()!=null && !usuario.getClave().equals(""))
				claveUsuarioHash=getHashPassword(usuario.getClave());
			else
				claveUsuarioHash=datosLdapUsuario.getHasedPasswd();			

			//Ahora modificamos
			this.getLdapWebSemanticaHandler().modifyUser(usuario.getUsuario(), claveUsuarioHash, 
					usuario.getNombre() + " " + usuario.getApellido1() + " " + usuario.getApellido2());			
		}	

		try {
			//Damos de alta al usuario en Agrega
			return modificarUsuarioAgrega(usuario);
			
		} catch (Exception e) {
			if(existeLdapWebSemantica && ldapExterno.equalsIgnoreCase("false")) {				
				//Rollback
				this.getLdapWebSemanticaHandler().modifyUser(datosLdapUsuario.getUsuario(), datosLdapUsuario.getHasedPasswd(), 
					datosLdapUsuario.getNombreApellidos());
			}
			log.error("Error al dar de alta al usuario en agrega ", e);
			throw e;
		}
	}

	
	/**
	 * Obtiene todos los roles registrados en el sistema
	 * 
	 * @return RolVO[] array de RolVO
	 * @throws Exception
	 */
	protected es.pode.adminusuarios.negocio.servicios.RolVO[] handleListarRoles() throws java.lang.Exception
	{
		RolVO[] rolV = null;

		try
		{
			
			Collection roles = this.getRolDao().getRoles();
			this.getRolDao().toRolVOCollection(roles);
			log.debug("los roles obtenidos son " + roles.size());
			rolV = (RolVO[]) roles.toArray(new RolVO[0]);

		} catch (Exception e)
		{
			log.error("Error: ", e);
			throw e;
		}

		return rolV;
	}
	
	
	private Long altaUsuarioAgrega (es.pode.adminusuarios.negocio.servicios.UsuarioVO usuario, 
			boolean ponerFechaDesactivacionVacia,
			boolean enviarCorreoUsuario) throws java.lang.Exception
	{
		Usuario usuarioCreado = null;
		Usuario usuarioEntity = null;
		Long resultado = null;
		CorreoUsuarioVO correoUsuarioVO =new CorreoUsuarioVO();
		String setTo[]=new String [1];
		ResultadoEnvioCorreoVO correoVO2= new ResultadoEnvioCorreoVO();

		// En BD no se va a almacenar la clave del usuario unicamente en ldap
		String clave = usuario.getClave();
		usuario.setClave("");
		SrvCorreo correoService=  this.getSrvCorreo();

		if (ObtieneSrvPropiedad().get (AgregaProperties.CHECK_PASSWORD).equalsIgnoreCase("true")) {
			//Chequeamos la clave
			log.debug("Comprobamos clave");
			if (ChequeoClave.chequea(clave) != 0)
				return null;
		}
		// No se almacena en BBDD la clave encriptada
		//	usuario.setClaveEncriptada(EncriptacionUtiles.encriptar(clave));
		String login = usuario.getUsuario();
		usuario.setUsuario(login.toLowerCase());
		if(log.isDebugEnabled())log.debug("Se transforma el login a minúsculas");
		
		setTo[0]=usuario.getEmail();
		correoUsuarioVO.setTo(setTo);
		correoUsuarioVO.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
		correoUsuarioVO.setHrefLogo(ImagenesAgrega.urlImagenLogoAgrega());
		correoUsuarioVO.setUrlImagenLogo(ImagenesAgrega.urlImagenLogoAgrega());
		correoUsuarioVO.setEmailUsuario(usuario.getEmail());
		correoUsuarioVO.setUsuario(usuario.getUsuario());
		correoUsuarioVO.setPassword(clave);
				
		try
		{
			//Comprobamos la cuota si es nula o vacia ponemos la de por defecto
			if((usuario.getCuota() == null)|| (usuario.getCuota() == 0)) {
				if(log.isDebugEnabled())log.debug("Se pone la cuota por defecto");
				usuario.setCuota((new Long((ObtieneSrvPropiedad().get (AgregaProperties.VALOR_CUOTA_DEFECTO)))* 1024 * 1024));
			}
			
			//Comprobamos si los grupos o usuarios son nulos, en ese caso devolvemos una excepcion
			if((usuario.getGrupos() == null)||(usuario.getGrupos().length == 0)) {
				log.error("El usuario no tiene ningún grupo asociado");
				throw new Exception("El usuario no tienen ningún grupo asociado");
			}
			
			if (this.obtenerUsuario(usuario.getNIF()) == null) {
				//Comprobamos si el login ya pertenece a otro usuario en ese caso como no coinciden el nif devolvemos un error
				if(this.existeUsuario(usuario.getUsuario())) {
					throw new Exception("El usuario ya esta registrado en la plataforma");
				}
				usuarioEntity = this.getUsuarioDao().fromUsuarioVO(usuario);
			    usuarioCreado = this.getUsuarioDao().create(usuarioEntity);
				if (AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.LDAPEXTERNO).equalsIgnoreCase("false"))
				{
					this.getLdapHandler().insertUser(usuarioCreado.getUsuario(), this.getHashPassword(clave), 
							usuarioCreado.getNombre() + " " + usuarioCreado.getApellido1() + " " + usuarioCreado.getApellido2());
				}
				if(log.isDebugEnabled())log.debug("insertamos en ldap el usuario");
				resultado = usuarioCreado.getId();

			} else {
				if(log.isDebugEnabled())log.debug("Modificamos un usuario ya registrado en el sistema");
				
				if(!(this.existeUsuario(usuario.getUsuario()))) {
					throw new Exception("El nif y el usuario ya estan registrados en la plataforma");
				}
				
				if(usuario.getId() == null) {
					if(log.isDebugEnabled())log.debug("El id es null");
					usuarioEntity = this.getUsuarioDao().load((this.obtenerUsuario(usuario.getNIF())).getId());
					usuario.setId(usuarioEntity.getId());
					
				} else {
					usuarioEntity = this.getUsuarioDao().load(usuario.getId());
				}
				
				log.debug("usuarioEntity "+usuarioEntity.getId());
				this.getBeanMapper().map(usuario, usuarioEntity, "MAPEO_USUARIOVO_USUARIO");
				//this.getUsuarioDao().fromUsuarioVO(usuario, usuarioEntity);
				
				if(ponerFechaDesactivacionVacia)
					usuarioEntity.setFechaDesactivacion(null);
				
				this.getUsuarioDao().update(usuarioEntity);
				
				if (AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.LDAPEXTERNO).equalsIgnoreCase("false")) 
				{
					this.getLdapHandler().insertUser(usuario.getUsuario(), this.getHashPassword(clave), 
							usuario.getNombre() + " " + usuario.getApellido1() + " " + usuario.getApellido2());
				}
			}
			
			log.debug("Mandamos el correo de alta");
			String idiomaSecundario = I18n.getInstance().obtenerIdiomaSecundarioPlataforma();
			int l=1;
			if(!(idiomaSecundario==null) && !idiomaSecundario.equals(""))
				l++;
			StringBuffer idiomas = null;
			
			//Si es nodo.taller
			if ("true".equals(AgregaPropertiesImpl.getInstance().getProperty("nodo.taller"))){
				idiomas=new StringBuffer();
				for(int j=0;j<l;j++){
					
					String idioma="";
					if(j==0){
						idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
					}else{
						idioma=idiomaSecundario;
					}
					idiomas.append(idioma).append(",");
					correoUsuarioVO.setNodoTaller(true);
					correoUsuarioVO.setLdapExterno(false);
				}
			} else {
				idiomas=new StringBuffer();
				for(int j=0;j<l;j++){
					String idioma="";
					if(j==0){
						idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
					}else{
						idioma=idiomaSecundario;
					}
					idiomas.append(idioma).append(",");
					correoUsuarioVO.setNodoTaller(false);
					correoUsuarioVO.setLdapExterno(false);
				}
			}
			
			if(enviarCorreoUsuario) {
				correoUsuarioVO.setEmailUsuario(usuario.getEmail());
				correoUsuarioVO.setIdiomaCorreo(idiomas.toString());
				correoVO2=correoService.altaUsuario(correoUsuarioVO);
			}
			
			//Comprobamos si el sistema tiene un ldap externo, en este
			// caso se mandara un email al administrador del ldap
			if ((AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.LDAPEXTERNO)).equalsIgnoreCase("true"))
			{
				if(log.isDebugEnabled())log.debug("Alta en un servidor ldap externo");
				correoUsuarioVO.setLdapExterno(true);
				correoUsuarioVO.setIdiomaCorreo(I18n.getInstance().obtenerIdiomaDefectoPlataforma());

				correoUsuarioVO.setEmailUsuario(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ADMINLDAPEXTERNO));
				setTo[0]=(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ADMINLDAPEXTERNO));
				correoUsuarioVO.setTo(setTo);
				correoVO2=correoService.altaUsuario(correoUsuarioVO);
			}
			
			if(usuarioCreado == null) {
				resultado = usuarioEntity.getId();
			} else {
				resultado = usuarioCreado.getId();
			}
			//}
		} catch (org.springframework.dao.DataRetrievalFailureException comunicationException)
		{
			log.error("Problema en ldap : DataRetrievalFailureException :" + comunicationException.getMessage());

			UsuarioVO usuarioCreated = this.obtenerDatosUsuario(usuario.getUsuario());
			this.eliminarUserTransaccion(usuarioCreated);

		} catch (org.springframework.ldap.UncategorizedLdapException exception)
		{
			log.error("Problema en ldap : org.springframework.ldap.UncategorizedLdapException :" + exception.getMessage());

			UsuarioVO usuarioCreated = this.obtenerDatosUsuario(usuario.getUsuario());
			this.eliminarUserTransaccion(usuarioCreated);
			
		} catch (org.springframework.dao.DataIntegrityViolationException dataIntegrityViolation)
		{
			log.error("Problema en ldap +org.springframework.dao.DataIntegrityViolationException :" + dataIntegrityViolation.getMessage());

			UsuarioVO usuarioCreated = this.obtenerDatosUsuario(usuario.getUsuario());
			this.eliminarUserTransaccion(usuarioCreated);

		} catch (Exception e)
		{
			log.error("Error al dar de alta al usuario lanzamos la excepcion ", e);
			throw e;
		}
		return resultado;
	}
	
	
	/**
	 * Metodo para saber si la rama de LDAP de la Web Semantica existe
	 */
	private boolean ldapWebSemanticaActiva() {
		LDAPContactDAO ldapDao=null;
		boolean ldapWebSemanticaActivo=true;
		try {
			ldapDao=this.getLdapWebSemanticaHandler();
		} catch (Exception e) {
			ldapWebSemanticaActivo=false;
		}
		if(ldapDao==null) ldapWebSemanticaActivo=false;
		return ldapWebSemanticaActivo;
	}

	
	/**
	 * Metodo para dar de alta un usuario. Se le agregará en el servidor
	 * ldap y se enviará un correo con el usuario y la clave
	 * @param usuario usuarioVO con los datos del usuario que se dara de alta
	 * @return resultado String que devuelve si el alta ha ido bien o no
	 * @throws Exception
	 */
	protected Long handleAltaUsuario(es.pode.adminusuarios.negocio.servicios.UsuarioVO usuario) throws java.lang.Exception
	{
		boolean existeLdapWebSemantica=ldapWebSemanticaActiva();
		String ldapExterno=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.LDAPEXTERNO);
		
		// Pasamos a mayúsculas para que todos los docmentos de identidad se inserten en mayúsculas en bd
	    if (usuario.getNIF()!=null)
	    	usuario.setNIF(usuario.getNIF().toUpperCase());

	    // Pasamos a minúsculas para que todos los correos se inserten en minúsculas en bd
	    if (usuario.getEmail()!=null)
	    	usuario.setEmail(usuario.getEmail().toLowerCase());

	    //Damos al usuario de alta en la rama LDAP de la web semantica
		if(existeLdapWebSemantica && ldapExterno.equalsIgnoreCase("false")) {
			this.getLdapWebSemanticaHandler().insertUser(usuario.getUsuario(), this.getHashPassword(usuario.getClave()),
					usuario.getNombre() + " " + usuario.getApellido1() + " " + usuario.getApellido2());
		}

		try {
			//Damos de alta al usuario en Agrega
			return altaUsuarioAgrega(usuario, false, true);
			
		} catch (Exception e) {
		    //Damos al usuario de baja en la rama LDAP de la web semantica
			if(existeLdapWebSemantica && ldapExterno.equalsIgnoreCase("false")) {
				this.getLdapWebSemanticaHandler().deleteContact(usuario.getUsuario());
			}
			log.error("Error al dar de alta al usuario en agrega ", e);
			throw e;
		}
	}
	
	
	private ValidaBajaUsuarioVO bajaUsuarioAgrega(java.lang.Long[] ids, boolean bEnviarCorreoBajaUsuario) throws java.lang.Exception {
		
		ValidaBajaUsuarioVO validaBajaVO = new ValidaBajaUsuarioVO();
		String setTo[]=new String [ids.length];
		StringBuffer idiomas = null;
		ResultadoEnvioCorreoVO resultado= new ResultadoEnvioCorreoVO();
	
		int usuarioDeleted = 0;
		Usuario usuario = null;
		StringBuffer usuarios = new StringBuffer();

		Collection grupos = null;
		List itemsDeleted = new ArrayList();
		CorreoUsuarioVO correoUsuarioVO =new CorreoUsuarioVO();
		
		try
		{
			usuarioDeleted = 0;
			for (int j = 0; j < ids.length; j++)
			{
				correoUsuarioVO.setLdapExterno(true);
				Long identificador = ids[j];
				usuario = (this.getUsuarioDao()).load(identificador);
				grupos = usuario.getGrupos();
				usuario.setFechaBaja(new Date(System.currentTimeMillis()));
				usuario.setGrupos(null);
				
				////Elimino los grupos de trabajo que tiene asociados
				usuario.setGrupoTrabajo(null);
				usuarios.append(usuario.getUsuario()).append(",");
				//TODO Hacer el backup del usuarioPublico antes de borrarlo
				
				if( usuario.getUsuarioPublico()!=null /*&& !usuario.getUsuarioPublico().equals("")*/){
					ResultadoOperacionVO recibo = this.getSrvPerfilPublico().eliminarUsuarioPublico(usuario.getUsuario());
					if(recibo.getResultado().equals(Boolean.TRUE)){
						usuario.setUsuarioPublico(null);
					}
				}
				this.getUsuarioDao().update(usuario);
				if(log.isDebugEnabled())log.debug("Se actualiza la fecha de baja del usuario");
				if (!((AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.LDAPEXTERNO)).equalsIgnoreCase("true")))
				{
					this.getLdapHandler().deleteContact(usuario.getUsuario());
				}
				log.info("[BAJAUSUARIO] El usuario " + usuario.getUsuario()+ "ha sido dado de baja por el administrador: " );
				
				usuarioDeleted = usuarioDeleted + 1;
				itemsDeleted.add((this.getUsuarioDao()).toUsuarioVO(usuario));
				StringBuffer contenido = new StringBuffer();
				String idiomaSecundario = I18n.getInstance().obtenerIdiomaSecundarioPlataforma();
				int l=1;
				if(!(idiomaSecundario==null) && !idiomaSecundario.equals(""))
					l++;
//				setTo[j]=new String();
				setTo[j]=usuario.getEmail();
				
				correoUsuarioVO.setTo(setTo);
				correoUsuarioVO.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
				correoUsuarioVO.setHrefLogo(ImagenesAgrega.urlImagenLogoAgrega());
				correoUsuarioVO.setUrlImagenLogo(ImagenesAgrega.urlImagenLogoAgrega());
				correoUsuarioVO.setLdapExterno(false);
				correoUsuarioVO.setEmailUsuario(usuario.getEmail());
				correoUsuarioVO.setNodoTaller(false);
				correoUsuarioVO.setUsuario(usuario.getUsuario());
				
				if ("true".equals(AgregaPropertiesImpl.getInstance().getProperty("nodo.taller"))){
					idiomas = new StringBuffer();
					for(int m=0;m<l;m++){
						String idioma="";
						if(m==0){
							idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
						}else{
							idioma=idiomaSecundario;
						}
						idiomas.append(idioma).append(",");
						correoUsuarioVO.setNodoTaller(true);
						correoUsuarioVO.setLdapExterno(false);
					}
				}else{
					idiomas = new StringBuffer();
					for(int m=0;m<l;m++){
						String idioma="";
						if(m==0){
							idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
						}else{
							idioma=idiomaSecundario;
						}idiomas.append(idioma).append(",");
						correoUsuarioVO.setNodoTaller(false);
						correoUsuarioVO.setLdapExterno(false);
					}
				}
				if (bEnviarCorreoBajaUsuario)
				{
					SrvCorreo correoService=  this.getSrvCorreo();
					correoUsuarioVO.setIdiomaCorreo(idiomas.toString());
					resultado=correoService.bajaUsuario(correoUsuarioVO);
				}

			}
			validaBajaVO.setNumDeleted(new Integer(usuarioDeleted));
			validaBajaVO.setDescripcion("ok.borrarUsuarios");
			validaBajaVO.setItemsDeleted((UsuarioVO[]) itemsDeleted.toArray(new UsuarioVO[0]));
			// Enviamos un correo al administrador del ldap externo con
			// todos los usuarios que han sido realmente eliminados

			if ((AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.LDAPEXTERNO)).equalsIgnoreCase("true"))
			{
				correoUsuarioVO.setLdapExterno(true);
				correoUsuarioVO.setIdiomaCorreo(I18n.getInstance().obtenerIdiomaDefectoPlataforma());
				correoUsuarioVO.setUsuario(usuario.getUsuario());
				setTo[0]=(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ADMINLDAPEXTERNO));
				correoUsuarioVO.setTo(setTo);
				if(log.isDebugEnabled())log.debug("Enviamos un correo al administrador del ldap externo");
				SrvCorreo correoService=  this.getSrvCorreo();
				resultado=correoService.bajaUsuario(correoUsuarioVO);
				if(log.isDebugEnabled())log.debug("Resultado del envio del correo al administrador del ldap externo "+resultado.getResultado());
			}

		} catch (org.springframework.dao.DataRetrievalFailureException comunicationException)
		{
			log.error("Se produce una excepcion de ldap : org.springframework.dao.DataRetrievalFailureException " + comunicationException);
			usuario.setFechaBaja(null);
			usuario.setGrupos(grupos);
			usuario.setUsuarioPublico(null);
			this.getUsuarioDao().update(usuario);
			validaBajaVO.setNumDeleted(new Integer(0));
			validaBajaVO.setDescripcion("errors.borrarUsuario");
			validaBajaVO.setItemsDeleted((UsuarioVO[]) itemsDeleted.toArray(new UsuarioVO[0]));
		} catch (org.springframework.ldap.UncategorizedLdapException exception)
		{
			log.error("Se produce una excepcion de ldap : org.springframework.ldap.UncategorizedLdapException " + exception);
			
			usuario.setFechaBaja(null);
			usuario.setGrupos(grupos);
			usuario.setUsuarioPublico(null);

			this.getUsuarioDao().update(usuario);
			if(log.isDebugEnabled())log.debug("despueds de rollback");
			validaBajaVO.setNumDeleted(0);
			validaBajaVO.setDescripcion("errors.borrarUsuario");
			validaBajaVO.setItemsDeleted((UsuarioVO[]) itemsDeleted.toArray(new UsuarioVO[0]));
		} catch (Exception e)
		{
			log.error("Se ha producido la siguiente excepcion ", e);
//			BajaUsuarioException exception = new BajaUsuarioException("Error al eliminar el usuario ", e);
			validaBajaVO.setNumDeleted(0);
			validaBajaVO.setDescripcion("errors.borrarUsuario");
			validaBajaVO.setItemsDeleted((UsuarioVO[]) itemsDeleted.toArray(new UsuarioVO[0]));
			////throw exception;
		}
		return validaBajaVO;
	}
	

	/**
	 * Elimina de BD (actualiza la fecha de baja con la actual) y lo elimina
	 * del servidor ldap
	 * @param ids array de id de los usuarios que deben darse de baja
	 * @return ValidaBajaVO incluye el numero de usuarios que han sido
	 *         eliminados y una descripcion con el resultado de la operacion
	 * @throws Exception
	 */
	protected ValidaBajaUsuarioVO handleBajaUsuario(java.lang.Long[] ids) throws java.lang.Exception
	{
		Usuario usuario=null;
		List<UsuarioVO> itemsDeleted = new ArrayList<UsuarioVO>();
		ValidaBajaUsuarioVO validaBajaVO = new ValidaBajaUsuarioVO();
		validaBajaVO.setNumDeleted(0);
		validaBajaVO.setDescripcion("ok.borrarUsuarios");
		validaBajaVO.setItemsDeleted((UsuarioVO[]) itemsDeleted.toArray(new UsuarioVO[0]));
		
		try {
			boolean existeLdapWebSemantica=ldapWebSemanticaActiva();
			String ldapExterno=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.LDAPEXTERNO);
			
			for (int j=0; j<ids.length; j++) {
				
				Long identificador = ids[j];
				usuario = this.getUsuarioDao().load(identificador);

			    //Damos al usuario de baja en la rama LDAP de la web semantica
				if(existeLdapWebSemantica && ldapExterno.equalsIgnoreCase("false")) {
					this.getLdapWebSemanticaHandler().deleteContact(usuario.getUsuario());
				}
				
				try {
					//Damos de baja al usuario en Agrega	
					Long[] idUsuario=new Long[1];
					idUsuario[0]=ids[j];
					ValidaBajaUsuarioVO ret=bajaUsuarioAgrega(idUsuario, true);
					
					if(ret.getNumDeleted()!=1) {
					    //Damos al usuario de alta en la rama LDAP de la web semantica
						if(existeLdapWebSemantica && ldapExterno.equalsIgnoreCase("false")) {
							this.getLdapWebSemanticaHandler().insertUser(usuario.getUsuario(), this.getHashPassword(usuario.getClave()),
									usuario.getNombre() + " " + usuario.getApellido1() + " " + usuario.getApellido2());
						}
						if(validaBajaVO.getNumDeleted()==0) validaBajaVO.setDescripcion("errors.borrarUsuario");
						log.error("Error al dar de baja al usuario "+usuario.getUsuario()+" en agrega");
						return validaBajaVO;
					}
					
				} catch (Exception e) {
					//Damos al usuario de alta en la rama LDAP de la web semantica
					if(existeLdapWebSemantica && ldapExterno.equalsIgnoreCase("false")) {
						this.getLdapWebSemanticaHandler().insertUser(usuario.getUsuario(), this.getHashPassword(usuario.getClave()), 
								usuario.getNombre() + " " + usuario.getApellido1() + " " + usuario.getApellido2());
					}
					if(validaBajaVO.getNumDeleted()==0) validaBajaVO.setDescripcion("errors.borrarUsuario");							
					log.error("Error al dar de baja al usuario "+usuario.getUsuario()+" en agrega ", e);
					return validaBajaVO;
				}
				validaBajaVO.setNumDeleted(validaBajaVO.getNumDeleted()+1);
				itemsDeleted.add((this.getUsuarioDao()).toUsuarioVO(usuario));
				validaBajaVO.setItemsDeleted((UsuarioVO[]) itemsDeleted.toArray(new UsuarioVO[0]));
			}

		} catch (Exception e) {
			if(validaBajaVO.getNumDeleted()==0) validaBajaVO.setDescripcion("errors.borrarUsuario");
			log.error("Error al dar de baja al usuario/s en agrega ", e);
		}
		return validaBajaVO;	
	}
	

	/**
	 * Obtiene la lista de los usuarios activos
	 * @return usuarioV array de usuariosVO
	 * @throws Exception
	 */
	protected es.pode.adminusuarios.negocio.servicios.UsuarioVO[] handleListarUsuarios() throws java.lang.Exception
	{
		UsuarioVO[] usuarioV = null;

		try
		{
			Collection usuarios = this.getUsuarioDao().getUsuarios();
			Iterator iter = usuarios.iterator();
			Usuario usuario = null;
			UsuarioVO usuarioVO = null;
			ArrayList usuariosVO = new ArrayList();
			
			while(iter.hasNext())
			{
				usuarioVO = (es.pode.adminusuarios.negocio.servicios.UsuarioVO)this.getBeanMapper().map(iter.next(),es.pode.adminusuarios.negocio.servicios.UsuarioVO.class, "MAPEO_USUARIO_USUARIOVO");
				log.debug("usuarioVO "+usuarioVO);
				usuariosVO.add(usuarioVO);
			}
			
		//	this.getUsuarioDao().toUsuarioVOCollection(usuarios);
			if(log.isDebugEnabled())log.debug("[LISTARUSUARIOS] usuarios ");

			usuarioV = (UsuarioVO[]) usuariosVO.toArray(new UsuarioVO[0]);
			if(log.isDebugEnabled())log.debug("Obtenidos todos los usuarios ");

		} catch (Exception e)
		{
			log.error("Error: " + e);
			throw e;
		}

		return usuarioV;
	}

	/**
	 * Obtiene el usuario cuyo identificador sea el que se le pasa por
	 * parametro
	 * @param id identificador de un usuario
	 * @return UsuarioVO usuarioVO
	 * @throws Exception
	 */
	protected es.pode.adminusuarios.negocio.servicios.UsuarioVO handleDescripcionUsuario(java.lang.Long id) throws java.lang.Exception
	{
		try
		{
			UsuarioVO usuarioVO = null;
			usuarioVO = (UsuarioVO) this.getUsuarioDao().load(UsuarioDao.TRANSFORM_USUARIOVO, id);
			return usuarioVO;
		} catch (Exception e)
		{
			log.error("Error al obtener el usuarioVO apartir del id");
			DescripcionUsuarioException descripcionUsuarioException = new DescripcionUsuarioException("Error al obtener el UsuarioVO apartir del id", e);
			throw descripcionUsuarioException;
		}
	}

	/**
	 * Obtiene un array con los roles (descripciones) del usuario cuyo login
	 * se pasa por parametro
	 * @param usuario login del usuario
	 * @return resultado Array de string con los roles del usuario
	 * @throws Exception
	 */
	protected String[] handleListarRolesUsuario(java.lang.String usuario) throws java.lang.Exception
	{
		String[] resultado = null;
		List roles = null;

		UsuarioDao usuarioDao = this.getUsuarioDao();
		Usuario datosUsuario = usuarioDao.obtenerDatosUsuario(usuario);
		if(!(datosUsuario == null))
		{
			if (datosUsuario.getFechaDesactivacion()==null) {
				roles = usuarioDao.consultaHQL(usuario.toLowerCase());
				if(log.isDebugEnabled())log.debug("roles " + roles.size());
				if((roles == null)||(roles.size() == 0))
				{
					if(log.isDebugEnabled())log.debug("El usuario no dispone de ningun rol ");
				}else
				{
					
					
					String serverId  = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.SERVER_ID);
					
					if (serverId.equals(SERVER_ID_INTEF))										
					{
						String rolAdmin = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ROL_ADMINISTRADOR);
						String rolPublicador = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ROL_PUBLICADOR);
						String rolCatalogador = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ROL_CATALOGADOR);
					
						if (!roles.contains(rolAdmin) && !roles.contains(rolPublicador) && !roles.contains(rolCatalogador))
						{
							String rol[] = {"INACTIVO"};
							log.info("[LISTARROLESUSUARIO]el usuario no es administrador, le ponemos el rol INACTIVO para que no acceda a Agrega");
							resultado = rol;
						}
						else
						{
							resultado = (String[]) roles.toArray(new String[0]);
							if(log.isDebugEnabled())log.debug("roles ordenados " + resultado.length+", primera posición de los ordenados es: "+ resultado[0]);
						}
					}else
					{
						resultado = (String[]) roles.toArray(new String[0]);
						if(log.isDebugEnabled())log.debug("roles ordenados " + resultado.length+", primera posición de los ordenados es: "+ resultado[0]);
					}
				}
				
			} else {
				String rol[] = {"INACTIVO"};
				log.info("[LISTARROLESUSUARIO]el usuario está desactivado, le ponemos el rol INACTIVO");
				resultado = rol;
			}
		}
		return resultado;
	}

	/**
     * Da de alta un grupo en la BD
     * @param grupoVO  Datos de un GrupoVO
     * @return resultado Long con el identificador del grupo que se ha dado de alta
     * @throws Exception
     */

	protected Long handleAltaGrupo(es.pode.adminusuarios.negocio.servicios.GrupoVO grupoVO) throws java.lang.Exception
	{
		Long resultado=null;
		try
		{
			Grupo grupo = this.getGrupoDao().fromGrupoVO(grupoVO);
			Grupo grupoCreado = this.getGrupoDao().create(grupo);
			resultado=grupoCreado.getId();
			
		} catch (Exception e)
		{
			log.error("Se ha producido la siguiente excepcion " + e);
			AltaGrupoException exception = new AltaGrupoException("No se ha podido dar de alta el grupo", e);
			throw exception;
		}
		return resultado;
	}
	
	
	/**
	 * Da de alta un grupo de trabajo en la BD
	 * @param grupoTrabajoVO  Datos de un GrupoTrabajoVO
	 * @return long que es el identificador del grupo de trabajo que se ha dado de alta
	 * @throws Exception
	 */
	protected Long handleAltaGrupoTrabajo(es.pode.adminusuarios.negocio.servicios.GrupoTrabajoVO grupoTrabajoVO) throws java.lang.Exception
	{
		Long resultado=null;
		try
		{
			GrupoTrabajo grupoTrabajo = this.getGrupoTrabajoDao().fromGrupoTrabajoVO(grupoTrabajoVO);
			GrupoTrabajo grupo=this.getGrupoTrabajoDao().create(grupoTrabajo);
			resultado=grupo.getIdentificador();

		} catch (Exception e)
		{
			log.error("Se ha producido la siguiente excepcion " + e);
			throw e;
		}
		return resultado;
	}

	/**
     * Obtiene el rol a partir del identificador
     * @param id Identificador del rol
     * @return  RolVO con los datos de un rol
     * @throws Exception
     */
	protected es.pode.adminusuarios.negocio.servicios.RolVO handleGetRol(java.lang.Long id) throws java.lang.Exception
	{
		RolDao rolDao = this.getRolDao();
		RolVO r = (RolVO) rolDao.load(RolDao.TRANSFORM_ROLVO, id);
		return r;
	}

	/**
     * Comprueba si la descripcion que se le pasa por parámetro esta
     * asociado a otro grupo
     * @param descripcion descripcion del grupo
     * @param id identificador del grupo
     * @return retorno Bolean  indicando si existe ya un grupo con esa descripción
     * @throws Exception
     */

	protected java.lang.Boolean handleExisteDescripcion(java.lang.String descripcion, java.lang.Long id) throws java.lang.Exception
	{
		Boolean retorno = false;
		GrupoVO[] grupoV = null;
		Collection grupos = this.getGrupoDao().loadAll(GrupoDao.TRANSFORM_GRUPOVO);
		grupoV = (GrupoVO[]) grupos.toArray(new GrupoVO[0]);
		if (id.compareTo(new Long("-1")) == 0)
		{

			if(log.isDebugEnabled())log.debug("Alta grupo la descripcion no puede coincidir");
			for (int j = 0; j < grupoV.length; j++)
			{
				if (grupoV[j].getDescripcion().equalsIgnoreCase(descripcion))
				{
					retorno = Boolean.TRUE;
				}
			}
		} else
		{

			for (int j = 0; j < grupoV.length; j++)
			{
				if (grupoV[j].getDescripcion().equalsIgnoreCase(descripcion))
				{
					if (grupoV[j].getId().compareTo(id) != 0)
					{
						retorno = Boolean.TRUE;
					}
				}
			}
		}
		return retorno;
	}

	/**
	 * Obtiene el usuario cuyo nif coincida con el que se pasa por
	 * parámetro. Este método se invocará durante el proceso de alta de un
	 * usuario puesto que no se va a permitir que existan dos usuarios
	 * registrados con el mismo nif.
	 * @param nif Nif del usuario
	 * @return Usuario usuarioVO, en caso de no haber en BD ningún usuario
	 *         con ese nif se devolverá null
	 * @throws Exception
	 */
	protected es.pode.adminusuarios.negocio.servicios.UsuarioVO handleObtenerUsuario(java.lang.String nif) throws java.lang.Exception
	{
		UsuarioVO usuario = null;
//		usuario = new UsuarioVO();
		Usuario usuarioEntity = this.getUsuarioDao().obtenerUsuario(nif);
		if (usuarioEntity == null)
		{
			if(log.isDebugEnabled())log.debug("No existe un usuario en BD con ese nif " + nif);
		} else
		{
			usuario = new UsuarioVO();
			log.debug("El usuario obtenido de BD con ese nif es "+usuarioEntity);
			this.getBeanMapper().map(usuarioEntity, usuario, "MAPEO_USUARIO_USUARIOVO");
			//usuario = this.getUsuarioDao().toUsuarioVO(usuarioEntity);
		}

		return usuario;
	}

	/**
	 * Compruebo si el usuario (login) que se le pasa por parámetro ya esta
	 * asociado a otro usuario
	 * @param usuario login del usuario
	 * @return resultado Boolean que inidica si ha ido bien o no la
	 *         operacion si existe ya en BD un usuario con ese login
	 *         devolverá un true.
	 * @throws Exception
	 */
	protected java.lang.Boolean handleExisteUsuario(java.lang.String usuario)
	{
		Boolean resultado = Boolean.TRUE;
		Usuario usuarioEntity = this.getUsuarioDao().obtenerDatosUsuario(usuario.toLowerCase());

		if (usuarioEntity == null)
		{
			resultado = Boolean.FALSE;
		}

		return resultado;
	}

	

	/**
	 * Envio de correo cuando un usuario solicita la baja del sistema. Se
	 * envia un correo a todos los administradores con el login del usuario
	 * que se quiere dar de baja
	 * @param usuarioVO usuarioVO con los datos del usuario
	 * @return String que indica si ha ido bien o no la operacion
	 * @throws Exception
	 */
	protected java.lang.String handleEnviarCorreoBaja(UsuarioVO usuarioVO) throws java.lang.Exception
	{
		String resultado = "";
		String[] destinatarios = null;
		String nodo = "";
		StringBuffer idiomas=null;
		CorreoUsuarioVO correoUsuarioVO=new CorreoUsuarioVO();
		ResultadoEnvioCorreoVO correoVO2= new ResultadoEnvioCorreoVO();
		SrvCorreo correoService=  this.getSrvCorreo();
		try{
			idiomas=new StringBuffer();
			String idiomaSecundario = I18n.getInstance().obtenerIdiomaSecundarioPlataforma();
			int l=1;
			if(!(idiomaSecundario==null) && !idiomaSecundario.equals(""))
				l++;
			destinatarios = this.getEmailAdmin(); // Vector con los emails de
			// todos los administradores que estan dados de alta en el
			// sistema
			for(int j=0;j<l;j++){
				String idioma="";
			if(j==0){
				idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
			}else{
				idioma=idiomaSecundario;
			}
			idiomas.append(idioma).append(",");
			}
			int num_correos_enviados = 0;
			boolean envioCorrecto = false;
			String setTo[]=new String [destinatarios.length];
			correoUsuarioVO.setLdapExterno(false);
			correoUsuarioVO.setEmailUsuario(usuarioVO.getEmail());
			correoUsuarioVO.setNodoTaller(false);
			correoUsuarioVO.setUsuario(usuarioVO.getUsuario());
			correoUsuarioVO.setPassword("");
			correoUsuarioVO.setHrefLogo(ImagenesAgrega.urlImagenLogoAgrega());
			correoUsuarioVO.setUrlImagenLogo(ImagenesAgrega.urlImagenLogoAgrega());
			log.debug("setPassword"+correoUsuarioVO.getPassword() );
		
			for (int i = 0; i < destinatarios.length; i++)
			{
				setTo[i]=destinatarios[i];
			}
			correoUsuarioVO.setTo(setTo);
			correoUsuarioVO.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
			correoUsuarioVO.setIdiomaCorreo(idiomas.toString());
			correoVO2=correoService.solicitudBajaUsuario(correoUsuarioVO);
			log.debug("Resultado del envio de correo de solicitud baja "+correoVO2.getResultado());
			if ("OK".equals(correoVO2.getResultado())){
				resultado = "1";

			}else
			{
				resultado = "0";
			}
			if ((AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.LDAPEXTERNO)).equalsIgnoreCase("true"))
			{
					correoUsuarioVO.setLdapExterno(true);
					correoUsuarioVO.setEmailUsuario(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ADMINLDAPEXTERNO));
					correoUsuarioVO.setIdiomaCorreo((I18n.getInstance().obtenerIdiomaDefectoPlataforma()));
					correoUsuarioVO.setPassword("");
					log.debug("setPassword"+correoUsuarioVO.getPassword() );
					String setToAdminLdap[]=new String [1];
					setToAdminLdap[0]=(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ADMINLDAPEXTERNO));
					correoUsuarioVO.setTo(setToAdminLdap);
					correoVO2=correoService.solicitudBajaUsuario(correoUsuarioVO);
					
			}
						
			
		}catch (Exception e)
		{

			log.error("Error al enviar el correo de solicitud de baja ", e);
			resultado = "0";

		}

		if(log.isDebugEnabled())log.debug("resultado del envio de la solicitud de baja vale " + resultado);
		return resultado;
	}

	/**
	 * Obtiene los datos de un usuario deovolviendo el VO completo del
	 * usuario corespondiente al login que se pasa como parametro
	 * @param usuario String con el login del usuario
	 * @return usuarioVO usuarioVO con los datos del usuario
	 * @throws Exception
	 */
	protected es.pode.adminusuarios.negocio.servicios.UsuarioVO handleObtenerDatosUsuario(java.lang.String usuario) throws java.lang.Exception
	{
		UsuarioVO usuarioVO = null;
		Usuario usuarioEntity = this.getUsuarioDao().obtenerDatosUsuario(usuario.toLowerCase());
		if (usuarioEntity == null)
		{
			log.error("No existe un usuario cuyo login sea " + usuario);
		} else
		{
			
//			usuarioVO = (es.pode.adminusuarios.negocio.servicios.UsuarioVO) this.getBeanMapper().map(usuarioEntity,
//		    		es.pode.adminusuarios.negocio.servicios.UsuarioVO.class, "DEF_MAPPING_USUARIO_USUARIOVO");
			
			usuarioVO = (es.pode.adminusuarios.negocio.servicios.UsuarioVO) this.getBeanMapper().map(usuarioEntity,
		    		es.pode.adminusuarios.negocio.servicios.UsuarioVO.class, "MAPEO_USUARIO_USUARIOVO");
			
		//	this.getBeanMapper().map(usuarioEntity, usuario, "MAPEO_USUARIO_USUARIOVO");
			//usuarioVO = this.getUsuarioDao().toUsuarioVO(usuarioEntity);
			//usuarioVO.setUsuarioPublico(null);
		}

		return usuarioVO;
	}

	/**
	 * Devuelve los mails de los usuarios que en ese momento son
	 * administradores
	 * @return String[] usuarioVO usuarioVO con los datos del usuario
	 * @throws Exception
	 */
	protected String[] handleGetEmailAdmin() throws Exception
	{
		String[] resultado = null;
		try
		{
			List listaUsuarios = this.getUsuarioDao().obtenerEmailAdmin(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ROL_ADMINISTRADOR));
			if(log.isDebugEnabled())log.debug("Obtenemos los email de los admin del sistema " + listaUsuarios.size());

			// Obtenemos el correo de todos aquellos usuarios que tengan un
			// grupo administrador.
			resultado = new String[listaUsuarios.size()];
			Iterator iter = listaUsuarios.iterator();
			for (int i = 0; i < resultado.length; i++)
			{
				resultado[i] = (String) iter.next();
			}

			// Sacamos todos los usuarios que tienen como grupo el
			// administrador
		} catch (Exception e)
		{
			log.error("Se produce un error al intentar obtener los correos de los administradores ", e);
			throw e;
		}
		return resultado;

	}

	
	/**
     * Devuelve los mails de los usuarios cuyo rol sea publicador
     * @return String[] con los datos del usuario
     * @throws Exception
     */

	protected String[] handleGetEmailPublicadores() throws Exception
	{
		String[] resultado = null;
		String rolPublicador = "";
		try
		{
			rolPublicador = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ROL_PUBLICADOR);
			List listaUsuarios = this.getUsuarioDao().obtenerEmailAdmin(rolPublicador);
			if(log.isDebugEnabled())log.debug("Obtenemos los email de los publicadores del sistema " + listaUsuarios.size());

			// Obtenemos el correo de todos aquellos usuarios que tengan un
			// grupo administrador.
			resultado = new String[listaUsuarios.size()];
			resultado = (String[])listaUsuarios.toArray(new String[0]);
			
			// Sacamos todos los usuarios que tienen como grupo el
			// administrador
		} catch (Exception e)
		{
			log.error("Se produce un error al intentar obtener los correos de los administradores ", e);
			throw e;
		}
		return resultado;

	}
	

	/**
	 * Obtiene los identificadores de los usuarios que son administradores
	 * @return resultado array de Long
	 * @throws Exception
	 */
	protected Long[] handleObtenerUsuariosAdministrador() throws Exception
	{
		Long[] resultado = null;
		try
		{
			String admin = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ROL_ADMINISTRADOR);
			Collection lista = this.getUsuarioDao().obtenerUsuariosAdministradores(admin);
			resultado = (Long[]) lista.toArray(new Long[0]);
			return resultado;
		
		} catch (Exception e)
		{
			log.error("Se produce una excepcion al obtener los usuarios con rol administrador", e);
			throw e;
		}
	}

	/**
	 * Obtiene los identificadores de los grupos que contienen usuarios que
	 * son administradores
	 * @return resultado array de Long
	 * @throws Exception
	 */
	protected Long[] handleObtenerGrupoAdministrador() throws Exception
	{
		Long[] resultado = null;
		try
		{
			String admin = AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ROL_ADMINISTRADOR);
			List lista = this.getGrupoDao().obtenerGruposAdministradores(admin);
			resultado = (Long[]) lista.toArray(new Long[0]);

			return resultado;
		} catch (Exception io)
		{
			log.error("Se produce una excepcion al leer el fichero adminusuarios.properties", io);
			throw io;
		}

	}

	

	/**
	 * Obtiene los usuarios pendientes de dar de alta en el sistema
	 * @return usuarioV UsuarioVO[] con los datos de los usuarios pendientes
	 *         de dar de alta
	 * @throws Exception
	 */
	protected UsuarioVO[] handleListarUsuariosPendientes() throws Exception
	{
		UsuarioVO[] usuarioV = null;

		try
		{
			Collection usuarios = this.getUsuarioDao().getUsuariosPendientes();
			this.getUsuarioDao().toUsuarioVOCollection(usuarios);
			usuarioV = (UsuarioVO[]) usuarios.toArray(new UsuarioVO[0]);

			if(log.isDebugEnabled())log.debug("[LISTARUSUARIOSPENDIENTES] usuarios pendientes " + usuarios.size());

		} catch (Exception e)
		{
			log.error("Error: " + e);
			throw e;
		}
		return usuarioV;

	}

	/**
	 * Almacena en el sistema la solicitud de alta de un usuario.
	 * @param usuario UsuarioVO con los datos del usuario que solicita el alta
	 * @return resultado String que indica si la operación se ha llevado a cabo
	 *         con éxito o no
	 * @throws Exception
	 */
	protected String handleSolicitarAltaUsuario(UsuarioVO usuario) throws Exception
	{
		Usuario usuarioCreado = null;
		String resultado = "";
		try
		{
			usuario.setFechaSolicitudAlta(new java.util.Date());
			if (this.obtenerUsuario(usuario.getNIF()) == null)
			{
				usuarioCreado = this.getUsuarioDao().create(this.getUsuarioDao().fromUsuarioVO(usuario));
				if(log.isDebugEnabled())log.debug("Usuario almacenado en BBDD" + usuarioCreado.getUsuario());
			} else
			{

				if(log.isDebugEnabled())log.debug("Modificamos un usuario ya registrado en el sistema");
				Usuario usuarioEntity = this.getUsuarioDao().load(usuario.getId());
				this.getBeanMapper().map(usuario, usuarioEntity, "MAPEO_USUARIOVO_USUARIO");
				//this.getUsuarioDao().fromUsuarioVO(usuario, usuarioEntity);
				this.getUsuarioDao().update(usuarioEntity);
			}
//			handleEnviarCorreoAlta(usuario);
			resultado = "OK";

		} catch (Exception e)
		{
			resultado = "FALLO";
			log.error("Error al dar de alta al usuario ", e);
		}
		return resultado;
	}

	/**
	 * Da de baja uno o varios usuarios que están pendientes de dar de alta 
	 * en el sistema.
	 * @param ids Long[] array de identificadores de usuarios
	 * @param emailAdmin UsuarioVO datos del administrador que efectúa la baja.
	 * @return validaBajaVO ValidaBajaUsuarioVO que contiene el número de usuarios
	 * dados de baja, un array con los usuarios y el resultado de la operación.
	 * @throws Exception
	 */
	protected ValidaBajaUsuarioVO handleBajaUsuarioPendiente(Long[] ids, UsuarioVO emailAdmin) throws Exception
	{
		ValidaBajaUsuarioVO validaBajaVO = new ValidaBajaUsuarioVO();

		int usuarioDeleted = 0;
		Usuario usuario = null;
		List itemsDeleted = new ArrayList();

		try
		{
			usuarioDeleted = 0;
			for (int j = 0; j < ids.length; j++)
			{
				Long identificador = ids[j];
				usuario = (this.getUsuarioDao()).load(identificador);
				usuario.setFechaBaja(new Date(System.currentTimeMillis()));
				usuario.setGrupos(null);
				this.getUsuarioDao().update(usuario);
				if(log.isDebugEnabled())log.debug("Se actualiza la fecha de baja del usuario pendiente");
				usuarioDeleted = usuarioDeleted + 1;
				itemsDeleted.add((this.getUsuarioDao()).toUsuarioVO(usuario));
				StringBuffer contenido = new StringBuffer();
				contenido.append("<html>");
				contenido.append("<br>");
				contenido.append("<br>");
				contenido.append(getResource("correo.bajaUsuarioPendiente.contenido1"));
				contenido.append(" " + "<b>" + usuario.getUsuario() + "</b>" + " ");
				contenido.append(getResource("correo.bajaUsuarioPendiente.contenido2"));
				contenido.append("</br>");
				contenido.append("</html>");
//				this.enviarCorreoThread(usuario.getEmail(), getResource("correo.bajaUsuarioPendiente.asunto"), contenido, emailAdmin.getEmail());
			}
			validaBajaVO.setNumDeleted(new Integer(usuarioDeleted));
			validaBajaVO.setDescripcion("ok.borrarUsuarios");
			validaBajaVO.setItemsDeleted((UsuarioVO[]) itemsDeleted.toArray(new UsuarioVO[0]));

		} catch (Exception e)
		{
			log.error("Se ha producido la siguiente excepcion ", e);
			BajaUsuarioException exception = new BajaUsuarioException("Error al eliminar el usuario ", e);
			validaBajaVO.setNumDeleted(0);
			validaBajaVO.setDescripcion("errors.borrarUsuario");
			validaBajaVO.setItemsDeleted((UsuarioVO[]) itemsDeleted.toArray(new UsuarioVO[0]));
			throw exception;
		}

		return validaBajaVO;
	}

	/**
	 * Obtiene los usuarios activos en un rago de fechas determinado.
	 * @param parametroAuditoriaUsuariosVO
	 *                ParametroAuditoriaUsuariosVO contiene el rango de fechas
	 * @return usuariosActivosVO UsuarioActivoVO que contiene los usuarios activos 
	 * 		   en el rango especificado.
	 * @throws Exception
	 */
	protected UsuarioActivoVO[] handleUsuariosActivos(ParametroAuditoriaUsuariosVO parametroAuditoriaUsuariosVO) throws Exception
	{
		UsuarioActivoVO[] usuariosActivosVO = null;
		try
		{
			UsuarioDao usuarioDao = this.getUsuarioDao();
			if(log.isDebugEnabled())log.debug("despues de obtener usuarioDao " + usuarioDao);
			// log("parametrosAuditoriaUsuarioVO.getFechaHasta() "+parametrosAuditoriaUsuarioVO.getFechaHasta());
			// List list = usuarioDao.usuariosActivos(fechaFin);
			List list = usuarioDao.usuariosActivos(parametroAuditoriaUsuariosVO.getFechaHasta());
			usuariosActivosVO = new UsuarioActivoVO[list.size()];
			if(log.isDebugEnabled())log.debug("El tamanio de la lista de los usuarios activos es " + list.size());
			for (int i = 0; i < list.size(); i++)
			{
				usuariosActivosVO[i] = new UsuarioActivoVO();
				usuariosActivosVO[i].setNombre(((Usuario) list.get(i)).getNombre());
				usuariosActivosVO[i].setApellido1(((Usuario) list.get(i)).getApellido1());
				usuariosActivosVO[i].setApellido2(((Usuario) list.get(i)).getApellido2());
				usuariosActivosVO[i].setLogin(((Usuario) list.get(i)).getUsuario());
				usuariosActivosVO[i].setEmail(((Usuario) list.get(i)).getEmail());
				usuariosActivosVO[i].setIdiomaAplicacion(((Usuario) list.get(i)).getIdioma());
				usuariosActivosVO[i].setIdiomaBusqueda(((Usuario) list.get(i)).getIdiomaBusqueda());
				usuariosActivosVO[i].setTipoEmpaquetador(((Usuario) list.get(i)).getTipoEmpaquetador());
				usuariosActivosVO[i].setNIF(((Usuario) list.get(i)).getNIF());
				//Asignamos el array con el nombre de los grupos que tiene asignados
				StringBuilder grupos = new StringBuilder();
				String rols = "";
				Grupo[] grupo = (Grupo[]) (((Usuario) list.get(i)).getGrupos().toArray(new Grupo[0]));
				if (!(grupo == null))
				{
					Vector roles = new Vector();
					for (int j = 0; j < grupo.length; j++)
					{
						grupos.append(" " + grupo[j].getDescripcion());
						Rol[] rolesGrupo = (Rol[]) (grupo[j].getRols()).toArray(new Rol[0]);

						for (int k = 0; k < rolesGrupo.length; k++)
						{
							if (!(roles.contains(rolesGrupo[k].getDescripcion())))
							{
								roles.add(rolesGrupo[k].getDescripcion());
							}
						}

					}
					//Asigno al usuario sus permisos
					for (int l = 0; l < roles.size(); l++)
					{
						rols = rols + " " + (String) roles.elementAt(l);
					}
					if(log.isDebugEnabled())log.debug("rols vale " + rols);
					usuariosActivosVO[i].setPermisos(rols);
					if(log.isDebugEnabled())log.debug("El usuario tiene " + (usuariosActivosVO[i].getPermisos()) + " permisos");
					usuariosActivosVO[i].setGrupos(grupos.toString());
					if(log.isDebugEnabled())log.debug("El usuario tiene " + (usuariosActivosVO[i].getGrupos()) + " grupos");
				}

			}

		} catch (Exception e)
		{
			log.error("Error : " + e);
			throw e;
		}

		return usuariosActivosVO;
	}

	/**
	 * Obtiene un listado de todos los usuarios presentes en el sistema salvo 
	 * los que están pendientes de dar de alta
	 * @return usuarioV UsuarioVO[] con los datos de los usuarios del sistema.
	 * @throws Exception
	 */
	protected UsuarioVO[] handleListarTodosUsuarios() throws Exception
	{

		UsuarioVO[] usuarioV = null;
		try
		{
			Collection usuarios = this.getUsuarioDao().getTodosUsuarios();
			Iterator iter = usuarios.iterator();
			UsuarioVO usuarioVO = null;
			ArrayList usuariosVO = new ArrayList();
			while(iter.hasNext())
			{
				
				usuarioVO = (es.pode.adminusuarios.negocio.servicios.UsuarioVO)this.getBeanMapper().map(iter.next(),es.pode.adminusuarios.negocio.servicios.UsuarioVO.class, "DEF_MAPPING_USUARIO_USUARIOVO");
				log.debug("usuarioVO "+usuarioVO);
				usuariosVO.add(usuarioVO);
			}
		//	this.getUsuarioDao().toUsuarioVOCollection(usuarios);
			if(log.isDebugEnabled())log.debug("[LISTARTODOSUSUARIOS] usuarios: ");

			usuarioV = (UsuarioVO[]) usuariosVO.toArray(new UsuarioVO[0]);

		} catch (Exception e)
		{
			log.error("Error: " + e);
			throw e;
		}
		return usuarioV;
				
	}

	/**
    * Activa un usuario que estaba inactivo
    * @param id Long identificador del usuario que se desea activar
    * @param admin login del administrador que activa el usuario
    * @throws Exception
    */
	protected void handleActivarUsuario(Long id, String admin){
		Usuario usuario = null;
		usuario = (this.getUsuarioDao()).load(id);
		usuario.setFechaDesactivacion(null);
		this.getUsuarioDao().update(usuario);
		log.info("[ACTIVARUSUARIO] El usuario " + usuario.getUsuario()+ "ha sido activado por el administrador: " + admin);
		
	}


	/**
	 * Desactiva un usuario que estaba activo
	 * @param id Long identificador del usuario que se desea desactivar
	 * @param admin login del administrador que desactiva el usuario
	 * @throws Exception
	 */

	protected void handleDesactivarUsuario(Long id, String admin) throws Exception {
		CorreoUsuarioVO correoUsuarioVO =new CorreoUsuarioVO();
		ResultadoEnvioCorreoVO correoVO2 = new ResultadoEnvioCorreoVO();
		String setTo[]=new String [1];
		Usuario usuario = null;
		StringBuffer idiomas = new StringBuffer();;

		usuario = (this.getUsuarioDao()).load(id);
		usuario.setFechaDesactivacion(new Date(System.currentTimeMillis()));
		this.getUsuarioDao().update(usuario);
		
		String idiomaSecundario = I18n.getInstance().obtenerIdiomaSecundarioPlataforma();
		int l=1;
		if(!(idiomaSecundario==null) && !idiomaSecundario.equals(""))
			l++;
		setTo[0]=usuario.getEmail();
		correoUsuarioVO.setTo(setTo);
		correoUsuarioVO.setFrom(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.FROMSENDER));
		correoUsuarioVO.setHrefLogo(ImagenesAgrega.urlImagenLogoAgrega());
		correoUsuarioVO.setUrlImagenLogo(ImagenesAgrega.urlImagenLogoAgrega());
		log.debug("from :" + correoUsuarioVO.getFrom());
		
		correoUsuarioVO.setEmailUsuario(usuario.getEmail());
		log.debug("setEmailUsuario :" + correoUsuarioVO.getEmailUsuario());
		correoUsuarioVO.setUsuario(usuario.getUsuario());
		log.debug("getUsuario :" + correoUsuarioVO.getUsuario());
		correoUsuarioVO.setLdapExterno(false);
		log.debug("setLdapExterno :" + correoUsuarioVO.getLdapExterno());
		correoUsuarioVO.setNodoTaller(false);
		log.debug("setNodoTaller :" + correoUsuarioVO.getNodoTaller());
		correoUsuarioVO.setPassword(usuario.getClave());
		//Se manda un correo al usuario para notificarle la desactivación
		for(int j=0;j<l;j++){
			String idioma="";
			if(j==0){
				idioma=I18n.getInstance().obtenerIdiomaDefectoPlataforma();
			}else{
				idioma=idiomaSecundario;
			}
			idiomas.append(idioma).append(",");
		log.info("[DESACTIVARUSUARIO] El usuario " + usuario.getUsuario()+ "ha sido desactivado por el administrador: " + admin);
		}
		SrvCorreo correoService=  this.getSrvCorreo();
		correoUsuarioVO.setIdiomaCorreo(idiomas.toString());
		correoVO2=correoService.desactivarUsuario(correoUsuarioVO);
		log.debug("El resultado del envio del correo de desactivacion "+correoVO2.getResultado());
	}

	/**
	 * Obtiene un listado de todos los usuarios inactivos del sistema.
	 * @return usuarioV UsuarioVO[] con los datos de los usuarios inactivos.
	 * @throws Exception
	 */
	protected UsuarioVO[] handleListarUsuariosInactivos() throws Exception {
		UsuarioVO[] usuarioV = null;

		try
		{
			Collection usuarios = this.getUsuarioDao().getUsuariosInactivos();
			this.getUsuarioDao().toUsuarioVOCollection(usuarios);
			usuarioV = (UsuarioVO[]) usuarios.toArray(new UsuarioVO[0]);

			if(log.isDebugEnabled())log.debug("[LISTARUSUARIOSINACTIVOS] usuarios inactivos " + usuarios.size());

		} catch (Exception e)
		{
			log.error("Error: " + e);
			throw e;
		}
		return usuarioV;
	}
	
	

	/**
	 * Obtiene un listado de todos los grupos de trabajo del sistema.
	 * @return grupoTrabajo GrupoTrabajoVO[] con los datos de los grupos de trabajo.
	 * @throws Exception
	 */
	
	protected GrupoTrabajoVO[] handleListarGruposTrabajo() throws Exception
	{
		
		GrupoTrabajoVO[] resultado = null;
		try
		{
			Collection gruposTrabajo = this.getGrupoTrabajoDao().loadAll(GrupoTrabajoDao.TRANSFORM_GRUPOTRABAJOVO);
			resultado = (GrupoTrabajoVO[]) gruposTrabajo.toArray(new GrupoTrabajoVO[0]);
	
		} catch (Exception e)
		{
			log.error("Error: " + e);
			throw e;
		}
		return resultado;
	}
	

	/**
	 * Obtiene un listado de todos los usuarios que pertenezcan al mismo grupo/s de trabajos que el usuario que se pasa como parámetro
	 * como mínimo todos los usuarios estarán en un grupo de trabajo.
	 * @param  loginUsuarioGrupoTrabajo identificador del usuario cuyos grupos se quieren obtener
	 * @return String[] Array de identificadores de los usuarios.
	 * @throws Exception
	 */
	
	protected String[] handleObtenerListaUsuariosGrupoTrabajo(String loginUsuarioGrupoTrabajo) throws Exception
	{
		String[] resultado = null;
		List listaUsuarios = null;
		UsuarioVO usuarioGrupoTrabajo = null;
		GrupoTrabajoVO[] grupoTrabajo = null;
		//Primero vamos a comprobar si el usuario tiene el grupo Todos, en este caso devolveríamos todos los usuarios con grupo
		
		usuarioGrupoTrabajo = (UsuarioVO)this.getUsuarioDao().obtenerDatosUsuario(UsuarioDao.TRANSFORM_USUARIOVO, loginUsuarioGrupoTrabajo);
		if(log.isDebugEnabled())log.debug("usuarioGrupoTrabajo "+usuarioGrupoTrabajo);
		grupoTrabajo = usuarioGrupoTrabajo.getGrupoTrabajo();
		if(!(grupoTrabajo == null) && !(grupoTrabajo.length == 0))
		{
			//Si alguno de los grupos de trabajo del usuario es el grupo "Todos" se devolverá null 
			//de esta forma desde el catalogador se obtendrán todos los odes en estado pendientes de catalogación
			if((this.estaGrupoTrabajoTodos(grupoTrabajo)).booleanValue())
			{
				if(log.isDebugEnabled())log.debug("El usuario tiene como grupo de trabajo Todos");
				/////listaUsuarios = this.getUsuarioDao().getUsuariosTodosGruposTrabajo();
				////log("tamanio listaUsuarios (todos) "+listaUsuarios.size());
			
			}else
			{
				if(log.isDebugEnabled())log.debug("el usuario a partir del que se van a obtener los grupos de trabajo "+loginUsuarioGrupoTrabajo);
				listaUsuarios = this.getUsuarioDao().getUsuariosGrupoTrabajo(loginUsuarioGrupoTrabajo);
				if(log.isDebugEnabled())log.debug("tamanio listaUsuarios "+listaUsuarios.size());
				resultado = (String[]) listaUsuarios.toArray(new String[0]);
			
			}
			
		}
		return resultado;
	}

	/**
	 * Obtiene un listado de todos los usuarios que pertenezcan al mismo grupo/s de trabajos que el usuario que se pasa como parámetro
	 * @param  identificador identificador del usuario cuyos grupos se quieren obtener
	 * @return String[] Array de identificadores de los usuarios.
	 * @throws Exception
	 */
	protected GrupoTrabajoVO handleDescripcionGrupoTrabajo(Long identificador) throws Exception
	{
		GrupoTrabajoVO grupoTrabajoVO = null;
		grupoTrabajoVO = (GrupoTrabajoVO) this.getGrupoTrabajoDao().load(GrupoTrabajoDao.TRANSFORM_GRUPOTRABAJOVO, identificador);
		return grupoTrabajoVO;
		
	}
	
	
	
	/**
	 * Actualiza los datos del grupo de trabajo pasado por parametro con los datos que este contiene
	 * @param grupoTrabajo grupoTrabajoVO obtiene los nuevos datos de este grupo de trabajo
	 * @return Long con el identificador del grupo de trabajo
	 * @throws java.lang.Exception
	 */
	protected Long handleModificarGrupoTrabajo(es.pode.adminusuarios.negocio.servicios.GrupoTrabajoVO grupoTrabajo) throws java.lang.Exception
    {
		Long id=null;
		try
		{

			id = grupoTrabajo.getIdentificador();
			GrupoTrabajo grupoTrabajoEntity = this.getGrupoTrabajoDao().load(id);
			this.getGrupoTrabajoDao().fromGrupoTrabajoVO(grupoTrabajo, grupoTrabajoEntity);
			this.getGrupoTrabajoDao().update(grupoTrabajoEntity);

		} catch (Exception e)
		{
			log.error("Se ha producido la siguiente excepcion " + e);
			ModificacionGrupoException exception = new ModificacionGrupoException("No se ha podido modificar el grupo", e);
			throw exception;
		}
		return id;
    }
	
	
	/**
	 * Elimina los grupos de trabajo cuyo identoficador coincide con los pasados como parametro 
	 * @param ids identificadores de grupos de trabajo para ser eliminados
	 * @return ValidaBajaGrupoTrabajoVO
	 * @throws java.lang.Exception
	 */
	protected es.pode.adminusuarios.negocio.servicios.ValidaBajaGrupoTrabajoVO handleBajaGrupoTrabajo(java.lang.Long[] ids) throws java.lang.Exception
	{
		ValidaBajaGrupoTrabajoVO validaBajaVO = new ValidaBajaGrupoTrabajoVO();

		int grupoDeleted = 0;

		List itemsDeleted = new ArrayList();
		try
		{
			grupoDeleted = 0;
			for (int j = 0; j < ids.length; j++)
			{
				Long identificador = ids[j];
				if (!(this.obtenerUsuariosGrupoTrabajo(identificador)).booleanValue())
				{
					GrupoTrabajo grupoTrabajo = (this.getGrupoTrabajoDao()).load(identificador);
					GrupoTrabajo grupoTrabajoAux = grupoTrabajo;
					this.getGrupoTrabajoDao().remove(grupoTrabajo);
					grupoDeleted = grupoDeleted + 1;
				    itemsDeleted.add(grupoTrabajoAux.getDescripcion());
					
				}
			}
			if(log.isDebugEnabled())log.debug("despues de borrar todos los grupos de trabajo");
			//validaBajaVO.setItemsDeleted((GrupoTrabajoVO[]) itemsDeleted.toArray(new GrupoTrabajoVO[0]));
			validaBajaVO.setItemsDeleted((String[]) itemsDeleted.toArray(new String[0]));
			if(log.isDebugEnabled())log.debug("despues de validaBajaVO.setItemsDeleted");
			if (grupoDeleted == ids.length)
			{
				validaBajaVO.setDescripcion("ok.borrarGrupo");
			} else
			{
				validaBajaVO.setDescripcion("errors.borrarGrupo.algunosGrupos");
			}

		} catch (Exception e)
		{
			log.error("Se ha producido la siguiente excepcion " + e);
			BajaGrupoException exception = new BajaGrupoException("Error al eliminar el grupo ", e);

			validaBajaVO.setDescripcion("errors.borrarGrupo");
			throw exception;
		}

		// }

		return validaBajaVO;

	}
	
	/**
	 * Elimina en BD el usuario que se le pasa como parametro, se invocara
	 * este metodo si se produce un error en la insercion en ldap
	 * @param usuario usuarioVO con los datos del usuario
	 */
	private void eliminarUserTransaccion(UsuarioVO usuario)
	{

		try
		{

			Usuario usuarioEntity = this.getUsuarioDao().load(usuario.getId());
			this.getUsuarioDao().fromUsuarioVO(usuario, usuarioEntity);
			usuarioEntity.setGrupos(null);
			this.getUsuarioDao().update(usuarioEntity);
			this.getUsuarioDao().remove(usuarioEntity);

		} catch (Exception e)
		{
			log.error("se produce una excepcion ", e);
		}
	}

	/**
	 * Modifica los datos del usuario una vez que se ha solicitado la
	 * modificacion
	 * @param usuario usuarioVO con los datos del usuario
	 */
	private void modificarUserTransaccion(UsuarioVO usuario)
	{
		try
		{

			Usuario usuarioEntityModificar = this.getUsuarioDao().load(usuario.getId());
			this.getUsuarioDao().fromUsuarioVO(usuario, usuarioEntityModificar);
			this.getUsuarioDao().update(usuarioEntityModificar);
		} catch (Exception e)
		{
			log.error("se produce una excepcion ", e);
		}

	}


	/**
	 * Convierte el string que se pasa como parametro, que es el password, a
	 * un hash y se devuelve el resultado
	 * @param password String que contiene el password del usuario
	 * @return String String
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	private String getHashPassword(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException
	{

		byte[] theTextToDigestAsBytes = password.getBytes("8859_1" /* encoding */);
		MessageDigest md = MessageDigest.getInstance("SHA");
		md.update(theTextToDigestAsBytes);
		byte[] digest = md.digest();
		String sha = "{SHA}";
		return sha + String.copyValueOf(Base64Coder.encode(digest));
	}

	/**
	 * Recoge la traducción de un literal del archivo de traducciones application-resources. 
	 * El idioma en el que se traducirá será el de por defecto de la plataforma 
	 * @param key codigo del literal
	 * @return String Traducción del literal
	 */
	private static String getResource(String key){

        String recurso = "";
       
        try
        {
        	String idiomaNavegacion = I18n.getInstance().obtenerIdiomaDefectoPlataforma();
        	if(log.isDebugEnabled())log.debug("idiomaNavegacion "+idiomaNavegacion);
        	Locale localeNavegacion = new Locale(idiomaNavegacion);
        	
        	ResourceBundle ficheroRecursos = null;
    		ficheroRecursos = ResourceBundle.getBundle("application-resources", localeNavegacion);
        	if(ficheroRecursos == null)
        	{
        		ficheroRecursos = ResourceBundle.getBundle("application-resources",new Locale("es"));
        		if(log.isDebugEnabled())log.debug("application resources por defecto");
        	}else
        	{
        		recurso = ficheroRecursos.getString(key);
        		
        	}

        }catch (MissingResourceException mre){

                  recurso = key;

        }catch (Exception e)
        {
        	recurso = key;
        }

        return recurso;

    }
	
	/**
	 * Recoge la traducción de un literal del archivo de traducciones application-resources. 
	 * El idioma en el que se traducirá será el que se pase por parámetro 
	 * @param key código del literal
	 * @param idioma idioma de traducción
	 * @return String Traducción del literal
	 */
//	private static String getResource(String key,String idioma){
//
//        String recurso = "";
//       
//        try
//        {
//        	Locale localeNavegacion = new Locale(idioma);
//        	
//        	ResourceBundle ficheroRecursos = null;
//    		ficheroRecursos = ResourceBundle.getBundle("application-resources", localeNavegacion);
//        	if(ficheroRecursos == null)
//        	{
//        		ficheroRecursos = ResourceBundle.getBundle("application-resources",new Locale("es"));
//        		if(log.isDebugEnabled())log.debug("application resources por defecto");
//        	}else
//        	{
//        		recurso = ficheroRecursos.getString(key);
//        		
//        	}
//
//        }catch (MissingResourceException mre){
//
//                  recurso = key;
//
//        }catch (Exception e)
//        {
//        	recurso = key;
//        }
//
//        return recurso;
//
//    }
	

	/**
	 * Comprueba si alguno de los grupos del usuario es el grupo "Todos" 
	 * @param gruposTrabajo array con todos los grupos de trabajo a los que pertenece el usuario
	 * @return Boolean devolverá true si alguno de los grupos de trabajo que se pasan como parámetro es el grupo Todos
	 */
	private Boolean estaGrupoTrabajoTodos(GrupoTrabajoVO[] gruposTrabajo)
	{
		if(log.isDebugEnabled())log.debug("estaGrupoTrabajoTodos");
		Boolean resultado = false;
		try
		{
			for(int i=0;i<gruposTrabajo.length;i++)
			{
			
				if(log.isDebugEnabled())log.debug("gruposTrabajo[i].getNombre() "+gruposTrabajo[i].getNombre());
				if(gruposTrabajo[i].getNombre().equalsIgnoreCase(this.getAdminUsuariosPropertyValue("grupoTrabajoTodos")))
				{
					resultado = true;
					break;
				}
			}
		}catch(Exception e)
		{
			log.error("Error al leer del fichero adminusuarios.properties "+e);
			
		}
		return resultado;
	}
	
	/**
	 * Asiganamos un manejador de LDAP
	 * @return ldapContact ldapContactDAO
	 * @throws Exception
	 */
	private LDAPContactDAO getLdapHandler() throws Exception
	{
		LDAPContactDAO ldapContact = null;
		try
		{
			Resource resource = new ClassPathResource("springldap.xml");
			if(log.isDebugEnabled())log.debug("Despues de obtener el resource " + resource.getFilename());
			BeanFactory factory = new XmlBeanFactory(resource);
			if(log.isDebugEnabled())log.debug("Despues de obtener el factory " + factory);
			ldapContact = (LDAPContactDAO) factory.getBean("ldapContact");
			if(log.isDebugEnabled())log.debug("Despues de obtener el bean ldapContact " + ldapContact);
			return ldapContact;
		} catch (Exception e)
		{
			log.error("Se produce la siguiente excepcion " + e);
			throw e;
		}
	}

	/**
	 * Asiganamos un manejador de LDAP
	 * @return ldapContact ldapContactDAO
	 * @throws Exception
	 */
	private LDAPContactDAO getLdapWebSemanticaHandler() throws Exception
	{
		LDAPContactDAO ldapContact = null;
		try
		{
			Resource resource = new ClassPathResource("springldap.xml");
			if(log.isDebugEnabled())log.debug("Despues de obtener el resource " + resource.getFilename());
			BeanFactory factory = new XmlBeanFactory(resource);
			if(log.isDebugEnabled())log.debug("Despues de obtener el factory " + factory);
			ldapContact = (LDAPContactDAO) factory.getBean("ldapContactProcomun");
			if(log.isDebugEnabled())log.debug("Despues de obtener el bean ldapContact " + ldapContact);
			return ldapContact;
		} catch (Exception e)
		{
			log.error("Se produce la siguiente excepcion " + e);
			throw e;
		}
	}

	

	

	/**
	 * Método para obtener una nueva clave para el usuario Estará formada
	 * por números y letras entre 7 y 10 caracteres.
	 * @return clave String que contiene la clave del usuario
	 */
	private String getPassword()
	{
		String clave="";
		do {
			Random random = new Random();
			int max_caracs = 8 + random.nextInt(4);
			StringBuilder claveTemp = new StringBuilder();
			for (int i = 0; i < max_caracs; i++)
			{
				int esLetra = random.nextInt(2);
				if (esLetra == 1)
				{
					claveTemp.append(caracteres.charAt(random.nextInt(caracteres.length())));
				} else
				{
					claveTemp.append(random.nextInt(10));
				}
			}
			clave=claveTemp.toString();
		} while (ChequeoClave.chequea(clave)!=0);

		return clave;
	}
	
	
	/**
	 * Obtiene los usuarios que pertenecen a un grupo que se identifica con
	 * el id que se pasa como parametro
	 * @param id identificador del grupo
	 * @return resultado Boolean que indica si existen o no usuarios en el
	 *         grupo especificado
	 * @throws Exception
	 */
	public Boolean handleObtenerUsuariosGrupo(Long id) throws Exception
	{
		try
		{
			Boolean resultado = Boolean.FALSE;
			List usuarios = this.getUsuarioDao().obtenerNumUsuariosGrupo(id);
			int numUsuarios = usuarios.size();
			if (numUsuarios > 0)
			{
				resultado = Boolean.TRUE;
			}
			return resultado;
		} catch (Exception e)
		{
			throw e;
		}
	}
	
	/**
	 * Método que comprueba si existen usuarios cuyo grupo de trabajo sea el mismo que el del usuario cuyo identificador
	 * se pasa como parámetro
	 * @param id Long identificador del usuario
	 * @return Boolean indica si existen o no más usuarios con el mismo grupo de trabajo
	 * @throws Exception
	 */
	public Boolean handleObtenerUsuariosGrupoTrabajo(Long id) throws Exception
	{
		try
		{
			Boolean resultado = Boolean.FALSE;
			List usuarios = this.getUsuarioDao().obtenerUsuariosPertenecenGrupoTrabajo(id);
			int numUsuarios = usuarios.size();
			if (numUsuarios > 0)
			{
				resultado = Boolean.TRUE;
			}
			return resultado;
		} catch (Exception e)
		{
			throw e;
		}
	}
	
	/**
	 * Recoge un literal del archivo properties adminusuarios.properties referenciando el codigo de
	 * dicho literal
	 * @param sKey codigo del literal
	 * @return String String con el literal
	 * @throws IOException
	 */
	private String getAdminUsuariosPropertyValue(String sKey) throws IOException
	{
		InputStream fIsSpringProperties = this.getClass().getResourceAsStream("/adminusuarios.properties");
		if (this.pAdminUsuariosProperties == null)
		{
			pAdminUsuariosProperties = new java.util.Properties();
			pAdminUsuariosProperties.load(fIsSpringProperties);
		}
		fIsSpringProperties.close();

		// devolvemos la propiedad
		return pAdminUsuariosProperties.getProperty(sKey);
	}
	
	
	/**
     * Comprueba si el nombre que se le pasa por parámetro esta
     * asociado a otro grupo de trabajo
     * @param nombre descripcion del grupo
     * @param id identificador del grupo
     * @return retorno Bolean indica si existe un grupo de trabajo en el sistema con ese nombre de grupo
     * @throws Exception
     */

	protected java.lang.Boolean handleExisteNombreTrabajo(java.lang.String nombre, java.lang.Long id) throws java.lang.Exception
	{
		Boolean retorno = false;
		GrupoTrabajoVO[] grupoTrabajoVO = null;
		
		if(log.isDebugEnabled())log.debug("Estoy antes de recoger los grupos de usuarios");
		
		Collection gruposTrabajo = this.getGrupoTrabajoDao().loadAll(GrupoTrabajoDao.TRANSFORM_GRUPOTRABAJOVO);
		grupoTrabajoVO = (GrupoTrabajoVO[]) gruposTrabajo.toArray(new GrupoTrabajoVO[0]);
		
		if(log.isDebugEnabled())log.debug("Estoy antes de comparar si el id es -1 (para alta) o no (para modificar)");
		
		if (id.compareTo(new Long("-1")) == 0)
		{

			if(log.isDebugEnabled())log.debug("Alta grupo trabajo - la descripcion no puede coincidir");
			for (int j = 0; j < grupoTrabajoVO.length; j++)
			{
				if (grupoTrabajoVO[j].getNombre().equalsIgnoreCase(nombre))
				{
					retorno = Boolean.TRUE;
				}
			}
		} 
		else
		{
			
			if(log.isDebugEnabled())log.debug("Modificar grupo trabajo - ni descripcion ni el id pueden coincidir");
			for (int j = 0; j < grupoTrabajoVO.length; j++)
			{
				if (grupoTrabajoVO[j].getNombre().equalsIgnoreCase(nombre))
				{
					if (grupoTrabajoVO[j].getIdentificador().compareTo(id) != 0)
					{
						retorno = Boolean.TRUE;
					}
				}
			}
		}
		if(log.isDebugEnabled())log.debug("Estoy antes de return");
		return retorno;
	}

	/**
	 * Obtiene los usuarios pasandole el openId
	 * @param openIdUrl el identificador del openId
	 * @return UsuarioVO el usuario que tiene ese openIdUrl
	 * @throws Exception
	 */
	protected UsuarioVO handleObtenerUsuarioConOpenId(java.lang.String openIdUrl) throws Exception {
		UsuarioVO usuari=new UsuarioVO();
		try{
			log.debug("handleObtenerUsuarioConOpenId urlOpenId "+openIdUrl);
			// Si se utiliza el OpenId de Yahoo, al identificador que genera del tipo https://me.yahoo.com/a/IEMxZkRwivKasjYBKoyU7uTmS2iLhCBgisFV le añade
			// al final un #2341c que impide que lo encuentre como Identificador de OpenId del usuario en BD.
			// Por eso si es de yahoo, eliminamos esos caracteres extra
			// 26092012 Corrección para OpenId de Yahoo porque al modificar perfil no permitía meter ids sin # por el substring
			// sólo quitamos la parte final si tiene #, sino es que viene de modificar perfil.
			if ((openIdUrl.contains("me.yahoo")) && (openIdUrl.contains("#")))
				openIdUrl = openIdUrl.substring(0,openIdUrl.indexOf("#"));
			
			log.debug("handleObtenerUsuarioConOpenId buscando por: "+openIdUrl);
			
			UsuarioDao usuario = this.getUsuarioDao();
			ObtenerUsuarioPorOpenIdCriteria criteria=new ObtenerUsuarioPorOpenIdCriteria();
			criteria.setOpenIdUrl(openIdUrl);
			List<UsuarioVO> usuarios=usuario.obtenerUsuariosPorOpenId(UsuarioDao.TRANSFORM_USUARIOVO, criteria);
			
			if(usuarios!=null && usuarios.size()>0){
				usuari=usuarios.get(0);
			}else{
				usuari=null;
			}
		}catch(Exception e){
			log.error("Error al obtener los usuarios con el openId");
			throw e;
		}
		
		return usuari;
	}

	/**
	 * Obtiene el grupo cuyo único rol sea el que se pasa como parámetro 
	 * @param rol nombre del rol que tiene que tener el grupo
	 * @return GrupoVO grupo cuyo único rol es el que se para por parámetro
	 * @throws Exception
	 */
	protected GrupoVO handleObtenerGrupoRol(String rol) throws Exception
	{
		Long identificador = null;
		GrupoVO grupo = null;
		GrupoVO resultado = null;
		List lista = this.getGrupoDao().obtenerGruposAdministradores(rol);
		log.debug("El número de grupos con rol "+rol +" es "+lista.size());
		
		//comprobamos si los grupos obtenidos tienen un unico rol
		if(!(lista == null)&& !(lista.size() == 0))
		{
			//comprobamos si esos grupos tienen un unico rol, en ese caso se devolverá el grupo
			log.debug("Existe un grupo con ese rol "+rol);
			//Recorremos la lista de identificadores para buscar el grupo cuyo único rol sea el que se pasa 
			//como parámetro al método
			Iterator iter = lista.iterator();
			while (iter.hasNext())
			{
				identificador = (Long)iter.next();
				grupo = (GrupoVO)this.getGrupoDao().load(GrupoDao.TRANSFORM_GRUPOVO, identificador);
				if(grupo.getRols().length == 1)
				{
					log.debug("El grupo "+grupo.getDescripcion()+" tiene como único rol "+(grupo.getRols()[0]).getDescripcion());
					resultado = grupo;
					break;
				}
			}
			if(resultado == null)
			{
				log.debug("Todos los grupos tiene mas de un rol");
				//creamos un grupo con un único rol, el que se pasa como parámetro
				try
				{
					log.error("No existe ningún grupo con ese rol, lo creo "+rol);
					grupo = new GrupoVO();
					grupo.setDescripcion("Grupo_"+rol);
					
					//Recorremos todos los roles dados de alta en la plataforma para recoger el DOCENTE
					Collection rolesVO = this.getRolDao().loadAll(RolDao.TRANSFORM_ROLVO);
					Iterator iterRoles = rolesVO.iterator();
					log.debug("iterRoles "+iterRoles);
					////String rolDocente = Agrega PropertiesImpl.getInstance().getProperty(AgregaProperties.ROL_EMPAQUETADOR);
					while (iterRoles.hasNext())
					{
						RolVO role = (RolVO)iterRoles.next();
						log.debug("role "+role);
						String descripcion = role.getDescripcion();
						log.debug("role.getDescripcion() "+descripcion);
						log.debug("rol_empaquetador en agrega.properties "+rol);
						if(descripcion.equalsIgnoreCase(rol))
						{
							
							grupo.setRols(new RolVO[] { role });
							break;
						}
					}
					log.debug("(grupo.getRols()[0]).getDescripcion() "+(grupo.getRols()[0]).getDescripcion());
					log.debug("Se ha creado un nuevo grupo descripcion "+grupo.getDescripcion()+" e identificador "+grupo.getId());
					
					
					
					Grupo grupoEntity = this.getGrupoDao().fromGrupoVO(grupo);
					Grupo grupoCreado = this.getGrupoDao().create(grupoEntity);
					log.debug("creado un nuevo grupo con identificador "+grupoCreado.getId()+" y descripcion "+grupoCreado.getDescripcion());
					resultado = grupo;
					
				} catch (Exception e)
				{
					log.error("Se ha producido la siguiente excepcion " + e);
					AltaGrupoException exception = new AltaGrupoException("No se ha podido dar de alta el grupo", e);
					//throw exception;
				}
			}
			
		}else
		{
			log.debug("No existe grupos con ese rol los creo ");
			try
			{
				log.error("No existe ningún grupo con ese rol, lo creo");
				grupo = new GrupoVO();
				grupo.setDescripcion("Grupo_"+rol);
				
				//Recorremos todos los roles dados de alta en la plataforma para recoger el DOCENTE
				Collection rolesVO = this.getRolDao().loadAll(RolDao.TRANSFORM_ROLVO);
				Iterator iter = rolesVO.iterator();
				////String rolDocente = Agrega PropertiesImpl.getInstance().getProperty(AgregaProperties.ROL_EMPAQUETADOR);
				while (iter.hasNext())
				{
					RolVO role = (RolVO)iter.next();
					String descripcion = role.getDescripcion();
					log.debug("role.getDescripcion() "+descripcion);
					log.debug("rol_empaquetador en agrega.properties "+rol);
					if(descripcion.equalsIgnoreCase(rol))
					{
						
						grupo.setRols(new RolVO[] { role });
						break;
					}
				}
				log.debug("(grupo.getRols()[0]).getDescripcion() "+(grupo.getRols()[0]).getDescripcion());
				log.debug("Se ha creado un nuevo grupo descripcion "+grupo.getDescripcion()+" e identificador "+grupo.getId());
				
				
				
				Grupo grupoEntity = this.getGrupoDao().fromGrupoVO(grupo);
				Grupo grupoCreado = this.getGrupoDao().create(grupoEntity);
				log.debug("creado un nuevo grupo con identificador "+grupoCreado.getId()+" y descripcion "+grupoCreado.getDescripcion());
				resultado = grupo;
				
			} catch (Exception e)
			{
				log.error("Se ha producido la siguiente excepcion " + e);
				AltaGrupoException exception = new AltaGrupoException("No se ha podido dar de alta el grupo", e);
				//throw exception;
			}
		}
				
		return resultado;
		
	}

	/**
	 * Registra en base de datos al usuario que se le pasa por parámetro 
	 * @param usuario usuario que se quiere dar de alta en la base de datos
	 * @return String[] con el resultado de la inserción en la base de datos
	 * @throws Exception
	 */
	protected String[] handleRegistrarIntegracionLdap(UsuarioVO usuario) throws Exception
	{
		String[] resultado = null;
		UsuarioVO usuarioExistente = null;
		Boolean existeUsuario = false;
		Usuario usuarioEntity = null;
		String login = null;
		Usuario usuarioCreado = null;
//		String clave = null;
		GrupoVO grupo = null;
		GrupoTrabajoVO[] gruposTrabajo = null;
		String nif = null;
		List roles = null;
		
		//Transformaciones a realizar en el usuario que recibimos de soporte
		
		login = (usuario.getUsuario()).toLowerCase();
		usuario.setUsuario(login);
		//EN la BD no se guardará la clave
//		clave = usuario.getClave();
		usuario.setClave("");
		//Transformamos el nif ya que siempre se tiene que guardar en mayusculas
		nif = (usuario.getNIF()).toUpperCase();
		usuario.setNIF(nif);
		//Obtenemos el grupo que se le va a asignar al usuario que se va a dar de alta
		if(log.isDebugEnabled())log.debug("Se va a obtener el grupo DOCENTE");
		grupo = this.obtenerGrupoRol(AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.ROL_EMPAQUETADOR));
		if(log.isDebugEnabled())log.debug("Se obtiene el grupo cuyo unico rol sea el de docente el identificador "+grupo.getId()+" descripcion "+grupo.getDescripcion());
		usuario.setGrupos(new GrupoVO[] { grupo });
		//Comprobamos el idioma, idiomabusqueda, tipo_catalogador, tipo_empaquetador y la cuota
		if(usuario.getIdioma() == null)
		{
			usuario.setIdioma("es");
		}
		if(usuario.getIdiomaBusqueda() == null)
		{
			usuario.setIdiomaBusqueda("es");
		}
		if(usuario.getTipoCatalogador() == null)
		{
			usuario.setTipoCatalogador("basico");
		}
		if(usuario.getTipoEmpaquetador() == null)
		{
			usuario.setTipoEmpaquetador("basico");
		}
		if(usuario.getCuota() == null)
		{
			usuario.setCuota((new Long((ObtieneSrvPropiedad().get (AgregaProperties.VALOR_CUOTA_DEFECTO)))* 1024 * 1024));
			
		}
		//Comprobamos si tiene fecha de alta en el caso de que no lo tenga se le asigna la fecha actual
		if(usuario.getFechaAlta() == null)
		{
			usuario.setFechaAlta(new Date(System.currentTimeMillis()));
		}
		//Comprobamos si ya existe un usuario con el mismo nif dado de alta en la plataforma
		if(log.isDebugEnabled())log.debug("Comprobamos si existe otro usuario con ese mismo nif ");
		usuarioExistente = this.obtenerUsuario(usuario.getNIF().toUpperCase());
		if(log.isDebugEnabled())log.debug("existe otro usuario con el mismo nif "+usuarioExistente);
		if (usuarioExistente == null) 
		{
			if(log.isDebugEnabled())log.debug("No existe otro usuario en BD con ese mismo nif");
			existeUsuario = this.existeUsuario(usuario.getUsuario());
			if (existeUsuario.booleanValue()) 
			{
				if(log.isDebugEnabled())log.debug("Existe otro usuario en BD con ese mismo login");
				
				
			}else
			{
				//Asignamos un grupo de trabajo al usuario, por tener rol docente tiene que tener uno
				//por defecto le vamos a asignar el grupo de trabajo "todos"
				gruposTrabajo = this.handleListarGruposTrabajo();
				usuario.setGrupoTrabajo(new GrupoTrabajoVO[] { gruposTrabajo[0] });
				if(log.isDebugEnabled())log.debug("Se asigna el primer grupo de trabajo al usuario");
				//Damos de alta ese usuario en BD
				//this.getBeanMapper().map(usuario, usuarioEntity, "MAPEO_USUARIOVO_USUARIO");
				usuarioEntity = this.getUsuarioDao().fromUsuarioVO(usuario);
			    usuarioCreado = this.getUsuarioDao().create(usuarioEntity);
			    if(log.isDebugEnabled())log.debug("Se ha creado el nuevo usuario en BD");
			    roles = this.getUsuarioDao().consultaHQL(usuarioCreado.getUsuario().toLowerCase());
			    resultado = (String[]) roles.toArray(new String[0]);
			    			    
			}
		}else
		{
			if (usuarioExistente.getFechaBaja() == null) 
			{
				if(log.isDebugEnabled())log.debug("Existe otro usuario en BD con ese mismo nif");
				
			}else
			{
				//Comprobamos si el login es el mismo
				if(log.isDebugEnabled())log.debug("usuarioExistente.getUsuario() "+usuarioExistente.getUsuario());
				if(log.isDebugEnabled())log.debug("usuario.getUsuario() "+usuario.getUsuario());
				if(!(usuarioExistente.getUsuario().equalsIgnoreCase(usuario.getUsuario())))
				{
					if(log.isDebugEnabled())log.debug("Existe otro usuario en BD con ese mismo nif pero con otro login");
				}else
				{
				//se modificara el usuario de bd
				if(log.isDebugEnabled())log.debug("Modificamos un usuario ya registrado en el sistema");
				usuarioEntity = this.getUsuarioDao().load(usuarioExistente.getId());
				usuario.setId(usuarioExistente.getId());
				//Actualizamos los atributos del usuario de BD
				this.getUsuarioDao().fromUsuarioVO(usuario, usuarioEntity);
				this.getUsuarioDao().update(usuarioEntity);
				if(log.isDebugEnabled())log.debug("Hemos modificado el usuario en BD");
				roles = this.getUsuarioDao().consultaHQL((usuarioEntity.getUsuario()).toLowerCase());
				resultado = (String[]) roles.toArray(new String[0]);
				}
				
			}
		}
		
		return resultado;
	}
	
	/**
	 * Compruebo si el usuario (login) que se le pasa por parámetro esta
	 * activo en la plataforma
	 * @param usuario login del usuario
	 * @return resultado Boolean que inidica si esta activo
	 * o no.
	 * @throws Exception
	 */
	protected Boolean handleEstaActivo(String usuario) throws Exception
	{
		Boolean resultado = Boolean.TRUE;
		Usuario usuarioEntity = this.getUsuarioDao().obtenerDatosUsuario(usuario.toLowerCase());

		if (usuarioEntity == null)
		{
			resultado = Boolean.FALSE;
		}else if (!(usuarioEntity.getFechaBaja() == null))
		{
			resultado = Boolean.FALSE;
		}

		return resultado;
	}

	/**
	 * Este metodo realiza una búsqueda de todos los usuarios activos que coincidan con el criterio establecido
	 * @param usuario String que corresponde al usuario o usuarios que se quieren buscar por el que se desea buscar
	 * @return UsuarioActivoVO[] retorna un array con los usuarios encontrados
	 * @throws Exception
	 */
	protected UsuarioVO[] handleObtenerUsuariosActivosPorNombre(String usuario) throws Exception {
		UsuariosActivosPorNombreCriteria activosPorNombreCriteria = new UsuariosActivosPorNombreCriteria();
		UsuarioVO[] usuarios=null;
		try {
			if (usuario.trim().length()>0 && usuario!=null){
				StringBuilder busqueda = new StringBuilder();
//				usuario = this.concatenarBusqueda(usuario);
				log.info("El filtro de búsqueda es: " + usuario);
				busqueda.append(usuario.trim()).append("%");
//				activosPorNombreCriteria.setNombreUsuario(usuario.trim());
				activosPorNombreCriteria.setNombreUsuario(busqueda.toString());
				//List result = this.getUsuarioDao().buscarUsuariosActivosPorNombre(this.getUsuarioDao().TRANSFORM_USUARIOVO,activosPorNombreCriteria);
				List result = this.getUsuarioDao().buscarUsuariosActivosPorNombre(activosPorNombreCriteria);
				log.info("Número de usuarios obtenidos " + result.size());
				//Transformamos los entities en VO
				ArrayList usuariosVO = new ArrayList();
				UsuarioVO usuarioVO = null;
				Iterator iter = result.iterator();
				while(iter.hasNext())
				{
					
					usuarioVO = (es.pode.adminusuarios.negocio.servicios.UsuarioVO)this.getBeanMapper().map(iter.next(),es.pode.adminusuarios.negocio.servicios.UsuarioVO.class, "MAPEO_USUARIO_USUARIOVO");
					log.debug("usuarioVO "+usuarioVO);
					usuariosVO.add(usuarioVO);
				}
				usuarios = (UsuarioVO[]) usuariosVO.toArray(new UsuarioVO[result.size()]);
			}
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
		return usuarios;
	}
	
	/**
	 * Este método concantena la cadena que se le pasa, añadiendole antes y despues un %, 
	 * Ej. %texto% 
	 * @param texto
	 * @return String
	 * @throws Exception
	 */
	private String concatenarBusqueda (String texto) throws Exception{
		try {
			log.debug("titulo" + texto);
			StringBuffer busqueda = new StringBuffer("%");
			StringTokenizer token = new StringTokenizer(texto, " ");
			while ((token.hasMoreElements())){
				busqueda.append(token.nextElement().toString()).append("%");
			}
			log.info("La busqueda se realizará por los siguientes criterios: " + busqueda.toString());
			return busqueda.toString();
		} catch (Exception e) {
			log.error(e);
			throw e;
		}
}

	/**
	 * Este método devuelve información necesaria para manda los correos o no de información de publicación, rechazo o comentario de un ode del usuario dado 
	 * @param usuario
	 * @return UsuarioCorreoVO Datos necesarios para informar al usuario o no
	 * @throws Exception
	 */
	protected UsuarioCorreoVO handleObtenerUsuarioCorreo(String usuario)
			throws Exception {
		UsuarioCorreoVO usuarioCorreoVO=new UsuarioCorreoVO();
		try{
			
			Usuario usuarioEntity = this.getUsuarioDao().obtenerDatosUsuario(usuario.toLowerCase());
			String email=usuarioEntity.getEmail();
			Boolean correo=usuarioEntity.getRecibirCorreoPublicacion();
			usuarioCorreoVO.setEmail(email);
			usuarioCorreoVO.setRecibeCorreo(correo);
			usuarioCorreoVO.setUsuario(usuario);
		}catch (Exception e) {
			log.error("Error al obtener todos los datos de envio de correo para el usuario "+usuario);
			throw new Exception("Error al obtener todos los datos de envio de correo para el usuario "+usuario,e);
		}
		return usuarioCorreoVO;
	}

	/**
	 * Este método devuelve los correos de todos aquellos usuarios que desean recibirlos 
	 * @param usuarios
	 * @return UsuarioCorreoVO[] Array con los datos del usuario al que se le va a enviar el correo
	 * @throws Exception
	 */
	protected UsuarioCorreoVO[] handleObtenerUsuariosConCorreo(String[] usuarios) throws Exception
	{
		UsuarioCorreoVO[] usuariosCorreoVO=null;
		List listaUsuarios = new ArrayList();
		Iterator iter = null;
		for(int i=0;i<usuarios.length;i++)
		{
		try{
			
			Usuario usuarioEntity = this.getUsuarioDao().obtenerDatosUsuario((usuarios[i]).toLowerCase());
			if((usuarioEntity.getFechaBaja() == null)&&(usuarioEntity.getRecibirCorreoPublicacion()))
			{
				listaUsuarios.add(usuarioEntity);
			}
			
		}catch (Exception e) {
			log.error("Error al obtener todos los datos de envio de correo para el usuario "+usuarios[i]);
			//throw new Exception("Error al obtener todos los datos de envio de correo para el usuario "+usuarios[i],e);
		}
		}
		//Obtenemos el array con los UsuarioCorreoVO
		iter = listaUsuarios.iterator();
		usuariosCorreoVO = new UsuarioCorreoVO[listaUsuarios.size()];
		int j = 0;
		while (iter.hasNext())
		{
			usuariosCorreoVO[j] = new  UsuarioCorreoVO();
			Usuario usuario = (Usuario)iter.next();
			usuariosCorreoVO[j].setEmail(usuario.getEmail());
			usuariosCorreoVO[j].setRecibeCorreo(usuario.getRecibirCorreoPublicacion());
			usuariosCorreoVO[j].setUsuario(usuario.getUsuario());
			j++;
		}
		log.debug("Array con los usuarios que reciben correo "+Arrays.toString(usuariosCorreoVO));
		return usuariosCorreoVO;
	}

	
	private SrvPropiedadService ObtieneSrvPropiedad() throws Exception {
		
		if (this.prop==null)
		{
			prop = this.getSrvPropiedadService();
		}
		return prop;
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
	
	
	private ResultadoOperacionWebSemanticaVO comprobarIntegridadDatosBasicosUsuarioProComun (UsuarioVO usuario, boolean comprobarPasswd)
			{

		ResultadoOperacionWebSemanticaVO r = new ResultadoOperacionWebSemanticaVO();
		r.setMensaje("");
		
		//Revisamos que recibimos los datos necesarios para crear el usuario de web semantica
		if(usuario==null) {
			r.setMensaje("No se ha recibido un usuario que dar de alta/modificar en la web semantica");
			r.setCodigoResultado(ERROR_PARAMETROS_WS);
			return r;
		}
		
		//Validacion para nombre y apellidos
	    Pattern mask = Pattern.compile("[^\\¿\\?\\·\\!\\>\\#\\&\\<\\@\\$\\/\\\'\\\"]+");
	    //Validacion para password
	    Pattern maskPwd = Pattern.compile("[^Ññ\\¿\\?\\·\\!\\>\\#\\&\\<\\@\\$\\/\\\'\\\"]+");

		//Cargamos los campos del usuario que podemos recibir
	    String nombre = usuario.getNombre();
	    String apellido1 = usuario.getApellido1();
	    String apellido2 = usuario.getApellido2();
	    String nif = usuario.getNIF();
	    String passwd = usuario.getClave();

	    //Nombre
		if(usuario.getNombre()==null || usuario.getNombre().isEmpty()) {
			r.setMensaje("El campo nombre no es valido");
			r.setCodigoResultado(ERROR_PARAMETROS_WS);
			return r;
		}
		Matcher matcher = mask.matcher(nombre);
		if (!matcher.matches()) {
			r.setMensaje("Caracter ilegal en el campo nombre");
			r.setCodigoResultado(ERROR_PARAMETROS_WS);
			return r;
		}			
		//Apellido1
		if(apellido1==null || apellido1.isEmpty()) {
			r.setMensaje("El campo apellido1 no es valido");
			r.setCodigoResultado(ERROR_PARAMETROS_WS);
			return r;
		}
		matcher = mask.matcher(apellido1);
		if (!matcher.matches()) {
			r.setMensaje("Caracter ilegal en el campo apellido1");
			r.setCodigoResultado(ERROR_PARAMETROS_WS);
			return r;
		}			
		//Apellido2
		if(apellido2!=null && !apellido2.isEmpty()) {
			matcher = mask.matcher(apellido2);
			if (!matcher.matches()) {
				r.setMensaje("Caracter ilegal en el campo apellido2");
				r.setCodigoResultado(ERROR_PARAMETROS_WS);
				return r;
			}
		}						
		//NIF
		/*
		if(nif==null || nif.isEmpty()) {
			throw new Exception("El campo nif no es valido");
		}
		*/		
	    //Passwd
		if(comprobarPasswd) {
			if(passwd==null || passwd.isEmpty()) {
				r.setMensaje("El campo password no es valido");
				r.setCodigoResultado(ERROR_PARAMETROS_WS);
				return r;
			}
		    matcher = maskPwd.matcher(passwd);
		    if (!matcher.matches()) {
				r.setMensaje("Caracter ilegal en el campo password");
				r.setCodigoResultado(ERROR_PARAMETROS_WS);
				return r;
		    }
		}
		//Mail
		if(!esUnMail(usuario.getEmail())) {
			r.setMensaje("El campo email no es valido");
			r.setCodigoResultado(ERROR_PARAMETROS_WS);
			return r;
		}
		r.setCodigoResultado(OK_WS);
		return r;
	}
	
	
	/**
	 * Metodo para dar de alta un usuario de Procomun. Se le agregará en el servidor
	 * ldap de Agrega y en el de Web Semantica y se enviará un correo con el usuario y la clave
	 * El usuario se creara desactivado.
	 * @param usuario usuarioVO con los datos del usuario que se dara de alta
	 * @return resultado String que devuelve si el alta ha ido bien o no
	 * @throws Exception
	 */	
	@Override
	protected ResultadoOperacionWebSemanticaVO handleAltaUsuarioProComun(UsuarioVO usuario)
			{

		ResultadoOperacionWebSemanticaVO checkDatos=null;
		ResultadoOperacionWebSemanticaVO r = new ResultadoOperacionWebSemanticaVO();
		r.setMensaje("");
		Long retorno = null;
		String nombreGrupoWebSemantica="UsuariosWebSemantica";
		
		try {
		    // Validamos los campos que informan desde Web Semántica (nombre, apellidos, nif y password)
			checkDatos=comprobarIntegridadDatosBasicosUsuarioProComun(usuario, true);
			if(checkDatos==null || !checkDatos.getCodigoResultado().contentEquals(OK_WS))
				return checkDatos;
		    
			// Pasamos a mayúsculas para que todos los docmentos de identidad se inserten en mayúsculas en bd
		    if (usuario.getNIF()!=null)
		    	usuario.setNIF(usuario.getNIF().toUpperCase());
			
		    // Pasamos a minúsculas para que todos los correos se inserten en minúsculas en bd
		    if (usuario.getEmail()!=null)
		    	usuario.setEmail(usuario.getEmail().toLowerCase());
		    
			//Cargamos los campos del usuario que podemos recibir
		    String nombre = usuario.getNombre();
		    String apellido1 = usuario.getApellido1();
		    String apellido2 = usuario.getApellido2();
		    String nif;
		    if (usuario.getNIF()!=null && !usuario.getNIF().isEmpty())
		    	nif = usuario.getNIF();
		    else 
		    	nif = "WS"+System.currentTimeMillis();
		    Boolean recibirCorreoPublicacion = usuario.getRecibirCorreoPublicacion();
		    //Campos de usuario que ajustamos automaticamente independientemente
		    //de lo que recibamos
			String user=usuario.getUsuario();
		    String openIdUrl = "";
		    String idiomaBusqueda="es"; //Puede ser; ca, en, eu, gl, va o fr
		    String idioma="es";
		    String tipoEmpaquetador="basico"; //Puede ser basico o avanzado
		    String tipoCatalogador="basico";
		    String tipoVisualizador="agrega"; //Puede ser agrega o adl
		    //Long cuota = Long.valueOf(5).longValue() * 1024 * 1024; //5MB por defecto
			
			//RecibirCorreo
		    if (recibirCorreoPublicacion == null) {
		    	recibirCorreoPublicacion=false;
		    }
    	    
		    //Cargo las modificaciones y los ajustes de los campos en el usuario a crear 
		    usuario.setNombre(nombre);
		    usuario.setApellido1(apellido1);
		    usuario.setApellido2(apellido2);
		    usuario.setNIF(nif);
		    usuario.setRecibirCorreoPublicacion(recibirCorreoPublicacion);
		    usuario.setUsuario(user);
		    usuario.setOpenIdUrl(openIdUrl);
		    usuario.setIdiomaBusqueda(idiomaBusqueda);
		    usuario.setIdioma(idioma);
		    usuario.setTipoEmpaquetador(tipoEmpaquetador);
		    usuario.setTipoCatalogador(tipoCatalogador);
		    usuario.setTipoVisualizador(tipoVisualizador);
		    //usuario.setCuota(cuota);    
		    usuario.setFechaAlta(new java.util.Date()); 
		    usuario.setFechaBaja(null);
		    //usuario.setFechaDesactivacion(null);
		    usuario.setFechaDesactivacion(new java.util.Date());
		    
		    //Asignamos grupo
		    Boolean grupoWebSemanticaEncontrado=false;
		    
		    //Buscamos el grupo UsuariosWebSemantica
		   
		    GrupoVO[] grupos;
		    
			try {
				grupos = handleListarGrupos();
			    for(int i=0; i<grupos.length; i++) {
			    	
			    	if(grupos[i].getDescripcion().equalsIgnoreCase(nombreGrupoWebSemantica)) {
			    	
			    		GrupoVO[] grupoWebSemantica=new GrupoVO[1];
			    		grupoWebSemantica[0]=grupos[i];
			    		usuario.setGrupos(grupoWebSemantica);
			    		grupoWebSemanticaEncontrado=true;
			    		break;
			    	}
			    }
			} catch (Exception ex) {
				
				log.error("Error al obtener los grupos ", ex);
				r.setMensaje("Error al obtener los grupos");
				r.setCodigoResultado(ERROR_LISTAR_GRUPOS_ALTA_WS);
				return r;
			}

		    		    
		    if(!grupoWebSemanticaEncontrado) {
		    	
		    	try
		    	{
			    Boolean rolDocenteEncontrado=false;
			    
		    	//Para crear el nuevo grupo necesito darle el rol docente
			    RolVO[] roles = handleListarRoles();
			    for(int i=0; i<roles.length; i++) {
			    	
			    	if(roles[i].getDescripcion().equalsIgnoreCase("DOCENTE")) {
			    	
			    		GrupoVO nuevoGrupo=new GrupoVO();
			    		RolVO[] rolWebSemantica=new RolVO[1];
			    		rolWebSemantica[0]=roles[i];
			    		nuevoGrupo.setRols(rolWebSemantica);
			    		nuevoGrupo.setDescripcion(nombreGrupoWebSemantica);
			    		Long idGrupo=handleAltaGrupo(nuevoGrupo);
			    		
			    		GrupoVO[] grupoWebSemantica=new GrupoVO[1];
			    		grupoWebSemantica[0]=handleDescripcionGrupo(idGrupo);
			    		usuario.setGrupos(grupoWebSemantica);
			    		rolDocenteEncontrado=true;
			    		break;
			    	}
			    	
			    	
			    }
			    if(!rolDocenteEncontrado) {
					r.setMensaje("No existe el rol DOCENTE en la plataforma. Este es necesario para crear el grupo de usuarios de web semantica.");
					r.setCodigoResultado(ERROR_OBTENER_ROL_DOCENTE_WS);
					return r;
			    }
				} catch (Exception ex) {
					
					log.error("Error al obtener los roles ", ex);
					r.setMensaje("Error al obtener los roles");
					r.setCodigoResultado(ERROR_LISTAR_ROLES_ALTA_WS);
					return r;
				}
		    }
		    
		    //Asignamos el grupo de trabajo
		    grupoWebSemanticaEncontrado=false;
		    
		    try
		    {
			    GrupoTrabajoVO[] gruposDeTrabajo=handleListarGruposTrabajo();
			    for(int i=0; i<gruposDeTrabajo.length; i++) {
			    	
			    	if(gruposDeTrabajo[i].getNombre().equalsIgnoreCase(nombreGrupoWebSemantica)) {
			    		
			    		GrupoTrabajoVO[] grupoWebSemantica=new GrupoTrabajoVO[1];
			    		grupoWebSemantica[0]=gruposDeTrabajo[i];
			    		usuario.setGrupoTrabajo(grupoWebSemantica);
			    		grupoWebSemanticaEncontrado=true;
			    		break;
			    	}
			    }
			    
				if(!grupoWebSemanticaEncontrado) {
		    		GrupoTrabajoVO nuevogrupoTrabajo=new GrupoTrabajoVO();
		    		nuevogrupoTrabajo.setNombre(nombreGrupoWebSemantica);
		    		nuevogrupoTrabajo.setDescripcion("Grupo de trabajo de los usuarios de Web Semantica");
		    		Long idGrupo=handleAltaGrupoTrabajo(nuevogrupoTrabajo);
		    		
		    		GrupoTrabajoVO[] grupoWebSemantica=new GrupoTrabajoVO[1];
		    		grupoWebSemantica[0]=handleDescripcionGrupoTrabajo(idGrupo);
		    		usuario.setGrupoTrabajo(grupoWebSemantica);
				}
		    	 
			} catch (Exception ex) {
				
				log.error("Error al obtener los grupos de trabajo ", ex);
				r.setMensaje("Error al obtener los grupos de trabajo");
				r.setCodigoResultado(ERROR_LISTAR_GRUPOS_TRABAJO_ALTA_WS);
				return r;
			}
		    //Compruebo si el usuario(NIF) ya esta dado de alta. Si lo esta y no tiene fecha
	        //de baja saco un mensaje de error.
			try
			{
			    UsuarioVO usuarioExistente = handleObtenerUsuario(nif.toUpperCase());
			    if (usuarioExistente == null) {
			    
					//Chequeamos si el usuario introducido ya esta en la BD en ese
					//caso sacamos un mensaje de error
					Boolean existeUsuario = handleExisteUsuario(user);
					if (existeUsuario) {
						r.setMensaje("El usuario "+user+" ya existe");
						r.setCodigoResultado(ERROR_USUARIO_YA_EXISTENTE_WS);
						return r;
					}			
					
			    } else {
					if (usuarioExistente.getFechaBaja() == null) {
						r.setMensaje("Ya existe un usuario registrado con NIF "+nif);
						r.setCodigoResultado(ERROR_USUARIO_YA_EXISTENTE_WS);
						return r;
					}
					//El usuario ya esta registrado pero esta dado de baja
				    usuario.setFechaAlta(usuarioExistente.getFechaAlta());
			    }
			} catch (Exception ex) {
				
				log.error("Error al comprobar si existe usuario ", ex);
				r.setMensaje("Error al comprobar si existe usuario");
				r.setCodigoResultado(ERROR_COMPROBAR_USUARIO_TRABAJO_ALTA_WS);
				return r;
			}
			
		    // 16092014 La plataforma final de Procomún pasará la clave como hash sha1.
		    // Para compatibilidad con el piloto de Procomún que la pasa en claro, si se detecta que no es un hash se calcula
		    String pass = usuario.getClave();
		    if (!pass.startsWith("{SHA}"))
		    	try
		    	{
		    		pass = getHashPassword(pass);
				} catch (Exception ex) {					
					log.error("Error al calcular la contraseña ", ex);
					r.setMensaje("Error al calcular la contraseña");
					r.setCodigoResultado(ERROR_CALCULO_PASS_WS);
					return r;
				}
		    
		    //Damos al usuario de alta en la rama LDAP de la web semantica
			if (AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.LDAPEXTERNO).equalsIgnoreCase("false"))
			{
				try
				{
					this.getLdapWebSemanticaHandler().insertUser(usuario.getUsuario(),pass, 
						usuario.getNombre() + " " + usuario.getApellido1() + " " + usuario.getApellido2());
				} catch (Exception ex) {					
					log.error("Error al acceder a ldap", ex);
					r.setMensaje("Error al acceder a ldap para dar de alta");
					r.setCodigoResultado(ERROR_ACCESO_LDAP_WS);
					return r;
				}
			}

			try {
				//Damos de alta al usuario en Agrega
				//return altaUsuarioAgrega(usuario, true);
				retorno=altaUsuarioAgrega(usuario, false, false);
				r.setMensaje("Usuario creado correctamente con identificador : " + usuario.getUsuario());
				r.setCodigoResultado(OK_WS);
				return r;
				
			} catch (Exception e) {
				try
				{
					this.getLdapWebSemanticaHandler().deleteContact(usuario.getUsuario());
				} catch (Exception ex) {					
					log.error("Error al acceder a ldap para revertir alta de usuario", ex);
					r.setMensaje("Error al acceder a ldap para revertir alta de usuario");
					r.setCodigoResultado(ERROR_ACCESO_LDAP_WS);
					return r;
				}
				log.error("Error al dar de alta al usuario de Procomun en Agrega ", e);
				r.setMensaje("Error al dar de alta al usuario de Procomun en Agrega. Motivo :" +e.getMessage());
				r.setCodigoResultado(ERROR_ALTA_EN_AGREGA_WS);
				return r;
			}

		} catch (Exception e) {
			log.error("Error génerico al dar de alta al usuario de Procomun ", e);
			r.setMensaje("Error génerico al dar de alta al usuario de Procomun");
			r.setCodigoResultado(ERROR_GENERICO_ALTA_WS);
			return r;
		}
	}

	
	/**
	 * Devuelve true si el el valor del campo no se esta usando por ningun usuario de Agrega.
	 * En otras palabras devuelve true si el par campo-valor es valido para ser usable por un nuevo usuario.
	 */
	@Override
	protected ResultadoOperacionWebSemanticaVO handleValidarCampoUsuarioProComun(String nombreCampo,
			String valorCampo)  {

		ResultadoOperacionWebSemanticaVO r = new ResultadoOperacionWebSemanticaVO();
		r.setMensaje("");
		
		// Validamos que los parametros vengan informados
		if(nombreCampo==null || nombreCampo.isEmpty()) {
			r.setMensaje("El campo nombre es vacio o null");
			r.setCodigoResultado(ERROR_PARAMETROS_WS);
			return r;
		}
		if(valorCampo==null || valorCampo.isEmpty()) {
			r.setMensaje("El campo valor es vacio o null");
			r.setCodigoResultado(ERROR_PARAMETROS_WS);
			return r;
		}
		
		// Verificamos que el campo informado no esté ya utilizado en Agrega.
		if(nombreCampo.equalsIgnoreCase(CAMPO_EMAIL)) {
			if (!esUnMail(valorCampo)) {
				r.setMensaje("El campo valor no es un email valido");
				r.setCodigoResultado(ERROR_PARAMETROS_WS);
				return r;
			}
			if (this.getUsuarioDao().obtenerUsuarioPorEmail(valorCampo.toLowerCase())!=null) {
				r.setMensaje("El email ya pertenece a un usuario registrado");
				r.setCodigoResultado(ERROR_VALOR_YA_UTILIZADO_WS);
				return r;
			} else {
				r.setMensaje("El valor del campo no se está utilizando actualmente.");
				r.setCodigoResultado(OK_WS);
				return r;
			}
		} else if(nombreCampo.equalsIgnoreCase(CAMPO_DOCUMENTO_IDENTIDAD)) {
			if (obtenerUsuario(valorCampo.toUpperCase())!=null) {
				r.setMensaje("El documento de identidad ya pertenece a un usuario registrado");
				r.setCodigoResultado(ERROR_VALOR_YA_UTILIZADO_WS);
				return r;
			}
			else {
				r.setMensaje("El valor del campo no se está utilizando actualmente.");
				r.setCodigoResultado(OK_WS);
				return r;
			}
		}
		else if(nombreCampo.equalsIgnoreCase(CAMPO_IDENTIFICADOR)) {
				if (handleExisteUsuario(valorCampo))
				{
					r.setMensaje("El identificador ya pertenece a un usuario registrado");
					r.setCodigoResultado(ERROR_VALOR_YA_UTILIZADO_WS);
					return r;
				}
				else {
					r.setMensaje("El valor del campo no se está utilizando actualmente.");
					r.setCodigoResultado(OK_WS);
					return r;
				}
		}
		r.setMensaje("El nombre del campo no es correcto. Debe ser email, documentoIdentidad o identificador");
		r.setCodigoResultado(ERROR_CAMPO_INCORRECTO_WS);
		return r;
	}

	
	@Override
	protected ResultadoOperacionWebSemanticaVO handleAutenticacionUsuarioProComun(String email,
			String password) {

		ResultadoOperacionWebSemanticaVO r = new ResultadoOperacionWebSemanticaVO();
		r.setMensaje("");	

		if(email==null) {
			r.setMensaje("No se ha recibido un email o identificador del usuario a autenticar");
			r.setCodigoResultado(ERROR_PARAMETROS_WS);
			return r;
		}
		if(password==null) {
			r.setMensaje("No se ha recibido una password del usuario a autenticar");
			r.setCodigoResultado(ERROR_PARAMETROS_WS);
			return r;
		}
		
		String idUsuario = email; 

		log.info("PROCOMUN Va a autenticar el usuario: " + email);
		try
		{
			// Si es un email tenemos que obtener el identificador para Agrega
			// Para nuevos usuarios de web semántica será el mismo,
			// para usuarios anteriores de Agrega obtendremos un identificador normal de Agrega.
			Usuario usuario = null;
			
			if (esUnMail(email))
			{
				log.info("PROCOMUN Es un email " + email);
				usuario = this.getUsuarioDao().obtenerUsuarioPorEmail(email.toLowerCase());
				idUsuario = usuario.getUsuario();
				log.info("PROCOMUN Va a autenticar el id de usuario: " + idUsuario);
			}
			
			// Si no está activo devolvemos error diferenciado
			if (usuarioDesactivadoOEliminado(idUsuario)) {	
				log.info("El usuario " + email + " esta desactivado o eliminado");
				r.setMensaje("El usuario " + email + " esta desactivado o eliminado");
				r.setCodigoResultado(ERROR_USUARIO_NO_ACTIVO_WS);
				return r;
			}
			
			// Obtenemos el password del usuario almacenado en LDAP
			String passUserLdap = "";
			try
			{
				passUserLdap = getLdapHandler().getUserHasedPasswd(idUsuario);	
			} catch (Exception ex) {					
				log.error("Error al acceder a ldap para obtener la contraseña ", ex);
				r.setMensaje("Error al acceder a ldap para obtener la contraseña");
				r.setCodigoResultado(ERROR_ACCESO_LDAP_WS);
				return r;
			}
		    // 16092014 La plataforma final de Procomún pasará la clave como hash sha1 en hexadcimal.		    
//		    String hashPassEntrada = password;
//		    if (!hashPassEntrada.startsWith("{SHA}"))
//		    {
//		    	try
//		    	{
//		    		hashPassEntrada = getHashPassword(hashPassEntrada);
//				} catch (Exception ex) {					
//					log.error("Error al calcular la contraseña ", ex);
//					r.setMensaje("Error al calcular la contraseña");
//					r.setCodigoResultado(ERROR_CALCULO_PASS_WS);
//					return r;
//				}
//		    }

//		    log.info("Comparando : " + passUserLdap + " contra : " + hashPassEntrada);
			// Si ambos hash son iguales asumimos que la password es correcta y damos el usuario por correcto
			// devolviendo su información
			if (verificarPassword(passUserLdap, password))
			//if (passUserLdap.equals(hashPassEntrada))
			{
				log.info("Se ha autenticado correctamente al usuario: " + email);
				r.setMensaje("Se ha autenticado correctamente al usuario: " + email);
				r.setCodigoResultado(OK_WS);				
				return r;
			} else if (passUserLdap.equals(getHashPassword(password)))
			{
				log.info("Se ha autenticado correctamente al usuario: " + email);
				r.setMensaje("Se ha autenticado correctamente al usuario: " + email);
				r.setCodigoResultado(OK_WS);				
				return r;
				
			}
			else{
				log.error("No se ha autenticado correctamente al usuario: " + email);
				r.setMensaje("No se ha autenticado correctamente al usuario: " + email);
				r.setCodigoResultado(ERROR_USUARIO_NO_ENCONTRADO_WS);
				return r;
			}
			
		} catch (Exception e) {
			log.error("Se ha producido un error genérico al autenticar al usuario: " + email);
			log.error(e.getMessage());
			r.setMensaje("Se ha producido un error genérico al autenticar al usuario: " + email);
			r.setCodigoResultado(ERROR_GENERICO_AUTENTIFICAR_USUARIO_WS);
			return r;
		}
	}

	
	/**
	 * Obtiene los datos del usuario que se pasa como parámetro. 
	 * @param correoUsuario permite el correo del usuario o el identficador (para usuarios antiguos de Agrega). 
	 * @return UsuarioVO Datos del usuario solicitado
	 * @throws Exception
	 */
	@Override
	protected UsuarioVO handleObtenerDatosUsuarioWebSemantica(
			String correoUsuario) throws Exception {
		
		UsuarioVO usuario= null;
		
		log.info("PROCOMUN Va a obtener los datos del usuario: " + correoUsuario);
		
		// Si es un mail obtenemos los datos asociados al mail
		if (esUnMail(correoUsuario))
		{
			Usuario usuBd = this.getUsuarioDao().obtenerUsuarioPorEmail(correoUsuario.toLowerCase());
			if (usuBd!=null)
				usuario = getUsuarioDao().toUsuarioVO(usuBd); 			
		}
		// Si no es un mail, puede ser un identificador de Agrega y buscamos por identificador
		else
		{
			Usuario usuBd = this.getUsuarioDao().obtenerDatosUsuario(correoUsuario.toLowerCase());
			if (usuBd!=null)
				usuario = getUsuarioDao().toUsuarioVO(usuBd); 			
			
		}
		return usuario;
	}
	
	
	/**
	 * Compruebo si el usuario (login) que se le pasa por parámetro esta
	 * desactivado o dado de baja
	 * @param usuario login del usuario
	 * @return resultado Boolean que inidica si esta activo
	 * o no.
	 * @throws Exception
	 */
	protected Boolean usuarioDesactivadoOEliminado(String usuario)
	{
		Boolean resultado = Boolean.FALSE;
		Usuario usuarioEntity = this.getUsuarioDao().obtenerDatosUsuario(usuario.toLowerCase());

		if (usuarioEntity == null)
		{
			resultado = Boolean.TRUE;
		}else if ((!(usuarioEntity.getFechaBaja() == null)) || (!(usuarioEntity.getFechaDesactivacion() == null)))
		{
			resultado = Boolean.TRUE;
		}

		return resultado;
	}

	
	/**
	 * A parte de para modificar los datos de usuario sirve tambien para dar de alta a un usuario.
	 * @see es.pode.adminusuarios.negocio.servicios.SrvAdminUsuariosServiceBase#handleModificarUsuarioWebSemantica(es.pode.adminusuarios.negocio.servicios.UsuarioVO, java.lang.String)
	 */
	@Override
	protected ResultadoOperacionWebSemanticaVO handleModificarUsuarioProComun(UsuarioVO usuario, String passwordAntiguo)
			 {
		
		ResultadoOperacionWebSemanticaVO checkDatos=null;
		ResultadoOperacionWebSemanticaVO r = new ResultadoOperacionWebSemanticaVO();
		r.setMensaje("");
		
		//try {	
		    // Validamos los campos que informan desde Web Semántica (nombre, apellidos, nif, password, e email)
		     
		    if(usuario.getClave()!=null && !usuario.getClave().isEmpty()) 
		    	checkDatos=comprobarIntegridadDatosBasicosUsuarioProComun(usuario, true);
		    else
		    	checkDatos=comprobarIntegridadDatosBasicosUsuarioProComun(usuario, false);
		    
		    if(checkDatos==null || !checkDatos.getCodigoResultado().contentEquals(OK_WS)) 
		    	return checkDatos;

			String ldapExterno=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.LDAPEXTERNO);
			
			// Pasamos a mayúsculas para que todos los docmentos de identidad se inserten en mayúsculas en bd
		    if (usuario.getNIF()!=null)
		    	usuario.setNIF(usuario.getNIF().toUpperCase());
			
		    // Pasamos a minúsculas para que todos los correos se inserten en minúsculas en bd
		    if (usuario.getEmail()!=null)
		    	usuario.setEmail(usuario.getEmail().toLowerCase());

		    // Obtenemos el usuario de base de datos
		    UsuarioVO user = null;
			try{
				user = handleObtenerDatosUsuarioWebSemantica(usuario.getUsuario());
			} catch (Exception ex) {					
				log.error("Error al obtener los datos del usuario", ex);
				r.setMensaje("Error al obtener los datos del usuario");
				r.setCodigoResultado(ERROR_OBTENER_DATOS_USUARIO_WS);
				return r;				
			}
			
			if (user==null)
			{
				log.error("Se intenta modificar un usuario de Procomun que no existe: "+ usuario.getUsuario());
				r.setMensaje("Se intenta modificar un usuario de Procomun que no existe: "+ usuario.getUsuario());
				r.setCodigoResultado(ERROR_USUARIO_NO_ENCONTRADO_WS);
				return r;				
			}
			
			// Intentamos autenticar al usuario para permitir modificar sus datos
			ResultadoOperacionWebSemanticaVO resAuten = handleAutenticacionUsuarioProComun(user.getUsuario(), passwordAntiguo);			
			if (!resAuten.getCodigoResultado().equals(OK_WS) && !resAuten.getCodigoResultado().equals(ERROR_USUARIO_NO_ACTIVO_WS) )
			{
				log.error("No se ha autenticado correctamente al usuario: "+ usuario.getUsuario());				
				return resAuten;								
			}
			
			// Si el correo ha cambiado verificamos que no se esté usando ya
			if (!user.getEmail().equalsIgnoreCase(usuario.getEmail()))
			{
				ResultadoOperacionWebSemanticaVO resVal = handleValidarCampoUsuarioProComun("email",usuario.getEmail());
				if (!resVal.getCodigoResultado().equals(OK_WS))
				{
					r.setMensaje("El email ya pertenece a un usuario registrado");
					r.setCodigoResultado(ERROR_VALOR_YA_UTILIZADO_WS);
					return r;
				}
			}
			
			// Si el dni ha cambiado verificamos que no se esté usando ya
			if (!user.getNIF().equalsIgnoreCase(usuario.getNIF()))
			{
				ResultadoOperacionWebSemanticaVO resVal = handleValidarCampoUsuarioProComun("documentoIdentidad",usuario.getNIF());
				if (!resVal.getCodigoResultado().equals(OK_WS))
				{
					r.setMensaje("El documento de identidad ya pertenece a un usuario registrado");
					r.setCodigoResultado(ERROR_VALOR_YA_UTILIZADO_WS);
					return r;
				}
			}
		    //Activamos al usuario
		    handleActivarUsuario(user.getId(), "Procomun");    
		    
		    // Actualizamos el vo con los campos que informa Web Semántica		    
		    user.setNombre(usuario.getNombre());
		    user.setApellido1(usuario.getApellido1());
		    user.setApellido2(usuario.getApellido2());
		    user.setFechaDesactivacion(null);
		    user.setEmail(usuario.getEmail());
		    
		    //Si nos pasan un NIF lo modificamos
		    if (usuario.getNIF()!=null && !usuario.getNIF().isEmpty())
		    	user.setNIF(user.getNIF());

			//Primero almacenamos los datos actuales por si hay que hacer rollback
			Person datosLdapUsuario=null;
			try
			{
				datosLdapUsuario=this.getLdapWebSemanticaHandler().getUserData(user.getUsuario());
			} catch (Exception ex) {					
				log.error("Error al acceder a ldap", ex);
				r.setMensaje("Error al acceder a ldap para obtener los datos del usuario");
				r.setCodigoResultado(ERROR_ACCESO_LDAP_WS);
				return r;
			}
			// Para poder cambiar la password debe haber informado la contraseña anterior
		    String hashedPassword="";
		    
		    if(usuario.getClave()!=null || !usuario.getClave().isEmpty()) {
		    //if(passwordAntiguo!=null && !passwordAntiguo.isEmpty()) {
			//	boolean usuarioCorrecto = handleAutenticacionUsuarioWebSemantica(user.getUsuario(), passwordAntiguo);
			//	if (!usuarioCorrecto) throw new Exception("El par usuario/password no es valido o el usuario esta desactivado o eliminado");
		    	try
		    	{
		    		hashedPassword=this.getHashPassword(usuario.getClave());
				} catch (Exception ex) {					
					log.error("Error al calcular la contraseña ", ex);
					r.setMensaje("Error al calcular la contraseña");
					r.setCodigoResultado(ERROR_CALCULO_PASS_WS);
					return r;
				}
			    user.setClave(usuario.getClave());
		    } else {
		    	hashedPassword=datosLdapUsuario.getHasedPasswd();
			    user.setClave("");
		    }
		
		    //Modificamos los datos del usuario en la rama LDAP de la web semantica
			if(ldapExterno.equalsIgnoreCase("false")) {	
				//Ahora modificamos
				try
				{
					this.getLdapWebSemanticaHandler().modifyUser(user.getUsuario(), hashedPassword, 
						user.getNombre() + " " + user.getApellido1() + " " + user.getApellido2());	
				} catch (Exception ex) {					
					log.error("Error al acceder a ldap", ex);
					r.setMensaje("Error al acceder a ldap para modificar los datos del usuario");
					r.setCodigoResultado(ERROR_ACCESO_LDAP_WS);
					return r;
				}
			}
			try
			{
				String resultado=modificarUsuarioAgrega(user);
				if(!resultado.equalsIgnoreCase("OK.MODIFICARUSUARIO")) {
					if(ldapExterno.equalsIgnoreCase("false")) {	
						try
						{
						//Rollback
							this.getLdapWebSemanticaHandler().modifyUser(datosLdapUsuario.getUsuario(), datosLdapUsuario.getHasedPasswd(), 
							datosLdapUsuario.getNombreApellidos());
						} catch (Exception ex) {					
							log.error("Error al acceder a ldap", ex);
							r.setMensaje("Error al acceder a ldap para modificar los datos del usuario");
							r.setCodigoResultado(ERROR_ACCESO_LDAP_WS);
							return r;
						}
					}
					log.error("Error genérico al modificar el usuario en agrega");
					r.setMensaje("Error genérico al modificar el usuario en agrega");
					r.setCodigoResultado(ERROR_GENERICO_MODIFICAR_USUARIO_WS);
					return r;
				}
			} catch (Exception ex) {					
				log.error("Error al modificar usuario en Agrega", ex);
				r.setMensaje("Error al modificar usuario en Agrega. Motivo:" + ex.getMessage());
				r.setCodigoResultado(ERROR_MODIFICAR_USUARIO_AGREGA_WS);
				return r;
			}			
						    			
//		} catch (Exception e) {
//			log.error("Error al modificar el usuario de web semantica :", e);
//			r.setMensaje("Error al modificar el usuario de web semantica");
//			r.setCodigoResultado(ERROR_USUARIO_NO_ENCONTRADO_WS);
//			return r;
//		}
		r.setMensaje("Usuario modificado correctamente");
		r.setCodigoResultado(OK_WS);
		return r;
	}

	
	@Override
	protected ResultadoOperacionWebSemanticaVO handleBajaUsuarioProComun(String email, String passwd)
			 throws Exception{
		
		ResultadoOperacionWebSemanticaVO resAutenticacion=null;
		ResultadoOperacionWebSemanticaVO r = new ResultadoOperacionWebSemanticaVO();
		r.setMensaje("");
		
		if(email==null) {
			r.setMensaje("No se ha recibido un mail para dar de baja en la web semantica");
			r.setCodigoResultado(ERROR_PARAMETROS_WS);
			return r;
		}
		if(passwd==null) {
			r.setMensaje("No se ha recibido la passwd para dar de baja al usuario "+email+" en la web semantica");
			r.setCodigoResultado(ERROR_PARAMETROS_WS);
			return r;
		}
		
		String mail=email.toLowerCase();
		
		boolean bCasoBajaEspecial = false;
		
		try {
			
			if (passwd.equals(PASSWORD_BAJA_ESPECIAL)) 
				bCasoBajaEspecial =true;
			
			log.info("Es una baja especial de usuario de web semantica :"+bCasoBajaEspecial);
			
			String ldapExterno=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.LDAPEXTERNO);
			
			//Comprobamos que la clave es correcta
			log.info("Va a autenticar : " + mail + " con : " + passwd);
			resAutenticacion=handleAutenticacionUsuarioProComun(mail, passwd);
			if(resAutenticacion==null) {
				r.setMensaje("El par usuario/password no es válido o el usuario esta desactivado o eliminado");
				r.setCodigoResultado(ERROR_GENERICO_AUTENTIFICAR_USUARIO_WS);
				return r;
			}			
			if(!bCasoBajaEspecial & !resAutenticacion.getCodigoResultado().contentEquals(OK_WS)) {
				
				return resAutenticacion;
			}
						
		    //Damos al usuario de baja en la rama LDAP de la web semantica
			if (AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.LDAPEXTERNO).equalsIgnoreCase("false"))
			{
				try
				{
					this.getLdapWebSemanticaHandler().deleteContact(mail);				
				} catch (Exception ex) {					
					log.error("Error al acceder a ldap para dar de baja", ex);
					r.setMensaje("Error al acceder a ldap para dar de baja");
					r.setCodigoResultado(ERROR_ACCESO_LDAP_WS);
					return r;				
				}
			}
				
			UsuarioVO usuario = null;
			boolean bRetrotraerBaja = false;
			
				try{
					usuario = handleObtenerDatosUsuarioWebSemantica(mail);
				} catch (Exception ex) {					
					log.error("Error al obtener los datos del usuario", ex);
					r.setMensaje("Error al obtener los datos del usuario");
					r.setCodigoResultado(ERROR_OBTENER_DATOS_USUARIO_WS);
					return r;				
				}
				if(usuario==null) {
					log.info("Se intenta dar de baja un usuario de Procomun que no existe: "+mail);
					r.setMensaje("Se intenta dar de baja un usuario de Procomun que no existe: "+mail);
					r.setCodigoResultado(ERROR_USUARIO_NO_ENCONTRADO_WS);
					return r;
				}
				
				//Damos de baja al usuario en Agrega	
				Long[] idUsuario=new Long[1];
				idUsuario[0]=usuario.getId();
				ValidaBajaUsuarioVO ret= null;
				try
				{
					ret = bajaUsuarioAgrega(idUsuario, false);
				} catch (Exception ex) {					
					log.error("Error al dar de baja el usuario en Agrega", ex);
					r.setMensaje("Error al dar de baja el usuario en Agrega. Motivo: " + ex.getMessage());						
					r.setCodigoResultado(ERROR_BAJA_USUARIO_AGREGA_WS);
					bRetrotraerBaja = true;	
				}
				if(bCasoBajaEspecial)
					resetearCamposUsuarioBajaEspecialWS(usuario.getId());
				
				if((ret.getNumDeleted()!=1) || bRetrotraerBaja) {
				    //Damos al usuario de alta en la rama LDAP de la web semantica
					if(ldapExterno.equalsIgnoreCase("false")) {
						try {
							this.getLdapWebSemanticaHandler().insertUser(usuario.getUsuario(), this.getHashPassword(usuario.getClave()),
									usuario.getNombre() + " " + usuario.getApellido1() + " " + usuario.getApellido2());
						} catch (UnsupportedEncodingException e) {
							log.error("Error al calcular la password del usuario", e);
							r.setMensaje("Error al calcular la password del usuario");						
							r.setCodigoResultado(ERROR_CALCULO_PASS_WS);
							return r;						
						} catch (NoSuchAlgorithmException e) {
							log.error("Error al calcular la password del usuario", e);
							r.setMensaje("Error al calcular la password del usuario");						
							r.setCodigoResultado(ERROR_CALCULO_PASS_WS);
							return r;
						} catch (Exception e) {
							log.error("Error al acceder a ldap para retrotraer la baja del usuario en Agrega", e);
							r.setMensaje("Error al acceder a ldap para retrotraer la baja del usuario en Agrega");						
							r.setCodigoResultado(ERROR_BAJA_USUARIO_AGREGA_WS);
							return r;						
						}
					}
					r.setMensaje("Error al dar de baja al usuario de Procomun: "+mail);
					r.setCodigoResultado(ERROR_BAJA_USUARIO_AGREGA_WS);
					return r;
				}
				
						
		} catch (Exception e) {
			log.error("Error genérico al dar de baja al usuario de Procomún "+mail+": ", e);
			r.setMensaje("Error genérico al dar de baja al usuario de Procomún. Motivo: "+e.getMessage());
			r.setCodigoResultado(ERROR_GENERICO_BAJA_USUARIO_WS);
			return r;
		}
		r.setMensaje("Baja realizada correctamente");
		r.setCodigoResultado(OK_WS);
		return r;
	}	

	/**
	* Resetea los campos de dni y correo electrónico de un usuario de Web Semántica para que se puedan utilizar por otro usuario 	    
	* @param id Long identificador del usuario que se desea activar
	* @throws Exception
	*/
	protected void resetearCamposUsuarioBajaEspecialWS(Long id){
		Usuario usuario = null;			
		usuario = (this.getUsuarioDao()).load(id);
		usuario.setNIF("WS_"+  usuario.getNIF() +"_" + System.currentTimeMillis());
		usuario.setEmail("WS_" + usuario.getEmail() +"_" + System.currentTimeMillis());
		usuario.setUsuario("WS_" + usuario.getUsuario() +"_" + System.currentTimeMillis());
		this.getUsuarioDao().update(usuario);
		log.info("Se han reseteado los campos dni y correo electrónico del usuario: " + usuario.getUsuario());			
	}
  
  	/**
	 * Metodo para dar de alta un usuario de web semantica. Se le agregará en el servidor
	 * ldap de Agrega y en el de Web Semantica y se enviará un correo con el usuario y la clave
	 * El usuario se creara desactivado.
	 * @param usuario usuarioVO con los datos del usuario que se dara de alta
	 * @return resultado String que devuelve si el alta ha ido bien o no
	 * @throws Exception
	 */	
	@Override
	protected Long handleAltaUsuarioWebSemantica(UsuarioVO usuario)
			throws Exception {
		
		Long retorno = null;
		String nombreGrupoWebSemantica="UsuariosWebSemantica";
		
		try {
		    // Validamos los campos que informan desde Web Semántica (nombre, apellidos, nif y password)
		    comprobarIntegridadDatosBasicosUsuarioWebSemantica(usuario, true);
		    
			// Pasamos a mayúsculas para que todos los docmentos de identidad se inserten en mayúsculas en bd
		    if (usuario.getNIF()!=null)
		    	usuario.setNIF(usuario.getNIF().toUpperCase());
			
		    // Pasamos a minúsculas para que todos los correos se inserten en minúsculas en bd
		    if (usuario.getEmail()!=null)
		    	usuario.setEmail(usuario.getEmail().toLowerCase());
		    
			//Cargamos los campos del usuario que podemos recibir
		    String nombre = usuario.getNombre();
		    String apellido1 = usuario.getApellido1();
		    String apellido2 = usuario.getApellido2();
		    String nif;
		    if (usuario.getNIF()!=null && !usuario.getNIF().isEmpty())
		    	nif = usuario.getNIF();
		    else 
		    	nif = "WS"+System.currentTimeMillis();
		    Boolean recibirCorreoPublicacion = usuario.getRecibirCorreoPublicacion();
		    //Campos de usuario que ajustamos automaticamente independientemente
		    //de lo que recibamos
			String user=usuario.getEmail();
		    String openIdUrl = "";
		    String idiomaBusqueda="es"; //Puede ser; ca, en, eu, gl, va o fr
		    String idioma="es";
		    String tipoEmpaquetador="basico"; //Puede ser basico o avanzado
		    String tipoCatalogador="basico";
		    String tipoVisualizador="agrega"; //Puede ser agrega o adl
		    //Long cuota = Long.valueOf(5).longValue() * 1024 * 1024; //5MB por defecto
			
			//RecibirCorreo
		    if (recibirCorreoPublicacion == null) {
		    	recibirCorreoPublicacion=false;
		    }
    	    
		    //Cargo las modificaciones y los ajustes de los campos en el usuario a crear 
		    usuario.setNombre(nombre);
		    usuario.setApellido1(apellido1);
		    usuario.setApellido2(apellido2);
		    usuario.setNIF(nif);
		    usuario.setRecibirCorreoPublicacion(recibirCorreoPublicacion);
		    usuario.setUsuario(user);
		    usuario.setOpenIdUrl(openIdUrl);
		    usuario.setIdiomaBusqueda(idiomaBusqueda);
		    usuario.setIdioma(idioma);
		    usuario.setTipoEmpaquetador(tipoEmpaquetador);
		    usuario.setTipoCatalogador(tipoCatalogador);
		    usuario.setTipoVisualizador(tipoVisualizador);
		    //usuario.setCuota(cuota);    
		    usuario.setFechaAlta(new java.util.Date()); 
		    usuario.setFechaBaja(null);
		    //usuario.setFechaDesactivacion(null);
		    usuario.setFechaDesactivacion(new java.util.Date());
		    
		    //Asignamos grupo
		    Boolean grupoWebSemanticaEncontrado=false;
		    
		    //Buscamos el grupo UsuariosWebSemantica
		    GrupoVO[] grupos=handleListarGrupos();
		    for(int i=0; i<grupos.length; i++) {
		    	
		    	if(grupos[i].getDescripcion().equalsIgnoreCase(nombreGrupoWebSemantica)) {
		    	
		    		GrupoVO[] grupoWebSemantica=new GrupoVO[1];
		    		grupoWebSemantica[0]=grupos[i];
		    		usuario.setGrupos(grupoWebSemantica);
		    		grupoWebSemanticaEncontrado=true;
		    		break;
		    	}
		    }
		    		    
		    if(!grupoWebSemanticaEncontrado) {
			    Boolean rolDocenteEncontrado=false;
			    
		    	//Para crear el nuevo grupo necesito darle el rol docente
			    RolVO[] roles = handleListarRoles();
			    for(int i=0; i<roles.length; i++) {
			    	
			    	if(roles[i].getDescripcion().equalsIgnoreCase("DOCENTE")) {
			    	
			    		GrupoVO nuevoGrupo=new GrupoVO();
			    		RolVO[] rolWebSemantica=new RolVO[1];
			    		rolWebSemantica[0]=roles[i];
			    		nuevoGrupo.setRols(rolWebSemantica);
			    		nuevoGrupo.setDescripcion(nombreGrupoWebSemantica);
			    		Long idGrupo=handleAltaGrupo(nuevoGrupo);
			    		
			    		GrupoVO[] grupoWebSemantica=new GrupoVO[1];
			    		grupoWebSemantica[0]=handleDescripcionGrupo(idGrupo);
			    		usuario.setGrupos(grupoWebSemantica);
			    		rolDocenteEncontrado=true;
			    		break;
			    	}
			    }
			    if(!rolDocenteEncontrado) {
					throw new Exception("No existe el rol DOCENTE en la plataforma. Este es necesario para crear el gru de usuarios de web semantica.");
			    }
		    }
		    
		    //Asignamos el grupo de trabajo
		    grupoWebSemanticaEncontrado=false;
		    
		    GrupoTrabajoVO[] gruposDeTrabajo=handleListarGruposTrabajo();
		    for(int i=0; i<gruposDeTrabajo.length; i++) {
		    	
		    	if(gruposDeTrabajo[i].getNombre().equalsIgnoreCase(nombreGrupoWebSemantica)) {
		    		
		    		GrupoTrabajoVO[] grupoWebSemantica=new GrupoTrabajoVO[1];
		    		grupoWebSemantica[0]=gruposDeTrabajo[i];
		    		usuario.setGrupoTrabajo(grupoWebSemantica);
		    		grupoWebSemanticaEncontrado=true;
		    		break;
		    	}
		    }
		    
			if(!grupoWebSemanticaEncontrado) {
	    		GrupoTrabajoVO nuevogrupoTrabajo=new GrupoTrabajoVO();
	    		nuevogrupoTrabajo.setNombre(nombreGrupoWebSemantica);
	    		nuevogrupoTrabajo.setDescripcion("Grupo de trabajo de los usuarios de Web Semantica");
	    		Long idGrupo=handleAltaGrupoTrabajo(nuevogrupoTrabajo);
	    		
	    		GrupoTrabajoVO[] grupoWebSemantica=new GrupoTrabajoVO[1];
	    		grupoWebSemantica[0]=handleDescripcionGrupoTrabajo(idGrupo);
	    		usuario.setGrupoTrabajo(grupoWebSemantica);
			}
		    	    
		    //Compruebo si el usuario(NIF) ya esta dado de alta. Si lo esta y no tiene fecha
	        //de baja saco un mensaje de error.
		    UsuarioVO usuarioExistente = handleObtenerUsuario(nif.toUpperCase());
		    if (usuarioExistente == null) {
		    
				//Chequeamos si el usuario introducido ya esta en la BD en ese
				//caso sacamos un mensaje de error
				Boolean existeUsuario = handleExisteUsuario(user);
				if (existeUsuario) {
					throw new Exception("El usuario "+user+" ya existe");
				}			
				
		    } else {
				if (usuarioExistente.getFechaBaja() == null) {
					throw new Exception("Ya existe un usuario registrado con NIF "+nif);
				}
				//El usuario ya esta registrado pero esta dado de baja
			    usuario.setFechaAlta(usuarioExistente.getFechaAlta());
		    }
		    
		    //Damos al usuario de alta en la rama LDAP de la web semantica
			if (AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.LDAPEXTERNO).equalsIgnoreCase("false"))
			{
				this.getLdapWebSemanticaHandler().insertUser(usuario.getUsuario(), this.getHashPassword(usuario.getClave()), 
						usuario.getNombre() + " " + usuario.getApellido1() + " " + usuario.getApellido2());
			}

			try {
				//Damos de alta al usuario en Agrega
				//return altaUsuarioAgrega(usuario, true);
				return altaUsuarioAgrega(usuario, false, false);
			} catch (Exception e) {
				this.getLdapWebSemanticaHandler().deleteContact(usuario.getUsuario());
				log.error("Error al dar de alta al usuario de web semantica en agrega ", e);
				throw e;
			}

		} catch (Exception e) {
			log.error("Error al dar de alta al usuario de web semantica ", e);
			throw e;
		}
	}

  	@Override
	protected boolean handleAutenticacionUsuarioWebSemantica(String email,
			String password) throws Exception {

		boolean autentCorrecto = false;		

		if(email==null) {
			throw new Exception("No se ha recibido un email o identificador del usuario a autenticar");
		}
		if(password==null) {
			throw new Exception("No se ha recibido una passwd del usuario a autenticar");
		}
		
		String idUsuario = email; 

		log.info("PROCOMUN Va a autenticar el usuario: " + email);
		try
		{
			// Si es un email tenemos que obtener el identificador para Agrega
			// Para nuevos usuarios de web semántica será el mismo,
			// para usuarios anteriores de Agrega obtendremos un identificador normal de Agrega.
			Usuario usuario = null;
			if (esUnMail(email))
			{
				usuario = this.getUsuarioDao().obtenerUsuarioPorEmail(email.toLowerCase());
				idUsuario = usuario.getUsuario();
			}
			
			// Si no está activo devolvemos false
//			if (usuarioDesactivadoOEliminado(idUsuario)) {	
//				log.info("El usuario " + email + "esta desactivado o eliminado");
//				return false;
//			}
			
			// Obtenemos el password del usuario almacenado en LDAP
			String passUserLdap = getLdapHandler().getUserHasedPasswd(idUsuario);	
			
			// Obtenemos el hash del password proporcionado por PROCOMUN
			String hashPassEntrada = getHashPassword(password);
			
			// Si ambos hash son iguales asumimos que la password es correcta y damos el usuario por correcto
			// devolviendo su información
			log.info("EMILIO. Va a comparar : " + passUserLdap + " contra : " + hashPassEntrada);
			if (passUserLdap.equals(hashPassEntrada))
			{
				log.info("Se ha autenticado correctamente al usuario: " + email);				
				autentCorrecto = true;				
			}else
				log.error("No se ha autenticado correctamente al usuario: " + email);
					
		}catch (Exception e) {
			log.error("Error al intentar autenticar al usuario: " + email);
			log.error(e.getMessage());
		}
		return autentCorrecto;
	}

  	@Override
	protected boolean handleBajaUsuarioWebSemantica(String email, String passwd)
			throws Exception {
		
		if(email==null) {
			throw new Exception("No se ha recibido un mail para dar de baja en la web semantica");
		}
		if(passwd==null) {
			throw new Exception("No se ha recibido la passwd para dar de baja al usuario "+email+" en la web semantica");
		}
		
		String mail=email.toLowerCase();
		
		boolean bCasoBajaEspecial = false;
		
		try {
			
			if (passwd.equals(PASSWORD_BAJA_ESPECIAL)) 
				bCasoBajaEspecial =true;
			
			log.info("Es una baja especial de usuario de web semantica :"+bCasoBajaEspecial);
			
			String ldapExterno=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.LDAPEXTERNO);
			
			//Comprobamos que la clave es correcta
			if(!bCasoBajaEspecial & (!handleAutenticacionUsuarioWebSemantica(mail, passwd))) 
				throw new Exception("El par usuario/password no es valido o el usuario esta desactivado o eliminado");
						
		    //Damos al usuario de baja en la rama LDAP de la web semantica
			if (AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.LDAPEXTERNO).equalsIgnoreCase("false"))
				this.getLdapWebSemanticaHandler().deleteContact(mail);
				
			UsuarioVO usuario = null;
			try {
				usuario = handleObtenerDatosUsuarioWebSemantica(mail); 
				if(usuario==null) {
					log.info("Se intenta dar de baja un usuario de web semantica que no existe: "+mail);
					throw new Exception ("Se intenta dar de baja un usuario de web semantica que no existe: "+mail);
				}
				//Damos de baja al usuario en Agrega	
				Long[] idUsuario=new Long[1];
				idUsuario[0]=usuario.getId();
				ValidaBajaUsuarioVO ret=bajaUsuarioAgrega(idUsuario, false);
				
				if(bCasoBajaEspecial)
					resetearCamposUsuarioBajaEspecialWS(usuario.getId());
				
				if(ret.getNumDeleted()!=1) {
				    //Damos al usuario de alta en la rama LDAP de la web semantica
					if(ldapExterno.equalsIgnoreCase("false")) {
						this.getLdapWebSemanticaHandler().insertUser(usuario.getUsuario(), this.getHashPassword(usuario.getClave()),
								usuario.getNombre() + " " + usuario.getApellido1() + " " + usuario.getApellido2());
					}
					throw new Exception ("Error al dar de baja al usuario de web semantica "+mail);
				}
				
			} catch (Exception e) {
				//Damos al usuario de alta en la rama LDAP de la web semantica
				if(ldapExterno.equalsIgnoreCase("false")) {
					this.getLdapWebSemanticaHandler().insertUser(usuario.getUsuario(), this.getHashPassword(usuario.getClave()), 
							usuario.getNombre() + " " + usuario.getApellido1() + " " + usuario.getApellido2());
				}							
				throw new Exception ("Error al dar de baja al usuario de web semantica "+mail);
			}
						
		} catch (Exception e) {
			log.error("Error al dar de baja al usuario de web semantica "+mail+": ", e);
			throw e;
		}
		return true;
	}
  
  	/**
	 * A parte de para modificar los datos de usuario sirve tambien para dar de alta a un usuario.
	 * @see es.pode.adminusuarios.negocio.servicios.SrvAdminUsuariosServiceBase#handleModificarUsuarioWebSemantica(es.pode.adminusuarios.negocio.servicios.UsuarioVO, java.lang.String)
	 */
	@Override
	protected boolean handleModificarUsuarioWebSemantica(UsuarioVO usuario, String passwordAntiguo)
			throws Exception {
		
		try {	
		    // Validamos los campos que informan desde Web Semántica (nombre, apellidos, nif, password, e email)
		    //if(passwordAntiguo!=null && !passwordAntiguo.isEmpty()) 
		    if(usuario.getClave()!=null && !usuario.getClave().isEmpty()) 
		    	comprobarIntegridadDatosBasicosUsuarioWebSemantica(usuario, true);
		    else
		    	comprobarIntegridadDatosBasicosUsuarioWebSemantica(usuario, false);

			String ldapExterno=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.LDAPEXTERNO);
			
			// Pasamos a mayúsculas para que todos los docmentos de identidad se inserten en mayúsculas en bd
		    if (usuario.getNIF()!=null)
		    	usuario.setNIF(usuario.getNIF().toUpperCase());
			
		    // Pasamos a minúsculas para que todos los correos se inserten en minúsculas en bd
		    if (usuario.getEmail()!=null)
		    	usuario.setEmail(usuario.getEmail().toLowerCase());

		    // Obtenemos el usuario de base de datos
		    UsuarioVO user = handleObtenerDatosUsuarioWebSemantica(usuario.getEmail());
		    
		    //Damos de alta al usuario
		    handleActivarUsuario(user.getId(), "Web Semantica");    
		    
		    // Actualizamos el vo con los campos que informa Web Semántica		    
		    user.setNombre(usuario.getNombre());
		    user.setApellido1(usuario.getApellido1());
		    user.setApellido2(usuario.getApellido2());
		    user.setFechaDesactivacion(null);
		    
		    //Si nos pasan un NIF lo modificamos
		    if (usuario.getNIF()!=null && !usuario.getNIF().isEmpty())
		    	user.setNIF(user.getNIF());

			//Primero almacenamos los datos actuales por si hay que hacer rollback
			Person datosLdapUsuario=null;
			datosLdapUsuario=this.getLdapWebSemanticaHandler().getUserData(user.getUsuario());

			// Para poder cambiar la password debe haber informado la contraseña anterior
		    String hashedPassword="";
		    
		    if(usuario.getClave()!=null || !usuario.getClave().isEmpty()) {
		    //if(passwordAntiguo!=null && !passwordAntiguo.isEmpty()) {
			//	boolean usuarioCorrecto = handleAutenticacionUsuarioWebSemantica(user.getUsuario(), passwordAntiguo);
			//	if (!usuarioCorrecto) throw new Exception("El par usuario/password no es valido o el usuario esta desactivado o eliminado");
				hashedPassword=this.getHashPassword(usuario.getClave());
			    user.setClave(usuario.getClave());
		    } else {
		    	hashedPassword=datosLdapUsuario.getHasedPasswd();
			    user.setClave("");
		    }
		
		    //Modificamos los datos del usuario en la rama LDAP de la web semantica
			if(ldapExterno.equalsIgnoreCase("false")) {	
				//Ahora modificamos
				this.getLdapWebSemanticaHandler().modifyUser(user.getUsuario(), hashedPassword, 
						user.getNombre() + " " + user.getApellido1() + " " + user.getApellido2());			
			}

			String resultado=modificarUsuarioAgrega(user);
			if(!resultado.equalsIgnoreCase("OK.MODIFICARUSUARIO")) {
				if(ldapExterno.equalsIgnoreCase("false")) {				
					//Rollback
					this.getLdapWebSemanticaHandler().modifyUser(datosLdapUsuario.getUsuario(), datosLdapUsuario.getHasedPasswd(), 
						datosLdapUsuario.getNombreApellidos());
				}
				log.error("Error al modificar el usuario en agrega");
				return false;
			}
						    			
		} catch (Exception e) {
			log.error("Error al modificar el usuario de web semantica :", e);
			throw e;
		}
		return true;
	}
  
	/**
	 * Devuelve true si el el valor del campo no se esta usando por ningun usuario de Agrega.
	 * En otras palabras devuelve true si el par campo-valor es valido para ser usable por un nuevo usuario.
	 */
	@Override
	protected boolean handleValidarCampoUsuarioWebSemantica(String nombreCampo,
			String valorCampo) throws Exception {

		// Validamos que los parametros vengan informados
		if(nombreCampo==null || nombreCampo.isEmpty())
			return false;
		if(valorCampo==null || valorCampo.isEmpty())
			return false;
		
		// Verificamos que el campo informado no esté ya utilizado en Agrega.
		if(nombreCampo.equalsIgnoreCase("email")) {
			if (!esUnMail(valorCampo)) return false;
			if (this.getUsuarioDao().obtenerUsuarioPorEmail(valorCampo.toLowerCase())!=null)		
				return false;
			else return true;				
		} else if(nombreCampo.equalsIgnoreCase("documentoIdentidad")) {
			if (obtenerUsuario(valorCampo.toUpperCase())!=null)
					return false;
			else return true;
		}
		return false;
	}
	
	private void comprobarIntegridadDatosBasicosUsuarioWebSemantica(
			UsuarioVO usuario, boolean comprobarPasswd) throws Exception {

		// Revisamos que recibimos los datos necesarios para crear el usuario de
		// web semantica
		if (usuario == null) {
			throw new Exception(
					"No se ha recibido un usuario que dar de alta/modificar en la web semantica");
		}

		// Validacion para nombre y apellidos
		Pattern mask = Pattern
				.compile("[^\\¿\\?\\¡\\!\\>\\#\\&\\<\\@\\$\\/\\\'\\\"]+");
		// Validacion para password
		Pattern maskPwd = Pattern
				.compile("[^Ññ\\¿\\?\\¡\\!\\>\\#\\&\\<\\@\\$\\/\\\'\\\"]+");

		// Cargamos los campos del usuario que podemos recibir
		String nombre = usuario.getNombre();
		String apellido1 = usuario.getApellido1();
		String apellido2 = usuario.getApellido2();
		String nif = usuario.getNIF();
		String passwd = usuario.getClave();

		// Nombre
		if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
			throw new Exception("El campo nombre no es valido");
		}
		Matcher matcher = mask.matcher(nombre);
		if (!matcher.matches()) {
			throw new Exception("Caracter ilegal en el campo nombre");
		}
		// Apellido1
		if (apellido1 == null || apellido1.isEmpty()) {
			throw new Exception("El campo apellido1 no es valido");
		}
		matcher = mask.matcher(apellido1);
		if (!matcher.matches()) {
			throw new Exception("Caracter ilegal en el campo apellido1");
		}
		// Apellido2
		if (apellido2 != null && !apellido2.isEmpty()) {
			matcher = mask.matcher(apellido2);
			if (!matcher.matches()) {
				throw new Exception("Caracter ilegal en el campo apellido2");
			}
		}
		// NIF
		/*
		 * if(nif==null || nif.isEmpty()) { throw new
		 * Exception("El campo nif no es valido"); }
		 */
		// Passwd
		if (comprobarPasswd) {
			if (passwd == null || passwd.isEmpty()) {
				throw new Exception("El campo password no es valido");
			}
			matcher = maskPwd.matcher(passwd);
			if (!matcher.matches()) {
				throw new Exception("Caracter ilegal en el campo password");
			}
		}
		// Mail
		if (!esUnMail(usuario.getEmail())) {
			throw new Exception("El campo email no es valido");
		}
	}
	
	private boolean verificarPassword(String passwordLdap, String hashHexEntrada) {
		boolean res = false;

		log.info("verificarPassword. PassLdap :" + passwordLdap + " HashHexEntrada : " + hashHexEntrada);
		try {
			String hashB64Calculado = "{SHA}" +getBase64FromHEX(hashHexEntrada);
			System.out.println("hashCalculado :" + hashB64Calculado);

			if (passwordLdap.equals(hashB64Calculado))
				res = true;

			log.info("Los passwords coinciden : " + res);

		} catch (Exception e) {
			log.error("Error al verificar los passwords: " + e.getMessage());
		}

		return res;
	}	
	
	public String getBase64FromHEX(String input) {

		byte barr[] = new byte[input.length() / 2];
		int bcnt = 0;
		for (int i = 0; i < input.length(); i += 2) {
			char c1 = input.charAt(i);
			char c2 = input.charAt(i + 1);
			int i1 = intFromChar(c1);
			int i2 = intFromChar(c2);

			barr[bcnt] = 0;
			barr[bcnt] |= (byte) ((i1 & 0x0F) << 4);
			barr[bcnt] |= (byte) (i2 & 0x0F);
			bcnt++;
		}
		
		return new String(Base64Coder.encode(barr));
	}

	private int intFromChar(char c) {
		char clower = Character.toLowerCase(c);
		for (int i = 0; i < carr.length; i++) {
			if (clower == carr[i]) {
				return i;
			}
		}

		return 0;
	}
}
