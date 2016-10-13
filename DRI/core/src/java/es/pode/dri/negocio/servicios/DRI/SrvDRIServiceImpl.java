/*
Agrega2 es una federación de repositorios de objetos digitales educativos formada por todas las Comunidades Autónomas propiedad de Red.es.

This program is free software: you can redistribute it and/or modify it under the terms of the European Union Public Licence (EUPL v.1.0).  This program is distributed in the hope that it will be useful,  but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the European Union Public Licence (EUPL v.1.0). You should have received a copy of the EUPL licence along with this program.  If not, see http://ec.europa.eu/idabc/en/document/7330.
*/
// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.dri.negocio.servicios.DRI;

import java.rmi.RemoteException;
import java.util.Date;

import javax.activation.DataHandler;

import org.apache.log4j.Logger;

import es.pode.dri.negocio.servicios.SesionVO;
import es.pode.entregar.negocio.servicios.PaquetePifDriVO;
import es.pode.publicacion.negocio.servicios.ResultadoOperacionVO;
import es.pode.soporte.seguridad.encriptacion.Autenticar;
import es.pode.soporte.seguridad.encriptacion.EncriptacionUtiles;
import es.pode.soporte.seguridad.ldap.LdapUserDetailsUtils;
import es.pode.soporte.utiles.date.DateManager;


/**
 * @see es.pode.dri.negocio.servicios.DRI.SrvDRIService
 */

