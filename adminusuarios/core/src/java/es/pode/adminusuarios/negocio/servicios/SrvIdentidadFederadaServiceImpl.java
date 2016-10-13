// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.pode.adminusuarios.negocio.servicios;



import org.apache.log4j.Logger;

import es.agrega.soporte.agregaProperties.AgregaProperties;
import es.agrega.soporte.agregaProperties.AgregaPropertiesImpl;

/**
 * @see es.pode.adminusuarios.negocio.servicios.SrvIdentidadFederadaService
 */

public class SrvIdentidadFederadaServiceImpl
    extends es.pode.adminusuarios.negocio.servicios.SrvIdentidadFederadaServiceBase
{
	
	private RegistroUsuarios registroUsuarios;

	private Logger log = Logger.getLogger(this.getClass());

	public SrvIdentidadFederadaServiceImpl() {
		super();
	}

	
	
    /**
     * Método que verifica que un usuario está auteticado
     * @param usuario String
     * @return Boolean
     * @throws java.lang.Exception 
     */
    protected java.lang.Boolean handleIsAutenticated(java.lang.String usuario)
        throws java.lang.Exception
    {
    	Boolean resultado = false;
    	log.debug("registroUsuarios <"+registroUsuarios+">");
    	String infoUsuario = registroUsuarios.getUserInformation(usuario);
    	if(!(infoUsuario == null) && (!(infoUsuario == "")))
    	{
    		resultado = true;
    	}
    	log.debug("estaAutenticado <"+resultado+">");
    	return resultado;
    }


	/**
	 * Método que elimina a un usuario de la sesión
	 * @param loginUsuario
	 * @return Boolean
	 * @throws java.lang.Exception 
	 */
	protected Boolean handleDeleteUserSession(String loginUsuario) throws Exception
	{

    	Boolean resultado = false;
    	resultado = registroUsuarios.removeSessionInformation(loginUsuario);
       	log.info("resultado de eliminar usuario <"+resultado+">");
    	return resultado;
	}


	/**
	 * Método que registra un usuario en la sesión
	 * @param usuarioLogin
	 * @return Boolean
	 * @throws java.lang.Exception 
	 */
	protected Boolean handleAddUserSession(String usuarioLogin) throws Exception
	{
		String a=AgregaPropertiesImpl.getInstance().getProperty(AgregaProperties.TIMEOUT_IDENTIDAD_FEDERADA);
		Boolean resultado = false;
    	resultado = registroUsuarios.registerNewSession(usuarioLogin, a);
       	log.debug("resultado de aniadir usuario <"+resultado+">");
    	return resultado;
	}

	
	/**
	 * @param registroUsuarios
	 *            the registroUsuarios to set
	 */
	public void setRegistroUsuarios(RegistroUsuarios registroUsuarios) {
		this.registroUsuarios = registroUsuarios;
	}

	/**
	 * @return the cacheEmpaquetacion
	 */
	public RegistroUsuarios getRegistroUsuarios() {
		return registroUsuarios;
	}

	
}