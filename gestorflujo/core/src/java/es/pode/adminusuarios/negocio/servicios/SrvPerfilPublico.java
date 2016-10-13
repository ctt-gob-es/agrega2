/**
 * SrvPerfilPublico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.adminusuarios.negocio.servicios;

public interface SrvPerfilPublico extends java.rmi.Remote {

    /**
     * Cuando se acepta se incluye el usuario en el grupo publico
     * y el
     *                 grupo publico en usuario publico y se elimina la solicitud
     */
    public java.lang.Boolean aceptarSolicitudGrupo(java.lang.Long idSolicitudGrupo) throws java.rmi.RemoteException;

    /**
     * Podemos añdir usuario a nuestra agenda(nuestros contactos)
     */
    public es.pode.adminusuarios.negocio.servicios.ResultadoOperacionVO anadirContactoAAgenda(es.pode.adminusuarios.negocio.servicios.ContactoVO contacto, java.lang.String usuario) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Long anadirFavoritoAUsuario(es.pode.adminusuarios.negocio.servicios.FavoritoVO favorito, java.lang.String usuario) throws java.rmi.RemoteException;

    /**
     * Método para añadir un ODE a un grupo, al asociarlo se enviará
     * un
     *                 correo a todos los usuarios miembros del grupo
     */
    public es.pode.adminusuarios.negocio.servicios.ResultadoOperacionVO[] asociarOdeAGrupo(es.pode.adminusuarios.negocio.servicios.OdeGrupoVO ode, java.lang.String[] nombres) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.adminusuarios.negocio.servicios.FavoritoAnadidioVO[] buscarFavoritosPorTitulo(java.lang.String titulo, java.lang.String usuario) throws java.rmi.RemoteException;

    /**
     * Buscamos todos los grupo que se asemejen a la descripción dada
     */
    public es.pode.adminusuarios.negocio.servicios.GrupoPublicoAdminVO[] buscarGruposPorDescripcion(java.lang.String descripcion, java.lang.String usuario) throws java.rmi.RemoteException;

    /**
     * Método para buscar los grupos que coincidan con el texto que
     * le
     *                 pasamos y nos devolverá una lista de grupos especificando
     * si
     *                 está asociado al usuario o no.
     */
    public es.pode.adminusuarios.negocio.servicios.GrupoPublicoAsociadoVO[] buscarGruposPorNombre(java.lang.String nombreGrupo, java.lang.String usuario) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.adminusuarios.negocio.servicios.ContactoAsociadoVO[] buscarUsuariosPorNombre(java.lang.String usuario, java.lang.String textoUsuario) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean cambiarImagenPorElDefecto(java.lang.String usuario) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean cancelarSolicitudGrupo(java.lang.Long idSolicitudGrupo) throws java.rmi.RemoteException;

    /**
     * Metodo para crear un grupo publico por un usuario que será
     * el
     *                 administrador
     */
    public java.lang.Long crearGrupoPublico(es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO grupoPublico, java.lang.String usuario) throws java.rmi.RemoteException;

    /**
     * Método para crear un usuario publico
     */
    public java.lang.Long crearUsuarioPublico(es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO usuarioPublico, java.lang.String usuario) throws java.rmi.RemoteException;

    /**
     * Sólo lo podrá hacer el administrador del grupo
     */
    public es.pode.adminusuarios.negocio.servicios.ResultadoOperacionVO[] desasociarOdeDeGrupo(java.lang.String[] id_mecs, java.lang.String nombre, java.lang.String usuario, java.lang.String[] titulos) throws java.rmi.RemoteException;

    /**
     * Si somos administradores del grupo podemos eliminar un contacto
     * de nuestra lista de contactos
     */
    public es.pode.adminusuarios.negocio.servicios.ResultadoOperacionVO[] eliminarContactoDeAgenda(java.lang.Long[] idContactos, java.lang.String usuario) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.adminusuarios.negocio.servicios.ResultadoOperacionVO[] eliminarFavoritoDeUsuario(java.lang.String[] id_mecs, java.lang.String usuario, java.lang.String[] titulos) throws java.rmi.RemoteException;

    /**
     * El usuario que tenga ROL ADMINISTRADOR pude borrar cualquier
     * grupo publico
     */
    public es.pode.adminusuarios.negocio.servicios.ResultadoOperacionVO[] eliminarGrupoPublicoPorAdministrador(java.lang.Long[] identificadores) throws java.rmi.RemoteException;

    /**
     * Metodo para eliminar los grupo publicos, sólo lo podrá eliminar
     * el usuario administrador de ese grupo
     */
    public es.pode.adminusuarios.negocio.servicios.ResultadoOperacionVO[] eliminarGrupoPublicoPorUsuario(java.lang.Long[] identificadores, java.lang.String usuario) throws java.rmi.RemoteException;

    /**
     * Método para borrar los odes de la parte publica
     */
    public es.pode.adminusuarios.negocio.servicios.ResultadoOperacionVO[] eliminarODEPerfilPublico(java.lang.String[] id_mecs) throws java.rmi.RemoteException;

    /**
     * El usuario que hace la solicitud puede eliminarlo
     */
    public java.lang.Boolean[] eliminarSolicitud(java.lang.Long[] idSolicitudes, java.lang.String solicitante) throws java.rmi.RemoteException;

    /**
     * El administrador del grupo puede eliminar un usuario de la
     * lista
     *                 del grupo
     */
    public es.pode.adminusuarios.negocio.servicios.ResultadoOperacionVO[] eliminarUsuarioDeGrupo(java.lang.String[] usuarios, java.lang.String nombre) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.adminusuarios.negocio.servicios.ResultadoOperacionVO eliminarUsuarioPublico(java.lang.String usuario) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean existeContactoEnAgendaDeUsuario(java.lang.String usuario, java.lang.String contacto) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean existeFavoritoEnUsuario(java.lang.String id_mec, java.lang.String usuario) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean existeGrupoEnUsuario(java.lang.String usuario, java.lang.String nombreGrupo) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean existeNombreGrupo(java.lang.String nombreGrupo, java.lang.Long id) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean existeOdeEnGrupo(java.lang.String id_mec, java.lang.String nombreGrupo) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean existeSolicitudGrupoIdentico(java.lang.String nombreGrupo, java.lang.String usuario) throws java.rmi.RemoteException;

    /**
     * Método para hacer la solicitud de ingreso en el grupo; Este
     * casó
     *                 se dará en el caso de que el solicitante no sea miembro.
     * Después
     *                 el administrador del grupo tendrá que decidir si aceptarlo
     * o no.
     */
    public java.lang.Long hacerSolicitudParaGrupo(es.pode.adminusuarios.negocio.servicios.SolicitudGrupoVO solicitudGrupo) throws java.rmi.RemoteException;

    /**
     * Devuelve la lista de todos los grupos donde el usuario está
     * asociado y el ode esté.
     */
    public es.pode.adminusuarios.negocio.servicios.OdeConGruposVO[] listadoGruposConAsociacionPorUsuarioYOde(java.lang.String usuario, java.lang.String[] id_mecs) throws java.rmi.RemoteException;

    /**
     * Listamos todos los contacto de un usuario
     */
    public es.pode.adminusuarios.negocio.servicios.ContactoVO[] listarContactosDeAgenda(java.lang.String usuario) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.adminusuarios.negocio.servicios.FavoritoVO[] listarFavoritosUsuario(java.lang.String usuario) throws java.rmi.RemoteException;

    /**
     * Listamos todos los grupos publico exitentes
     */
    public es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO[] listarGrupoPublicos() throws java.rmi.RemoteException;

    /**
     * Listamos todos los grupos donde el usuario es el administrador
     */
    public es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO[] listarGrupoPublicosDeAdministrador(java.lang.String administrador) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO[] listarGruposCreadosUltimos() throws java.rmi.RemoteException;

    /**

     */
    public es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO[] listarGruposModificadosUltimos() throws java.rmi.RemoteException;

    /**
     * Listamos todos los grupos publicos haciendo la distinción entre
     * aquellos en los que el usuario ya está y a los que se pude
     *                 asociar
     */
    public es.pode.adminusuarios.negocio.servicios.GrupoPublicoAsociadoVO[] listarGruposPublicosConAsociacion(java.lang.String usuario) throws java.rmi.RemoteException;

    /**
     * Listamos todos los grupos a los que el usuario a solicitado
     * integrar, pero la solicitud no se ha resuelto
     */
    public es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO[] listarGruposSolicitadosPorUsuario(java.lang.String usuario) throws java.rmi.RemoteException;

    /**
     * Listamos todos los odes que estén añadidos en el  grupo
     */
    public es.pode.adminusuarios.negocio.servicios.OdeGrupoVO[] listarOdesDeGrupo(java.lang.String nombre) throws java.rmi.RemoteException;

    /**
     * Listamos todas las solicitudes que le faltan por resolver al
     * administrador del grupo
     */
    public es.pode.adminusuarios.negocio.servicios.SolicitudGrupoVO[] listarSolicitudesAdministrador(java.lang.String administrador) throws java.rmi.RemoteException;

    /**
     * Listamos todas las solicitudes que le ha realizado el usuario
     */
    public es.pode.adminusuarios.negocio.servicios.SolicitudGrupoVO[] listarSolicitudesRealizadasPorUsuario(java.lang.String solicitante) throws java.rmi.RemoteException;

    /**
     * Listamos todos los grupos dónde el usuario es el administrador
     * o
     *                 asociado
     */
    public es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO[] listarTodosGrupoDeUsuario(java.lang.String usuario) throws java.rmi.RemoteException;

    /**
     * Buscamos todos los usuarios que tienen añadido como contacto
     * el
     *                 nombre del contacto que le pasamos y que tienen marcado
     * como
     *                 true la variable recibirContacto
     */
    public es.pode.adminusuarios.negocio.servicios.UsuarioCorreoVO[] listarUsuariosConContacto(java.lang.String contacto) throws java.rmi.RemoteException;

    /**
     * Se listan todos los usuarios pertenecientes al grupo
     */
    public es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO[] listarUsuariosDeGrupo(java.lang.String nombre) throws java.rmi.RemoteException;

    /**
     * Lista todos los usuarios de un grupo con la información de
     * si
     *                 debe o no recibir los e-mails. Sólo devolveremos aquellos
     * usuarios que quieren recibir los mail-s
     */
    public es.pode.adminusuarios.negocio.servicios.UsuarioCorreoVO[] listarUsuariosDeGrupoCorreo(java.lang.String nombreGrupo) throws java.rmi.RemoteException;

    /**
     * Metodo para modificar el grupo publico
     */
    public java.lang.Long modificarGrupoPublico(es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO grupoPublico, java.lang.Long idGrupo, java.lang.String usuario) throws java.rmi.RemoteException;

    /**
     * Metodo para modificar el usuario publico
     */
    public java.lang.Long modificarUsuarioPublico(es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO usuarioPublico, java.lang.String usuario) throws java.rmi.RemoteException;

    /**
     * Obtenermos el grupo publico
     */
    public es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO obtenerGrupoPublico(java.lang.String nombre) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.adminusuarios.negocio.servicios.GrupoPublicoVO[] obtenerGruposPublicosPorIdentificador(java.lang.Long[] ids) throws java.rmi.RemoteException;

    /**
     * Obtenemos todos los grupos creados por el usuario y también
     * aquellos grupos donde el usuario se ha asociado
     */
    public es.pode.adminusuarios.negocio.servicios.GrupoPublicoAdminVO[] obtenerGrupoUsuario(java.lang.String usuario) throws java.rmi.RemoteException;

    /**
     * Metodo que nos devuelve el número de favoritos que le indiquemos
     */
    public es.pode.adminusuarios.negocio.servicios.FavoritoVO[] obtenerNumeroFavoritosUsuario(java.lang.String usuario, java.lang.Long numero) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.adminusuarios.negocio.servicios.UsuarioPublicoVO obtenerUsuarioPublico(java.lang.String usuario) throws java.rmi.RemoteException;
}
