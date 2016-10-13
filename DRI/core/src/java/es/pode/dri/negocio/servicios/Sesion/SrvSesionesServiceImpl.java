// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.dri.negocio.servicios.Sesion;

import java.util.ArrayList;
import java.util.Date;

import es.pode.dri.negocio.dominio.Sesion;
import es.pode.dri.negocio.dominio.SesionDao;
import es.pode.dri.negocio.servicios.SesionVO;
import es.pode.soporte.seguridad.encriptacion.Autenticar;
import es.pode.soporte.seguridad.encriptacion.EncriptacionUtiles;
import es.pode.soporte.utiles.date.DateManager;


/**
 * @see es.pode.dri.negocio.servicios.Sesion.SrvSesionesService
 */

public class SrvSesionesServiceImpl
    extends es.pode.dri.negocio.servicios.Sesion.SrvSesionesServiceBase
{
//	private Logger logger = Logger.getLogger(this.getClass());
	
	private final String CLAVE_SESION_ANONIMA = "anonymous";

	private final long tiempoExpirar = 300000;

	 /**
	 * Crea una nueva sesion asociada al usuario.
	 * Chequea contra el autenticador la identidad del usuario que intenta crear una conexion.
     * @param userID Identificador del usuario. 
     * @param password Contraseña del usuario.   
     * @return se retorna el identificador de la sesión creada.
     * @throws Exception
     *      
     */
    protected java.lang.String handleCreateSession(java.lang.String userID, java.lang.String password)
        throws java.lang.Exception
    {
        //TODO Validar el usuario y la clave contra el LDAP sin utilizar la encriptación
    	//Validamos al usuaro contra el sistema.
    	String claveEncriptada = EncriptacionUtiles.encriptar(password);
    	if(Autenticar.validarUsuarioClaveLdap(userID, password))
    	{
    	//if (Autenticar.validarUsuarioClave(userID, claveEncriptada))
    	//{
    		SesionDao sesionDao = this.getSesionDao();
    		SesionVO sesionVO = new SesionVO();
    		sesionVO.setAnonima(Boolean.FALSE);
    		sesionVO.setFechaCreacion(DateManager.dateToCalendar(new Date(System.currentTimeMillis())));
    		sesionVO.setFechaExpiracion(DateManager.dateToCalendar(new Date(System.currentTimeMillis()+tiempoExpirar)));
    		sesionVO.setUsuario(userID);
    		sesionVO.setClave(claveEncriptada);
    		Sesion sesion = sesionDao.fromSesionVO(sesionVO);
    		sesionDao.create(sesion);
    		logger.info("Sesión creada para usuario["+userID+"] autenticado en la plataforma.");
    		return sesion.getIdSesion();
    	}
		logger.info("Sesión no creada para usuario["+userID+"] y clave["+password+"]. No esta autenticado en la plataforma.");
		return null;
    }

    /**
	 * Crea una sesion sin usuario definido o con usuario anonimo.      
     * @return se retorna el identificador de la sesión creada.
     * @throws Exception
     *      
     */
    protected java.lang.String handleCreateAnonymousSession()
        throws java.lang.Exception
    {
    	String anonName = "anonimo";
        SesionDao sesionDao = this.getSesionDao();
        SesionVO sesionVO = new SesionVO();
        sesionVO.setAnonima(Boolean.TRUE);
        sesionVO.setFechaCreacion(DateManager.dateToCalendar(new Date(System.currentTimeMillis())));
        sesionVO.setFechaExpiracion(DateManager.dateToCalendar(new Date(System.currentTimeMillis()+tiempoExpirar)));
        sesionVO.setUsuario(anonName);
        sesionVO.setClave(this.CLAVE_SESION_ANONIMA);
        Sesion sesion = sesionDao.fromSesionVO(sesionVO);
        sesionDao.create(sesion);
        logger.info("Sesión anonima creada.");
        return sesion.getIdSesion();
    }

    /**
	 * Este metodo limpia las sesiones caducadas almacenadas en la tabla de sesiones.     
     * @throws Exception
     *      
     */
    protected void handleLimpiarSesiones()
        throws java.lang.Exception
    {
    	SesionDao sesionDao = this.getSesionDao();
    	ArrayList allSesions = (ArrayList) sesionDao.loadAll(SesionDao.TRANSFORM_NONE);
    	for (int i=0; i<allSesions.size(); i++) {
    		Sesion sesion = (Sesion) allSesions.get(i);
    		if (sesion.getFechaExpiracion().after(new Date(System.currentTimeMillis()))) {
    			if (logger.isDebugEnabled())
    				logger.debug("Eliminado sesion caducada con fecha expiracion["+sesion.getFechaExpiracion()+"] usuario["+sesion.getUsuario()+"]");
    			sesionDao.remove(sesion);
    		}
    	}
    }

    /**
	 * Borra una sesion del sistema.
     * @param sessionID Identificador de la sesion que se quiere eliminar.     
     * @throws Exception
     *      
     */
    protected void handleDestroySession(java.lang.String sessionID)
        throws java.lang.Exception
    {
    	SesionDao sesionDao = this.getSesionDao();
    	logger.info("Eliminando sesion con id["+sessionID+"]");
        sesionDao.remove(sessionID);
    }

    /**
	 * Este metodo devuelve una sesion.
     * @param id Identificador de la sesion.     
     * @return se retorna la sesion con el identificador.
     * @throws Exception
     *      
     */
    protected es.pode.dri.negocio.servicios.SesionVO handleConsultaSesion(java.lang.String id)
        throws java.lang.Exception
    {
        SesionDao sesionDao = this.getSesionDao();
        Sesion sesion = sesionDao.load(String.valueOf(id));
		if (sesion!=null) {
			SesionVO sesionVO = sesionDao.toSesionVO(sesion);
			if (sesionVO.getFechaExpiracion().after(DateManager.dateToCalendar(new Date(System.currentTimeMillis())))) {
				sesion.setFechaExpiracion(DateManager.dateToCalendar(new Date(System.currentTimeMillis()+ tiempoExpirar)));
				sesionDao.update(sesion);
				return sesionVO;
			}
//			SesionNoValidaException e = new SesionNoValidaException("La sesión ha expirado. ID[" + id+"]");
//			logger.warn("Intento de acceso a sesión expirada id["+id+"]",e);
			logger.warn("Intento de acceso a sesión expirada id["+id+"]");
//			throw e; //TODO ¿Se podría cambiar por null?
			return null;
		}
//		SesionNoValidaException e = new SesionNoValidaException("No se ha encontrado la sesión con id[" + id+"]");
//		logger.warn("No se ha encontrado la sesión con id["+ id+"]", e);
		logger.warn("No se ha encontrado la sesión con id["+ id+"]");
		return null;
		//throw e;
		//TODO Comprobar que el null no causa males mayores
    }

    /**
	 * Este metodo modifica una sesion.
	 * Se tiene que comprobar que dicha sesion existe antes de modificarla.
     * @param sesion Sesion que se tiene que modificar con los parametros modificados.   
     * @return se retorna la sesion modificada.
     * @throws Exception
     *      
     */
    protected es.pode.dri.negocio.servicios.SesionVO handleModificarSesion(es.pode.dri.negocio.servicios.SesionVO sesion)
        throws java.lang.Exception
    {
        SesionDao sesionDao = this.getSesionDao();
//        Sesion ses = sesionDao.load(sesion.getIdSesion());
        if(sesion == null)
        {
//        	logger.warn("Error intentando modificar sesion con id["+sesion.getIdSesion()+"] de usuario["+sesion.getUsuario()+"]");
//        	throw new SesionNoValidaException("Error intentando modificar sesion con id["+sesion.getIdSesion()+"] de usuario["+sesion.getUsuario()+"]");
        	logger.warn("Error intentando modificar sesion");
//        	throw new SesionNoValidaException("Error intentando modificar sesion");
        	return null;
        }
        Sesion ses = sesionDao.load(sesion.getIdSesion());
//        Comprobamos que existen todos los parametros que exigimos para la actualizacion de sesion
        if (sesion.getAnonima() != null && sesion.getFechaCreacion() != null && sesion.getFechaExpiracion() != null &&
        		sesion.getUsuario() != null && sesion.getClave() != null)
        {
        	ses.setAnonima(sesion.getAnonima());
        	ses.setFechaCreacion(sesion.getFechaCreacion());
        	ses.setFechaExpiracion(sesion.getFechaExpiracion());
        	ses.setUsuario(sesion.getUsuario());
        	ses.setClave(EncriptacionUtiles.encriptar(sesion.getClave()));
        	sesionDao.update(ses);
        	logger.info("Error modificando sesion con id["+sesion.getIdSesion()+"]. Algun campo requerido es vacio:anonima["+sesion.getAnonima()
        			+"], fecha creacion["+ sesion.getFechaCreacion()+"] fecha expiracion["+sesion.getFechaExpiracion()
        			+"] usuario["+sesion.getUsuario()+"] clave["+sesion.getClave()+"]");
        	return sesion;
        }
		String texto = "Error modificando sesion con id["+sesion.getIdSesion()+"]. Algun campo requerido es vacio:anonima["+(sesion.getAnonima()!=null?sesion.getAnonima():null)
		+"], fecha creacion["+ (sesion.getFechaCreacion()!=null?sesion.getFechaCreacion():null)
		+"] fecha expiracion["+(sesion.getFechaExpiracion()!=null?sesion.getFechaExpiracion():null)
		+"] usuario["+(sesion.getUsuario()!=null?sesion.getUsuario():null)
		+"] clave["+(sesion.getClave()!=null?sesion.getClave():null)
		+"]";
		logger.warn(texto);
//		throw new SesionNoValidaException(texto);
		return null;
        
    }
}
