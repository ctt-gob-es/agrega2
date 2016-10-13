/**
 * SrvCorreo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.gestorCorreo.negocio.servicios;

public interface SrvCorreo extends java.rmi.Remote {

    /**
     * Método encargado de enviar el correo durante el proceso de
     * alta
     *                 de un usuario.
     *                 El asunto de este tipo de correos será recogido de
     * un fichero
     *                 properties.
     *                 Este método tendrá que distinguir si nos encontramos
     * ante un
     *                 nodo del tipo taller o con un nodo con un ldap externo.
     */
    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO altaUsuario(es.pode.gestorCorreo.negocio.servicios.CorreoUsuarioVO correoUsuarioVO) throws java.rmi.RemoteException;

    /**
     * Método encargado de enviar el correo durante el proceso de
     * baja 
     *                 de un grupo
     *                 El asunto de este tipo de correos será recogido de
     * un fichero
     *                 properties.
     */
    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO bajaGrupo(es.pode.gestorCorreo.negocio.servicios.CorreoGrupoVO correoGrupoVO) throws java.rmi.RemoteException;

    /**
     * Método encargado de enviar el correo durante el proceso de
     * baja
     *                 de un usuario.
     *                 El asunto de este tipo de correos será recogido de
     * un fichero
     *                 properties.
     *                 Este método tendrá que distinguir si nos encontramos
     * ante un
     *                 nodo del tipo taller o con un nodo con un ldap externo.
     */
    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO bajaUsuario(es.pode.gestorCorreo.negocio.servicios.CorreoUsuarioVO correoUsuarioVO) throws java.rmi.RemoteException;

    /**
     * Método encargado de enviar el correo durante el proceso de
     *                 comentario de un ODE
     *                 El asunto de este tipo de correos será recogido de
     * un fichero
     *                 properties.
     */
    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO comentarioODE(es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO correoOdeVO) throws java.rmi.RemoteException;

    /**
     * Método encargado de enviar el correo cuando un contacto
     *                 autopublica un ODE
     *                 El asunto de este tipo de correos será recogido de
     * un fichero
     *                 properties.
     */
    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO contactoAutopublicaODE(es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO correoOdeVO) throws java.rmi.RemoteException;

    /**
     * Método encargado de enviar el correo cuando un contacto publica
     * un ODE
     *                 El asunto de este tipo de correos será recogido de
     * un fichero
     *                 properties.
     */
    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO contactoPublicaODE(es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO correoOdeVO) throws java.rmi.RemoteException;

    /**
     * Método encargado de enviar el correo de la opción "Contenido
     * inapropiado".
     *                 El asunto de este tipo de correos será recogido de
     * un fichero
     *                 properties.
     */
    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO correoContenidoInapropiado(es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO correoODEVO) throws java.rmi.RemoteException;

    /**
     * Método encargado de enviar el correo de la opción "Enviar a
     * un
     *                 amigo".
     *                 El asunto de este tipo de correos será recogido de
     * un fichero
     *                 properties.
     */
    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO correoEnviarAmigo(es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO correoOdeVO) throws java.rmi.RemoteException;

    /**
     * Método encargado de enviar el correo de la opción "rechazar
     * contenido inapropiado".
     *                 El asunto de este tipo de correos será recogido de
     * un fichero
     *                 properties.
     */
    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO correoRechazoContenidoInapropiado(es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO correoOdeVO) throws java.rmi.RemoteException;

    /**
     * Método encargado de enviar el correo durante el proceso de
     *                 desactivacion de un usuario.
     *                 El asunto de este tipo de correos será recogido de
     * un fichero
     *                 properties.
     */
    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO desactivarUsuario(es.pode.gestorCorreo.negocio.servicios.CorreoUsuarioVO correoUsuarioVO) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO enviarCorreoIncidencia(java.lang.String listaDestinatarios, java.lang.String listaEnCopia, java.lang.String tipoIncidencia) throws java.rmi.RemoteException;

    /**
     * Se encarga de enviar los correos de las siguientes tareas del
     * planificador: obtenerMetadatosFederados,
     *                 obtenerMetadatosFederadosFaltantes,
     *                 obtenerDespublicadosFederados y
     *                 obtenerDespublicadosFederadosFaltantes
     */
    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO enviarCorreoTareaUnificacion(java.lang.String listaDestinatarios, java.lang.String listaNodosConError, java.lang.String tipoIncidencia, java.lang.String listaEnCopia) throws java.rmi.RemoteException;

    /**
     * Método encargado de enviar el correo durante el proceso de
     * envio
     *                 de un ODE a un grupo
     *                 El asunto de este tipo de correos será recogido de
     * un fichero
     *                 properties.
     */
    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO envioODEGrupo(es.pode.gestorCorreo.negocio.servicios.CorreoGrupoVO correoGrupoVO) throws java.rmi.RemoteException;

    /**
     * Método encargado de enviar el correo durante el proceso de
     *                 recuerdo de contraseña.
     *                 El asunto de este tipo de correos será recogido de
     * un fichero
     *                 properties.
     *                 Este método tendrá que distinguir si nos encontramos
     * ante un
     *                 nodo del tipo taller o con un nodo con un ldap externo.
     */
    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO nuevaClave(es.pode.gestorCorreo.negocio.servicios.CorreoUsuarioVO correoUsuario) throws java.rmi.RemoteException;

    /**
     * Método encargado de enviar el correo durante el proceso de
     *                 publicacion de un ODE
     *                 El asunto de este tipo de correos será recogido de
     * un fichero
     *                 properties.
     */
    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO publicacionODE(es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO correoODEVO) throws java.rmi.RemoteException;

    /**
     * Método encargado de enviar el correo durante el proceso de
     *                 rechazo de un ODE
     *                 El asunto de este tipo de correos será recogido de
     * un fichero
     *                 properties.
     */
    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO rechazoODE(es.pode.gestorCorreo.negocio.servicios.CorreoOdeVO correoODEVO) throws java.rmi.RemoteException;

    /**
     * Método encargado de enviar el correo durante el proceso de
     *                 solicitud de baja de un usuario.
     *                 El asunto de este tipo de correos será recogido de
     * un fichero
     *                 properties.
     *                 Este método tendrá que distinguir si nos encontramos
     * ante un
     *                 nodo del tipo taller o con un nodo con un ldap externo.
     */
    public es.pode.gestorCorreo.negocio.servicios.ResultadoEnvioCorreoVO solicitudBajaUsuario(es.pode.gestorCorreo.negocio.servicios.CorreoUsuarioVO correoUsuarioVO) throws java.rmi.RemoteException;
}
