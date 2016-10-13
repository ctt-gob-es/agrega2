/**
 * SrvDRIService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.dri.negocio.servicios.DRI;

public interface SrvDRIService extends java.rmi.Remote {

    /**
     * Método para subir desde offline los odes a la carpeta personal
     * del usuario utilizando usuario y clave. Devolverá un Integer que
     *                 será un código de error
     */
    public java.lang.Integer crear(java.lang.String usuario, java.lang.String clave, javax.activation.DataHandler pif) throws java.rmi.RemoteException;

    /**
     * Método para subir desde offline los odes a la carpeta personal
     * del usuario utilizando la sesión .Devolverá un Integer que será
     *                 un código de error
     */
    public java.lang.Integer crearSesion(java.lang.String sesion, javax.activation.DataHandler pif) throws java.rmi.RemoteException;

    /**

     */
    public java.lang.Boolean estasActivo() throws java.rmi.RemoteException;

    /**
     * Este metodo invoca el publicarPIF del servicio de publicacion.
     * Le pasan como parametros el identificador de usuario y la clave
     *                 de un usuario autenticado en la plataforma y el contenido
     * del
     *                 ODE en formato PIF. El usuario debe tener los suficientes
     * permisos dentro de la plataforma para poder realizar esta
     *                 accion. Devolverá un código de error
     */
    public java.lang.Integer presentarAlmacenar(java.lang.String usuario, java.lang.String clave, javax.activation.DataHandler pif) throws java.rmi.RemoteException;

    /**
     * Este metodo invoca el publicarPIF del servicio de publicacion.
     * Le pasan como parametros el identificador de la sesion que hay
     *                 que haber inicializado y el contenido del ODE en formato
     * PIF.
     *                 Devolverá un código de error
     */
    public java.lang.Integer presentarAlmacenarSesion(java.lang.String sesionId, javax.activation.DataHandler pif) throws java.rmi.RemoteException;

    /**
     * Este metodo recoge un ODE y lo introduce en el ciclo de vida
     * de
     *                 la plataforma en el estado catalogar en el que un
     * catalogador ha
     *                 de realizar su labor sobre el ODE. Devolverá un código
     * de error
     */
    public java.lang.Integer presentarCatalogar(java.lang.String usuario, java.lang.String clave, javax.activation.DataHandler pif) throws java.rmi.RemoteException;

    /**
     * Este metodo recoge un ODE y lo introduce en el ciclo de vida
     * de
     *                 la plataforma en el estado catalogar en el que un
     * catalogador ha
     *                 de realizar su labor sobre el ODE. Devolverá un código
     * de error
     */
    public java.lang.Integer presentarCatalogarSesion(java.lang.String sesionId, javax.activation.DataHandler pif) throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve un ODE en formato PIF.
     *                 Toma como parametros el identificador de la sesion
     * que hay que
     *                 haber inicializado antes y el identificador del ODE
     * que se
     *                 quiere obtener.
     */
    public javax.activation.DataHandler solicitarEntregarSesion(java.lang.String sesionId, java.lang.String mec) throws java.rmi.RemoteException;
}
