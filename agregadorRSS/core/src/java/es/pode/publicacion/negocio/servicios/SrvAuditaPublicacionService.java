/**
 * SrvAuditaPublicacionService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.publicacion.negocio.servicios;

public interface SrvAuditaPublicacionService extends java.rmi.Remote {

    /**
     * Este metodo devuelve para cada estado posible de un ODE, el
     * numero de transiciones que se han detectado para cada estado en
     *                 el periodo dado.
     */
    public es.pode.publicacion.negocio.servicios.ODEsEstadoVO[] actividadTransiciones(es.pode.publicacion.negocio.servicios.ParametroAuditPublicacionVO parametroAuditoria) throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve el tamanio de los ODEs que estan publicados
     * en la plataforma.
     *                 Se le pasa la fecha desde y hasta que se quiere tener
     * en cuenta
     *                 y el numero de ODEs que se quieren devolver.
     *                 Se devuelve una lista de la cantidad de informacion
     * pedida
     *                 ordenada de mayor a menor de los mas pesados a los
     * menos.
     */
    public es.pode.publicacion.negocio.servicios.PesoODEVO[] dimensionesODEsPublicados(es.pode.publicacion.negocio.servicios.ParametroAuditPublicacionVO parametroAuditoria) throws java.rmi.RemoteException;

    /**
     * Obtiene los n últimos odes publicados.
     *                 Parametro de entrada: entero con el numero de odes
     * que se
     *                 quieren obtener.
     *                 Parametro de salida: array de vo con los n últimos
     * odes
     *                 publicados.
     */
    public es.pode.publicacion.negocio.servicios.OdePublicadoVO[] obtenerUltimosOdesPublicados(int numeroOdes) throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve la cantidad de ODEs que hay en cada estado
     * dentro del periodo de tiempo dado.
     */
    public es.pode.publicacion.negocio.servicios.ODEsEstadoVO[] odesPorEstados(es.pode.publicacion.negocio.servicios.ParametroAuditPublicacionVO parametroAuditoria) throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve el listado de ODEs que un usuario tiene
     * asociado por estado dentro del ciclo de vida definido en el
     *                 modulo de publicacion.
     */
    public es.pode.publicacion.negocio.servicios.EstadoActividadVO[] odesPorUsuario(es.pode.publicacion.negocio.servicios.ParametroAuditPublicacionVO parametroAuditoria) throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve el numero de ODEs publicados en la
     *                 plataforma.
     */
    public es.pode.publicacion.negocio.servicios.ODEsEstadoVO odesPublicados() throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve el numero de ODEs que hay publicados por
     * idiomas.
     */
    public es.pode.publicacion.negocio.servicios.ODEsEstadoIdiomaVO[] odesPublicadosPorIdioma() throws java.rmi.RemoteException;

    /**
     * Este metodo devuelve la informacion de detalle de la lista
     * de
     *                 identificadores que le pasan.
     */
    public es.pode.publicacion.negocio.servicios.DetallePublicadoODEVO[] titulosODEsPorID(java.lang.String[] idODEs) throws java.rmi.RemoteException;
}
