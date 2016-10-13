/**
 * SrvAdminUsuariosService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.adminusuarios.negocio.servicios;

public interface SrvAdminUsuariosService extends java.rmi.Remote {

    /**

     */
    public void activarUsuario(java.lang.Long id, java.lang.String admin) throws java.rmi.RemoteException;

    /**

     */
    public void altaGrupo(es.pode.adminusuarios.negocio.servicios.GrupoVO grupoVO) throws java.rmi.RemoteException;

    /**
     * Alta de un usuario
     */
    public java.lang.String altaUsuario(es.pode.adminusuarios.negocio.servicios.UsuarioVO usuario, es.pode.adminusuarios.negocio.servicios.UsuarioVO adminVO) throws java.rmi.RemoteException;

    /**
     * Eliminación de un grupo
     */
    public es.pode.adminusuarios.negocio.servicios.ValidaBajaGrupoVO bajaGrupo(java.lang.Long[] ids) throws java.rmi.RemoteException;

    /**
     * Eliminación de usuarios
     */
    public es.pode.adminusuarios.negocio.servicios.ValidaBajaUsuarioVO bajaUsuario(java.lang.Long[] ids, es.pode.adminusuarios.negocio.servicios.UsuarioVO emailAdmin) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.adminusuarios.negocio.servicios.ValidaBajaUsuarioVO bajaUsuarioPendiente(java.lang.Long[] ids, es.pode.adminusuarios.negocio.servicios.UsuarioVO emailAdmin) throws java.rmi.RemoteException;

    /**

     */
    public void desactivarUsuario(java.lang.Long id, java.lang.String admin) throws java.rmi.RemoteException;

    /**
     * Descripción de los datos de un grupo
     */
    public es.pode.adminusuarios.negocio.servicios.GrupoVO descripcionGrupo(java.lang.Long id) throws java.rmi.RemoteException;

    /**
     * Los datos de un usuario
     */
    public es.pode.adminusuarios.negocio.servicios.UsuarioVO descripcionUsuario(java.lang.Long id) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String enviarCorreoBaja(es.pode.adminusuarios.negocio.servicios.UsuarioVO usuario) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean existeDescripcion(java.lang.String descripcion, java.lang.Long id) throws java.rmi.RemoteException;

    /**
     * Comprueba si existe en el sistema un usuario con ese usuario
     * (login)
     */
    public java.lang.Boolean existeUsuario(java.lang.String usuario) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String[] getEmailAdmin() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.adminusuarios.negocio.servicios.RolVO getRol(java.lang.Long id) throws java.rmi.RemoteException;

    /**
     * Listado de los grupos existentes
     */
    public es.pode.adminusuarios.negocio.servicios.GrupoVO[] listarGrupos() throws java.rmi.RemoteException;

    /**
     * Listado de los roles existentes
     */
    public es.pode.adminusuarios.negocio.servicios.RolVO[] listarRoles() throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String[] listarRolesUsuario(java.lang.String usuario) throws java.rmi.RemoteException;

    /**
     * Lista todos los usuarios del sistema (salvo los pendientes
     * de
     *                 dar de alta)
     */
    public es.pode.adminusuarios.negocio.servicios.UsuarioVO[] listarTodosUsuarios() throws java.rmi.RemoteException;

    /**
     * Listado de todos los usuarios del nodo que ya han sido dados
     * de
     *                 alta por el administrador
     */
    public es.pode.adminusuarios.negocio.servicios.UsuarioVO[] listarUsuarios() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.adminusuarios.negocio.servicios.UsuarioVO[] listarUsuariosInactivos() throws java.rmi.RemoteException;

    /**
     * Listado de los usuarios del nodo que no han sido dados de alta
     * por el administrador
     */
    public es.pode.adminusuarios.negocio.servicios.UsuarioVO[] listarUsuariosPendientes() throws java.rmi.RemoteException;

    /**
     * Modificación de los datos de un grupo
     */
    public void modificarGrupo(es.pode.adminusuarios.negocio.servicios.GrupoVO grupo) throws java.rmi.RemoteException;

    /**
     * Datos nuevos del usuario. La propiedad id del objeto UsuarioVO
     * es el identificado del usuarioque va a ser modificado
     */
    public java.lang.String modificarUsuario(es.pode.adminusuarios.negocio.servicios.UsuarioVO usuario) throws java.rmi.RemoteException;

    /**
     * Los usuarios podrán solicitar una nueva clave y que está se
     * les
     *                 envíe por correo electrónico a la cuenta que tienen
     * dada de alta
     *                 en el sistema. Para ello deberán introducir la dirección
     * de
     *                 correo electrónico y el NIF
     */
    public java.lang.Boolean nuevaClave(es.pode.adminusuarios.negocio.servicios.UsuarioVO emailNIF) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.adminusuarios.negocio.servicios.UsuarioVO obtenerDatosUsuario(java.lang.String usuario) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Long[] obtenerGrupoAdministrador() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.adminusuarios.negocio.servicios.UsuarioVO obtenerUsuario(java.lang.String nif) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Long[] obtenerUsuariosAdministrador() throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean obtenerUsuariosGrupo(java.lang.Long id) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String solicitarAltaUsuario(es.pode.adminusuarios.negocio.servicios.UsuarioVO usuario) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.adminusuarios.negocio.servicios.UsuarioActivoVO[] usuariosActivos(es.pode.adminusuarios.negocio.servicios.ParametroAuditoriaUsuariosVO parametroAuditoriaVO) throws java.rmi.RemoteException;
}
