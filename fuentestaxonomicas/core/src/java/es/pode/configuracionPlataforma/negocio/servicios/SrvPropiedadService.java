/**
 * SrvPropiedadService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.configuracionPlataforma.negocio.servicios;

public interface SrvPropiedadService extends java.rmi.Remote {

    /**
     * Devuelve una propiedad. Primero intenta localizarla como local
     * y
     *                 si falla la busca como global.
     */
    public es.pode.configuracionPlataforma.negocio.servicios.PropiedadVO getPropiedad(java.lang.String nombrePropiedad) throws java.rmi.RemoteException;

    /**
     * Devuelve las propiedades pertenecientes a la instancia Jboss
     * local. La instancia jboss puede ser global, default, node1,
     *                 node2, node3, node4, node5, o node6
     */
    public es.pode.configuracionPlataforma.negocio.servicios.PropiedadVO[] getPropiedadesJboss(java.lang.String instanciaJboss) throws java.rmi.RemoteException;

    /**
     * Devuelve las propiedades pertenecientes a la instancia Jboss
     * local filtrandolas por categoria. La instancia jboss puede ser
     *                 global, default, node1, node2, node3, node4, node5,
     * o node6
     */
    public es.pode.configuracionPlataforma.negocio.servicios.PropiedadVO[] getPropiedadesJbossPorCategoria(java.lang.String instanciaJboss, java.lang.String categoria) throws java.rmi.RemoteException;

    /**
     * Devuelve las propiedades pertenecientes a la instancia Jboss
     * local filtrandolas por el campo ficherosAfectados. La instancia
     *                 jboss puede ser global, default, node1, node2, node3,
     * node4,
     *                 node5, o node6
     */
    public es.pode.configuracionPlataforma.negocio.servicios.PropiedadVO[] getPropiedadesJbossPorFichero(java.lang.String nombreFichero, java.lang.String instanciaJboss) throws java.rmi.RemoteException;

    /**
     * Devuelve las propiedades pertenecientes a la instancia Jboss
     * local. La instancia JBoss local se ajustara automaticamente.
     */
    public es.pode.configuracionPlataforma.negocio.servicios.PropiedadVO[] getPropiedadesLocales() throws java.rmi.RemoteException;

    /**
     * Devuelve las propiedades pertenecientes a la instancia Jboss
     * local filtrandolas por categoria. La instancia JBoss local se
     *                 ajustara automaticamente.
     */
    public es.pode.configuracionPlataforma.negocio.servicios.PropiedadVO[] getPropiedadesLocalesPorCategoria(java.lang.String categoria) throws java.rmi.RemoteException;

    /**
     * Devuelve las propiedades pertenecientes a la instancia Jboss
     * local filtrandolas por el campo ficherosAfectados.  La instancia
     *                 JBoss local se ajustara automaticamente.
     */
    public es.pode.configuracionPlataforma.negocio.servicios.PropiedadVO[] getPropiedadesLocalesPorFichero(java.lang.String nombreFichero) throws java.rmi.RemoteException;

    /**
     * Devuelve el valor de una propiedad. Primero intenta localizarla
     * como local y si falla la busca como global.
     */
    public java.lang.String getValorPropiedad(java.lang.String nombrePropiedad) throws java.rmi.RemoteException;

    /**
     * Devuelve el valor de la variable perteneciente a una instancia
     * JBoss determinada; global, default, node1, node2, node3, node4,
     *                 node5 o node6
     */
    public java.lang.String getValorPropiedadJboss(java.lang.String nombrePropiedad, java.lang.String instanciaJboss) throws java.rmi.RemoteException;

    /**
     * Devuelve el valor de la variable perteneciente a la instancia
     * JBoss local.  La instancia JBoss local se ajustara
     *                 automaticamente.
     */
    public java.lang.String getValorPropiedadLocal(java.lang.String nombrePropiedad) throws java.rmi.RemoteException;

    /**
     * Modifica el valor de la variable perteneciente a una instancia
     * JBoss determinada; global, default, node1, node2, node3, node4,
     *                 node5 o node6
     */
    public boolean setValorPropiedadJboss(java.lang.String nombrePropiedad, java.lang.String valor, java.lang.String instanciaJboss) throws java.rmi.RemoteException;

    /**
     * Modifica el valor de la variable perteneciente a la instancia
     * JBoss local.  La instancia JBoss local se ajustara
     *                 automaticamente.
     */
    public boolean setValorPropiedadLocal(java.lang.String nombrePropiedad, java.lang.String valor) throws java.rmi.RemoteException;
}
