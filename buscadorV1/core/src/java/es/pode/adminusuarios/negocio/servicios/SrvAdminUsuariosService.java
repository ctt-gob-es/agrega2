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
    public java.lang.Long altaGrupo(es.pode.adminusuarios.negocio.servicios.GrupoVO grupoVO) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Long altaGrupoTrabajo(es.pode.adminusuarios.negocio.servicios.GrupoTrabajoVO grupoTrabajo) throws java.rmi.RemoteException;

    /**
     * Alta de un usuario
     */
    public java.lang.Long altaUsuario(es.pode.adminusuarios.negocio.servicios.UsuarioVO usuario) throws java.rmi.RemoteException;

    /**
     * Eliminación de un grupo
     */
    public es.pode.adminusuarios.negocio.servicios.ValidaBajaGrupoVO bajaGrupo(java.lang.Long[] ids) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.adminusuarios.negocio.servicios.ValidaBajaGrupoTrabajoVO bajaGrupoTrabajo(java.lang.Long[] ids) throws java.rmi.RemoteException;

    /**
     * Eliminación de usuarios
     */
    public es.pode.adminusuarios.negocio.servicios.ValidaBajaUsuarioVO bajaUsuario(java.lang.Long[] ids) throws java.rmi.RemoteException;

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
     * Obtiene el grupo de trabajo cuyo identificador se pasa como
     * parámetro
     */
    public es.pode.adminusuarios.negocio.servicios.GrupoTrabajoVO descripcionGrupoTrabajo(java.lang.Long identificador) throws java.rmi.RemoteException;

    /**
     * Los datos de un usuario
     */
    public es.pode.adminusuarios.negocio.servicios.UsuarioVO descripcionUsuario(java.lang.Long id) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String enviarCorreoBaja(es.pode.adminusuarios.negocio.servicios.UsuarioVO usuario) throws java.rmi.RemoteException;

    /**
     * Comprueba si el usuario que se pasa como parámetro esta activo
     * en la plataforma
     */
    public java.lang.Boolean estaActivo(java.lang.String usuario) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean existeDescripcion(java.lang.String descripcion, java.lang.Long id) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean existeNombreTrabajo(java.lang.String nombre, java.lang.Long id) throws java.rmi.RemoteException;

    /**
     * Comprueba si existe en el sistema un usuario con ese usuario
     * (login)
     */
    public java.lang.Boolean existeUsuario(java.lang.String usuario) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String[] getEmailAdmin() throws java.rmi.RemoteException;

    /**
     * Obtiene los correos de todos los publicadores de la plataforma
     */
    public java.lang.String[] getEmailPublicadores() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.adminusuarios.negocio.servicios.RolVO getRol(java.lang.Long id) throws java.rmi.RemoteException;

    /**
     * Listado de los grupos existentes
     */
    public es.pode.adminusuarios.negocio.servicios.GrupoVO[] listarGrupos() throws java.rmi.RemoteException;

    /**
     * Obtiene el listado de los grupos de trabajo que existen
     *                 actualmente en la plataforma. Además de los que estén
     * dados de
     *                 alta en la plataforma se devolverá el grupo "Todos",
     * si el
     *                 usuario selecciona este grupo de trabajo se le asociarán
     * todos
     *                 los grupos.
     */
    public es.pode.adminusuarios.negocio.servicios.GrupoTrabajoVO[] listarGruposTrabajo() throws java.rmi.RemoteException;

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

     */
    public java.lang.Long modificarGrupoTrabajo(es.pode.adminusuarios.negocio.servicios.GrupoTrabajoVO grupoTrabajo) throws java.rmi.RemoteException;

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
     * Método que obtiene el grupo cuyo único rol sea el que se pase
     * como parámetro, este método devolverá grupos con un único rol y
     *                 que coincidan con el que se pasa como parámetro.
     */
    public es.pode.adminusuarios.negocio.servicios.GrupoVO obtenerGrupoRol(java.lang.String rol) throws java.rmi.RemoteException;

    /**
     * Obtiene la lista de usuarios que pertenecen al mismo grupo
     * de
     *                 trabajo que el usuario que se pasa como parámetro
     */
    public java.lang.String[] obtenerListaUsuariosGrupoTrabajo(java.lang.String usuarioGrupoTrabajo) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.adminusuarios.negocio.servicios.UsuarioVO obtenerUsuario(java.lang.String nif) throws java.rmi.RemoteException;

    /**
     * Metodo para obtener el usuario que pertenezca ese openId
     */
    public es.pode.adminusuarios.negocio.servicios.UsuarioVO obtenerUsuarioConOpenId(java.lang.String openIdUrl) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.adminusuarios.negocio.servicios.UsuarioVO[] obtenerUsuariosActivosPorNombre(java.lang.String usuario) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Long[] obtenerUsuariosAdministrador() throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean obtenerUsuariosGrupo(java.lang.Long id) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean obtenerUsuariosGrupoTrabajo(java.lang.Long id) throws java.rmi.RemoteException;

    /**
     * Registra en BD aquellos usuarios que ya están dados de alta
     * en
     *                 el ldap devolviendo los roles asociados al usuario
     * que se acaba
     *                 de registrar, este método será utilizado como parte
     * de la
     *                 integración con el ldap de las comunidades
     */
    public java.lang.String[] registrarIntegracionLdap(es.pode.adminusuarios.negocio.servicios.UsuarioVO usuario) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.String solicitarAltaUsuario(es.pode.adminusuarios.negocio.servicios.UsuarioVO usuario) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.adminusuarios.negocio.servicios.UsuarioActivoVO[] usuariosActivos(es.pode.adminusuarios.negocio.servicios.ParametroAuditoriaUsuariosVO parametroAuditoriaVO) throws java.rmi.RemoteException;
}