public class SrvDRIServiceImpl
    extends es.pode.dri.negocio.servicios.DRI.SrvDRIServiceBase
{

	private Logger logger = Logger.getLogger(this.getClass());

	private final String CARGA_DRI = "Carga DRI";
	private final String CATALOGA_DRI = "Cataloga DRI";
	private final String SIN_ERRORES = "0.0";
	private final String ZIP=".zip";
	private final static String ROLE_DOCENTE = "ROLE_DOCENTE";
	private final static String ROLE_ADMINISTRADOR = "ROLE_ADMINISTRADOR";
	private final static String ROLE_CATALOGADOR = "ROLE_CATALOGADOR";
	private final static String ROLE_PUBLICADOR = "ROLE_PUBLICADOR";
	
	/**
	 * Este metodo invoca el publicarPIF del servicio de publicacion.
     * @param usuario Usuario perteneciente a la plataforma. 
     * @param clave Clave del usuario dentro de la platafoma.
     * @param pif Fichero del ODE en formato zip.   
     * @throws Exception
     *      
     */
	protected void handlePresentarAlmacenar(String usuario, String clave, DataHandler pif) throws Exception {
//		Higiene de datos
		if (usuario == null || usuario.equals(""))
		{
//			ServicioDRIException e = new ServicioDRIException("Error almacenando pif["+(pif!= null? pif.getName():null)+"] con usuario vacio.");
        	logger.error("Error almacenando pif["+(pif!= null? pif.getName():null)+"] con usuario vacio.");
//        	throw e;
        	return;
		}
		if (clave == null || clave.equals(""))
		{
//			ServicioDRIException e = new ServicioDRIException("Error almacenando pif["+(pif!= null? pif.getName():null)+"] con usuario ["+usuario+"] clave vacia.");
        	logger.error("Error almacenando pif["+(pif!= null? pif.getName():null)+"] con usuario ["+usuario+"] clave vacia.");
//        	throw e;
        	return;
		}
		if (pif == null || pif.getInputStream() == null)
		{
//			ServicioDRIException e = new ServicioDRIException("Error almacenando pif["+(pif!= null? pif.getName():null)+"] con usuario ["+usuario+"] y fichero vacio["+pif.getName()+"].");
        	logger.error("Error almacenando pif["+(pif!= null? pif.getName():null)+"] con usuario ["+usuario+"] y fichero vacio["+pif.getName()+"].");
//        	throw e;
        	return;
		}
    	//Comprobamos si existe el usuario
		
		
		String[] roles=new String[1];
		roles[0]=ROLE_PUBLICADOR;
		logger.debug("Entramos en añadir seguridad con los roles necesarios para publicador" +roles.length);
		Integer vueltaValidacion=Autenticar.validarUsuarioRoles(usuario,clave,roles);
		
		if (!vueltaValidacion.equals(1) && vueltaValidacion.equals(2))
		{
			String[] rolesAdmin=new String[1];
			rolesAdmin[0]=ROLE_ADMINISTRADOR;
			logger.debug("Entramos en añadir seguridad con los roles necesarios para administrador puesto que la validación con docente ha sido" +vueltaValidacion);
			Integer vueltaValidacionAdministrador=Autenticar.validarUsuarioRoles(usuario,clave,rolesAdmin);
			if (vueltaValidacionAdministrador.equals(2))//No nos vale que sea DOCENTE y ADMINISTRADOR, es publicador O administrador
			{
				
				logger.debug("Devolvemos el error "+ vueltaValidacionAdministrador+" al hacer la validación de administrador");
//				ServicioDRIException e = new ServicioDRIException("Error presentado almacenar pif["+pif.getName()+"] con usuario ["+usuario+"] . El usuario no esta autenticado en el sistema o no tiene los roles necesarios.");
				logger.error("Error presentado almacenar pif["+pif.getName()+"] con usuario ["+usuario+"] . El usuario no esta autenticado en el sistema o no tiene los roles necesarios.");
//				throw e;
				return;
			}
		}
		else if(vueltaValidacion.equals(1)){
//			ServicioDRIException e = new ServicioDRIException("Devolvemos el error "+ vueltaValidacion+" al hacer la validación de publicador");
			logger.debug("Devolvemos el error "+ vueltaValidacion+" al hacer la validación de publicador");
//			throw e;
			
		}
		
//		
//		if (!Autenticar.validarUsuarioClaveLdap(usuario, clave))
//		{
//			ServicioDRIException e = new ServicioDRIException("Error almacenando pif["+pif.getName()+"] con usuario ["+usuario+"] ]. El usuario no esta autenticado en el sistema.");
//			logger.error(e);
//			throw e;
//		}
		ResultadoOperacionVO res;
		String titulo=null;
		try {
//			Añadimos seguridad a la transaccion
			logger.info("Presentamos-Almacenamos antes de meter la mochila");
			Autenticar.anadirSeguridad(usuario,clave);
			logger.info("Presentamos-Almacenamos fichero pif["+pif.getName()+"] al repositorio con usuario["+usuario+"]  ");
			titulo=obtenerNombre(usuario);
			res = this.getSrvPublicacionService().publicarPIF(pif, usuario, CARGA_DRI,"n",titulo);
		} catch (RuntimeException e1) {
//			ServicioDRIException e =new ServicioDRIException("Excepcion almacenando pif["+pif.getName()+"] con usuario ["+usuario+"] y titulo ["+titulo+"] desde interfaz DRI.",e1);
    		logger.warn("Excepcion almacenando pif["+pif.getName()+"] con usuario ["+usuario+"] y titulo ["+titulo+"] desde interfaz DRI. "+e1);
//    		throw e;
    		return;
		}
    	if (!res.getIdResultado().equals(SIN_ERRORES))
    	{
//    		ServicioDRIException e =new ServicioDRIException("Error almacenando pif["+pif.getName()+"] con usuario ["+usuario+"] y titulo ["+titulo+"] desde interfaz DRI["+res.getIdResultado()+"]->["+res.getDescripcion()+"]");
    		logger.warn("Error almacenando pif["+pif.getName()+"] con usuario ["+usuario+"] y titulo ["+titulo+"] desde interfaz DRI["+res.getIdResultado()+"]->["+res.getDescripcion()+"]");
//    		throw e;
    	}
    	logger.info("Almacenado pif["+pif.getName()+"] con exito con usuario ["+usuario+"] y titulo ["+titulo+"]");
	}

	/**
	 * Este metodo invoca el publicarPIF del servicio de publicacion.
     * @param sesionId Identificador de la sesion que hay que haber inicializado. 
     * @param pif contenido del ODE en formato PIF.   
     * @throws Exception
     *      
     */
	protected void handlePresentarAlmacenarSesion(String sesionId, DataHandler pif) throws Exception {
        //TODO: No usar hasta que se valide el usuario y la clave contra el LDAP en la creación de la sesión: SrvSesionesServiceImpl.handleCreateSession(...)
        SesionVO sesion = this.getSrvSesionesService().consultaSesion(sesionId);
        if (sesion==null) 
        { 
//        	ServicioDRIException e = new ServicioDRIException("Error almacenando pif["+(pif!= null? pif.getName():null)+"]. No existe sesión con el id["+sesionId+"].");
        	logger.warn("Error almacenando pif["+(pif!= null? pif.getName():null)+"]. No existe sesión con el id["+sesionId+"].");
//        	throw e;
        	return;
        } 
        if (sesion.getFechaExpiracion().before(new Date())) 
        {
//        	ServicioDRIException e = new ServicioDRIException("Error almacenando pif["+(pif!= null? pif.getName():null)+"]. La sesión con el id["+sesionId+"] ha expirado.");
        	logger.warn("Error almacenando pif["+(pif!= null? pif.getName():null)+"]. La sesión con el id["+sesionId+"] ha expirado.");
//        	throw e;
        	return;
        } 
        ResultadoOperacionVO res;
        try {
//			Delegamos en el metodo seguro con usuario y clave la resolucion de tema
        	logger.info("Presentamos-Almacenamos fichero pif["+pif.getName()+"] al repositorio con id sesion["+sesionId+"] usuario["+sesion.getUsuario()+"]");
            // TODO: Hay que cambiar por un método similar que no haga falta la clave ya que la sesion ya ha sido validada con usuario y clave
        	handlePresentarAlmacenar(sesion.getUsuario(), EncriptacionUtiles.desencriptar(sesion.getClave()),pif);
        } catch (RuntimeException e1) {
//        	ServicioDRIException e =new ServicioDRIException("Excepcion almacenando pif["+(pif!= null? pif.getName():null)+"] con id sesion["+sesionId+"] desde interfaz DRI.",e1);
        	logger.warn("Excepcion almacenando pif["+(pif!= null? pif.getName():null)+"] con id sesion["+sesionId+"] desde interfaz DRI. "+e1);
//        	throw e;
        	return;
        }
        logger.info("Almacenado pif con exito con id sesion["+sesionId+"]");
	}

    /**
	 * Este metodo devuelve un ODE en formato PIF. 
     * @param usuario Usuario perteneciente a la plataforma. 
     * @param clave Clave del usuario dentro de la platafoma.
     * @param mec Identificador del ODE que se quiere obtener.   
     * @return se retorna el ODE.
     * @throws RemoteException 
     * @throws Exception
     *      
     */
	private DataHandler solicitarEntregar( String mec) throws RemoteException, Exception {

//		Higiene de datos

		if (mec == null || mec.equals(""))
		{
//			ServicioDRIException e = new ServicioDRIException("Error, mec vacio.");
        	logger.error("Error, mec vacio.");
//        	throw e;
        	return null;
		}
    	
		try {
			//		Añadimos seguridad a la transaccion
			logger.info("Solicitando ODE con mec"+mec+"]");
			PaquetePifDriVO respuestaDri= this.getSrvEntregarService().generarPaquetePIF(mec);
			DataHandler respuesta = respuestaDri.getPaquetePIF();
			if (respuesta == null)
			{
				logger.error("Error solicitando con  mec["+mec+"] "+ respuestaDri.getResultadoValidacion().getResultadoValidacion());
//				throw new Exception(respuestaDri.getResultadoValidacion().getResultadoValidacion());
				return null;
			}
			return respuesta;
		} catch (RuntimeException e) {
//			ServicioDRIException e1 = new ServicioDRIException("Excepcion solicitando con mec ["+mec+"].", e);
        	logger.error("Excepcion solicitando con mec ["+mec+"]. "+e);
//        	throw e1;
        	return null;
		}
	}

    /**
	 * Este metodo devuelve un ODE en formato PIF. 
     * @param sesionId Identificador de la sesion que hay que haber inicializado. 
     * @param mec Identificador del ODE que se quiere obtener.   
     * @return se retorna el ODE.
     * @throws Exception
     *      
     */
	protected DataHandler handleSolicitarEntregarSesion(String sesionId, String mec) throws Exception {
		
        SesionVO sesion = this.getSrvSesionesService().consultaSesion(sesionId);
        if (sesion==null) 
        { 
//        	ServicioDRIException e = new ServicioDRIException("Error solicitando mec["+mec+"]. No existe sesión con el id["+sesionId+"]");
        	logger.warn("Error solicitando mec["+mec+"]. No existe sesión con el id["+sesionId+"]");
//        	throw e;
        	return null;
        } 

        if (sesion.getFechaExpiracion().before(DateManager.dateToCalendar(new Date()))) 
        {
//        	ServicioDRIException e = new ServicioDRIException("Error solicitando mec["+mec+"]. La sesión con el id["+sesionId+"] ha expirado");
        	logger.warn("Error solicitando mec["+mec+"]. La sesión con el id["+sesionId+"] ha expirado");
//        	throw e;
        	return null;
        } 
        try {
        	logger.info("Solicitando paquete pif "+mec);
        	return solicitarEntregar(mec);
        }  catch (RuntimeException e) {
//			ServicioDRIException e1 = new ServicioDRIException("Excepcion solicitando con sesion["+sesionId+"] usuario["+sesion.getUsuario()+"] y mec ["+mec+"].", e);
        	logger.error("Excepcion solicitando con sesion["+sesionId+"] usuario["+sesion.getUsuario()+"] y mec ["+mec+"]."+ e);
//        	throw e1; 
        	return null;
		}

	}

	/** Para saber si el servicio está operativo
	 * 
	 * @return Boolean
	 * @throws Exception
	 * */
	protected Boolean handleEstasActivo() throws Exception {
		// TODO Auto-generated method stub
		return Boolean.TRUE;
	}

	/**
	 * Este metodo recoge un ODE y lo introduce en el ciclo de vida de la plataforma en el estado para catalogar en el que un catalogador ha de realizar su labor sobre el ODE.
	 * Si se ha subido bien a catalogar devolverá un 0;
	 * Si el usuario no está dado de alta en la plataforma se devolverá un 1;
	 * Si el rol del usuario no es docente o administrador devolverá un 2;
	 * Si hay errores de validación devolvemos un 4;
	 * Si la url dada no es válida saltará un error de Conexion que luego capturamos.
     * @param usuario Usuario perteneciente a la plataforma. 
     * @param clave Clave del usuario dentro de la platafoma.
     * @param pif Fichero del ODE en formato zip.   
     * @return En caso de exito, no se retorna nada.
     * @throws Exception
     *      
     */
	protected Integer handlePresentarCatalogar(String usuario, String clave, DataHandler pif) throws Exception {
		// //		Higiene de datos
		Integer vuelta=0;
		if (usuario == null || usuario.equals(""))
		{
//			ServicioDRIException e = new ServicioDRIException("Error presentado catalogando pif["+(pif!= null? pif.getName():null)+"] con usuario vacio.");
        	logger.error("Error presentado catalogando pif["+(pif!= null? pif.getName():null)+"] con usuario vacio.");
//        	throw e;
        	return 1;
		}
		if (clave == null || clave.equals(""))
		{
//			ServicioDRIException e = new ServicioDRIException("Error presentado catalogando pif["+(pif!= null? pif.getName():null)+"] con usuario ["+usuario+"] clave vacia.");
        	logger.error("Error presentado catalogando pif["+(pif!= null? pif.getName():null)+"] con usuario ["+usuario+"] clave vacia.");
//        	throw e;
        	return 1;
		}
		if (pif == null || pif.getInputStream() == null)
		{
//			ServicioDRIException e = new ServicioDRIException("Error presentado catalogando pif["+(pif!= null? pif.getName():null)+"] con usuario ["+usuario+"] y fichero vacio["+pif.getName()+"].");
        	logger.error("Error presentado catalogando pif["+(pif!= null? pif.getName():null)+"] con usuario ["+usuario+"] y fichero vacio["+pif.getName()+"].");
//        	throw e;
        	return 4;
		}
//		Comprobamos si existe el usuario
		String[] roles=new String[1];
		roles[0]=ROLE_DOCENTE;
		logger.debug("Entramos en añadir seguridad con los roles necesarios para docente" +roles.length);
		Integer vueltaValidacion=Autenticar.validarUsuarioRoles(usuario,clave,roles);
		if (vueltaValidacion.equals(2))//No nos vale que sea DOCENTE y ADMINISTRADOR, es docente O administrador
		{
			
			logger.debug("Devolvemos el error "+ vueltaValidacion+" al hacer la validación de docente");
//			ServicioDRIException e = new ServicioDRIException("Error presentado crear pif["+pif.getName()+"] con usuario ["+usuario+"] . El usuario no esta autenticado en el sistema o no tiene los roles necesarios.");
			logger.error("Error presentado crear pif["+pif.getName()+"] con usuario ["+usuario+"] . El usuario no esta autenticado en el sistema o no tiene los roles necesarios.");
			return vueltaValidacion;
//			throw e;
		}
//		if (!vueltaValidacion.equals(1) && vueltaValidacion.equals(2))
//		{
//			String[] rolesAdmin=new String[1];
//			rolesAdmin[0]=ROLE_ADMINISTRADOR;
//			logger.debug("Entramos en añadir seguridad con los roles necesarios para administrador puesto que la validación con docente ha sido" +vueltaValidacion);
//			Integer vueltaValidacionAdministrador=Autenticar.validarUsuarioRoles(usuario,clave,rolesAdmin);
//			if (vueltaValidacionAdministrador.equals(2))//No nos vale que sea DOCENTE y ADMINISTRADOR, es docente O administrador
//			{
//				
//				logger.debug("Devolvemos el error "+ vueltaValidacionAdministrador+" al hacer la validación de administrador");
//				ServicioDRIException e = new ServicioDRIException("Error presentado crear pif["+pif.getName()+"] con usuario ["+usuario+"] . El usuario no esta autenticado en el sistema o no tiene los roles necesarios.");
//				logger.error(e);
//				return vueltaValidacionAdministrador;
////				throw e;
//			}
		else if(vueltaValidacion.equals(1)){
			logger.debug("Devolvemos el error "+ vueltaValidacion+" al hacer la validación de docente");
			return vueltaValidacion;
			
		}
		ResultadoOperacionVO res;
		String titulo=null;
//		try {
//			Añadimos seguridad a la transaccion
			logger.info("Presentamos-Catalogamos antes de meter la mochila");
			Autenticar.anadirSeguridad(usuario,clave);
			logger.info("Presentamos-Catalogamos fichero pif["+pif.getName()+"] al repositorio con usuario["+usuario+"] ");
			titulo=this.obtenerNombre(usuario);
			res = this.getSrvPublicacionService().crearPIFCatalogado(pif, usuario, CATALOGA_DRI, titulo);
//		} catch (RuntimeException e1) {
//			ServicioDRIException e =new ServicioDRIException("Excepcion presentado catalogando pif["+pif.getName()+"] con usuario ["+usuario+"] y titulo ["+titulo+"] desde interfaz DRI.",e1);
//    		logger.warn(e);
//    		throw e;
//		}
    	if (!res.getIdResultado().equals(SIN_ERRORES))
    	{
//    		vuelta=new Integer(4);
//    		ServicioDRIException e =new ServicioDRIException("Error presentado catalogando pif["+pif.getName()+"] con usuario ["+usuario+"] y titulo ["+titulo+"] desde interfaz DRI["+res.getIdResultado()+"]->["+res.getDescripcion()+"]");
    		logger.warn("Error presentado catalogando pif["+pif.getName()+"] con usuario ["+usuario+"] y titulo ["+titulo+"] desde interfaz DRI["+res.getIdResultado()+"]->["+res.getDescripcion()+"]");
    		return 5;
//    		throw e;
    	}
    	logger.info("Catalogado pif["+pif.getName()+"] con exito con usuario ["+usuario+"] y titulo "+titulo+"]");
    	logger.debug("La vuelta de handlePresentarCatalogar es "+ vuelta);
    	return vuelta;
		
	}

	/**
	 * Este metodo recoge un ODE y lo introduce en el ciclo de vida de la plataforma en el estado catalogar en el que un catalogador ha de realizar su labor sobre el ODE.
	 * Si se ha subido bien a catalogar devolverá un 0;
	 * Si el usuario no está dado de alta en la plataforma se devolverá un 1;
	 * Si el rol del usuario no es docente o administrador devolverá un 2;
	 * Si hay errores de validación devolvemos un 4;
	 * Si la url dada no es correcta salta un error de Conexion que luego capturamos
     * @param sesionId Identificador de la sesion que hay que haber inicializado. 
     * @param pif contenido del ODE en formato ZIP.   
     * @return En el caso de exito no se retorna nada.
     * @throws Exception
     *      
     */
	protected Integer handlePresentarCatalogarSesion(String sesionId, DataHandler pif) throws Exception {

        //TODO: No usar hasta que se valide el usuario y la clave contra el LDAP en la creación de la sesión: SrvSesionesServiceImpl.handleCreateSession(...)
		Integer vuelta=0;
        SesionVO sesion = this.getSrvSesionesService().consultaSesion(sesionId);
        if (sesion==null) 
        { 
//        	ServicioDRIException e = new ServicioDRIException("Error presentado catalogando pif["+(pif!= null? pif.getName():null)+"]. No existe sesión con el id["+sesionId+"].");
        	logger.warn("Error presentado catalogando pif["+(pif!= null? pif.getName():null)+"]. No existe sesión con el id["+sesionId+"].");
//        	throw e;
        	return 1;
        } 
        if (sesion.getFechaExpiracion().before(new Date())) 
        {
//        	ServicioDRIException e = new ServicioDRIException("Error presentado catalogando pif["+(pif!= null? pif.getName():null)+"]. La sesión con el id["+sesionId+"] ha expirado.");
        	logger.warn("Error presentado catalogando pif["+(pif!= null? pif.getName():null)+"]. La sesión con el id["+sesionId+"] ha expirado.");
//        	throw e;
        	return 1;
        } 
        ResultadoOperacionVO res;
//        try {
//			Delegamos en el metodo seguro con usuario y clave la resolucion de tema
        	logger.info("Presentamos-Catalogamos fichero pif["+pif.getName()+"] al repositorio con id sesion["+sesionId+"] usuario["+sesion.getUsuario()+"]");
            // TODO: Hay que cambiar por un método similar que no haga falta la clave ya que la sesion ya ha sido validada con usuario y clave
        	handlePresentarCatalogar(sesion.getUsuario(), EncriptacionUtiles.desencriptar(sesion.getClave()),pif);
//        } catch (RuntimeException e1) {
//        	ServicioDRIException e =new ServicioDRIException("Excepcion presentado catalogando pif["+(pif!= null? pif.getName():null)+"] con id sesion["+sesionId+"] desde interfaz DRI.",e1);
//        	logger.warn(e);
//        	throw e;
//        }
        logger.info("Catalogado pif con exito con id sesion["+sesionId+"]");
        return vuelta;
	}
	
	private String obtenerNombre(String usuario){
		
		String valor=String.valueOf(System.currentTimeMillis());
		String nombre=usuario+valor+ZIP;
		return nombre;
	}

	/**
	 * Este método recoge un ODE y lo introduce en el ciclo de vida de la plataforma en el estado creado en el que un docente o adminstrador ha de realizar su labor sobre el ODE.
	 * Si se ha subido bien a la carpeta personal del usuario devolverá un 0;
	 * Si el usuario no está dado de alta en la plataforma se devolverá un 1;
	 * Si el rol del usuario no es docente o administrador devolverá un 2;
	 * Si la cuota se excede devolverá un 3;
	 * Si hay errores de validación devolvemos un 4;
	 * Si la url dada no es correcta salta un error de Conexion que luego capturamos
     * @param usuario Usuario perteneciente a la plataforma. 
     * @param clave Clave del usuario dentro de la platafoma.
     * @param pif Fichero del ODE en formato zip.   
     * @return Identificador de error
     * @throws Exception
     *      
     */
	protected Integer handleCrear(String usuario, String clave, DataHandler pif) throws Exception {
//		 //		Higiene de datos
		Integer vuelta=new Integer(0);
		if (usuario == null || usuario.equals(""))
		{
//			ServicioDRIException e = new ServicioDRIException("Error presentado crear pif["+(pif!= null? pif.getName():null)+"] con usuario vacio.");
        	logger.error("Error presentado crear pif["+(pif!= null? pif.getName():null)+"] con usuario vacio.");
//        	throw e;
        	return 1;
		}
		if (clave == null || clave.equals(""))
		{
//			ServicioDRIException e = new ServicioDRIException("Error presentado crear pif["+(pif!= null? pif.getName():null)+"] con usuario ["+usuario+"] clave vacia.");
        	logger.error("Error presentado crear pif["+(pif!= null? pif.getName():null)+"] con usuario ["+usuario+"] clave vacia.");
//        	throw e;
        	return 1;
		}
		if (pif == null || pif.getInputStream() == null)
		{
//			ServicioDRIException e = new ServicioDRIException("Error presentado crear pif["+(pif!= null? pif.getName():null)+"] con usuario ["+usuario+"] y fichero vacio["+pif.getName()+"].");
        	logger.error("Error presentado crear pif["+(pif!= null? pif.getName():null)+"] con usuario ["+usuario+"] y fichero vacio["+pif.getName()+"].");
//        	throw e;
        	return 4;
		}
    	//Comprobamos si existe el usuario
		String[] roles=new String[1];
		roles[0]=ROLE_DOCENTE;
		logger.debug("Entramos en añadir seguridad con los roles necesarios para docente" +roles.length);
		Integer vueltaValidacion=Autenticar.validarUsuarioRoles(usuario,clave,roles);
		if (!vueltaValidacion.equals(1) && vueltaValidacion.equals(2))
		{
			String[] rolesAdmin=new String[1];
			rolesAdmin[0]=ROLE_ADMINISTRADOR;
			logger.debug("Entramos en añadir seguridad con los roles necesarios para administrador puesto que la validación con docente ha sido" +vueltaValidacion);
			Integer vueltaValidacionAdministrador=Autenticar.validarUsuarioRoles(usuario,clave,rolesAdmin);
			if (vueltaValidacionAdministrador.equals(2))//No nos vale que sea DOCENTE y ADMINISTRADOR, es docente O administrador
			{
				
				logger.debug("Devolvemos el error "+ vueltaValidacionAdministrador+" al hacer la validación de administrador");
//				ServicioDRIException e = new ServicioDRIException("Error presentado crear pif["+pif.getName()+"] con usuario ["+usuario+"] . El usuario no esta autenticado en el sistema o no tiene los roles necesarios.");
				logger.error("Error presentado crear pif["+pif.getName()+"] con usuario ["+usuario+"] . El usuario no esta autenticado en el sistema o no tiene los roles necesarios.");
				return vueltaValidacionAdministrador;
//				throw e;
			}
		}else if(vueltaValidacion.equals(1)){
			logger.debug("Devolvemos el error "+ vueltaValidacion+" al hacer la validación de docente");
			return vueltaValidacion;
			
		}
		ResultadoOperacionVO res;
		String titulo=null;
//		try {
//			Añadimos seguridad a la transaccion
			logger.info("Presentamos-Creamos antes de meter la mochila");
			
			Autenticar.anadirSeguridad(usuario,clave);
			logger.info("Presentamos-Creamos fichero pif["+pif.getName()+"] al repositorio con usuario["+usuario+"] ");
			titulo=this.obtenerNombre(usuario);
			String idioma=LdapUserDetailsUtils.getIdioma();
			logger.debug("El idioma que recibimos es "+idioma);
			res = this.getSrvPublicacionService().crearPifConCuota(pif, usuario, CATALOGA_DRI, titulo, idioma);

//		} catch (RuntimeException e1) {
//			ServicioDRIException e =new ServicioDRIException("Excepcion presentado crear pif["+pif.getName()+"] con usuario ["+usuario+"] y titulo ["+titulo+"] desde interfaz DRI.",e1);
//    		logger.warn(e);
//    		throw e;
//		}
    	if (!res.getIdResultado().equals(SIN_ERRORES)&& res.getIdResultado().equals("10.17"))
    	{
    		vuelta=3;
//    		ServicioDRIException e =new ServicioDRIException("Error presentado crear pif["+pif.getName()+"] con usuario ["+usuario+"] y titulo ["+titulo+"] desde interfaz DRI["+res.getIdResultado()+"]->["+res.getDescripcion()+"]");
    		logger.warn("Error presentado crear pif["+pif.getName()+"] con usuario ["+usuario+"] y titulo ["+titulo+"] desde interfaz DRI["+res.getIdResultado()+"]->["+res.getDescripcion()+"]");
//    		throw e;
    	}else if(!res.getIdResultado().equals(SIN_ERRORES)){
    		//Como se hace un throw, no se devuelve nada
//    		vuelta=new Integer(4);
//    		ServicioDRIException e =new ServicioDRIException("Error presentado crear pif["+pif.getName()+"] con usuario ["+usuario+"] y titulo ["+titulo+"] desde interfaz DRI["+res.getIdResultado()+"]->["+res.getDescripcion()+"]");
    		logger.warn("Error presentado crear pif["+pif.getName()+"] con usuario ["+usuario+"] y titulo ["+titulo+"] desde interfaz DRI["+res.getIdResultado()+"]->["+res.getDescripcion()+"]");
//    		throw e;
    	}
    	logger.info("Creado pif["+pif.getName()+"] con exito con usuario ["+usuario+"] y titulo "+titulo+"]");
    	logger.debug("La vuelta de handleCrear es "+ vuelta);
    	return vuelta;
	}

	/**
	 * Este metodo recoge un ODE y lo introduce en el ciclo de vida de la plataforma en el estado creado en el que un docente o adminstrador ha de realizar su labor sobre el ODE.
	 * Si se ha subido bien a la carpeta personal del usuario devolverá un 0;
	 * Si el usuario no está dado de alta en la plataforma se devolverá un 1;
	 * Si el rol del usuario no es docente o administrador devolverá un 2;
	 * Si la cuota se excede devolverá un 3;
	 * Si hay errores de validación devolvemos un 4;
	 * Si la url dada no es correcta salta un error de Conexion que luego capturamos
     * @param sesionId Identificador de la sesion que hay que haber inicializado. 
     * @param pif Fichero del ODE en formato zip.   
     * @return Identificador de error
     * @throws Exception
     *      
     */
	protected Integer handleCrearSesion(String sesionId, DataHandler pif) throws Exception {
//		TODO: No usar hasta que se valide el usuario y la clave contra el LDAP en la creación de la sesión: SrvSesionesServiceImpl.handleCreateSession(...)
		Integer vueltaCrear=0;
        SesionVO sesion = this.getSrvSesionesService().consultaSesion(sesionId);
        if (sesion==null) 
        { 

//        	ServicioDRIException e = new ServicioDRIException("Error presentado crear pif["+(pif!= null? pif.getName():null)+"]. No existe sesión con el id["+sesionId+"].");
        	logger.warn("Error presentado crear pif["+(pif!= null? pif.getName():null)+"]. No existe sesión con el id["+sesionId+"].");
//        	throw e;
        	return 1;
        } 
        if (sesion.getFechaExpiracion().before(new Date())) 
        {

//        	ServicioDRIException e = new ServicioDRIException("Error presentado crear pif["+(pif!= null? pif.getName():null)+"]. La sesión con el id["+sesionId+"] ha expirado.");
        	logger.warn("Error presentado crear pif["+(pif!= null? pif.getName():null)+"]. La sesión con el id["+sesionId+"] ha expirado.");
//        	throw e;
        	return 1;
        } 
        ResultadoOperacionVO res;
//        try {
//			Delegamos en el metodo seguro con usuario y clave la resolucion de tema
        	logger.info("Presentamos-Creamos fichero pif["+pif.getName()+"] al repositorio con id sesion["+sesionId+"] usuario["+sesion.getUsuario()+"]");
            // TODO: Hay que cambiar por un método similar que no haga falta la clave ya que la sesion ya ha sido validada con usuario y clave
        	vueltaCrear=handleCrear(sesion.getUsuario(), EncriptacionUtiles.desencriptar(sesion.getClave()),pif);
//        } catch (RuntimeException e1) {
//        	
//        	ServicioDRIException e =new ServicioDRIException("Excepcion presentado crear pif["+(pif!= null? pif.getName():null)+"] con id sesion["+sesionId+"] desde interfaz DRI.",e1);
//        	logger.warn(e);
//        	throw e;
//        }
        logger.info("Creado pif con exito con id sesion["+sesionId+"]");
        return vueltaCrear;
		
	}

	
}
