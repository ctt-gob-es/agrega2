/**
 * SrvAuditoriaFederadaServicio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.auditoria.negocio.servicios;

public interface SrvAuditoriaFederadaServicio extends java.rmi.Remote {

    /**
     * método de obtención de los odes por cobertura curricular
     */
    public es.pode.auditoria.negocio.servicios.InformeCoberturaFederadoVO coberturaFederadoLocal(es.pode.auditoria.negocio.servicios.ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;

    /**
     * Obtiene el informe federados de los odes por área curricular
     * en
     *                 cada uno de los nodos de la federación
     */
    public es.pode.auditoria.negocio.servicios.InformeCoberturaFederadoVO[] informeCoberturaFederado(es.pode.auditoria.negocio.servicios.ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;

    /**
     * Método que generará un array de VO con la información de los
     * odes que pertenecen a cada nivel de agregación dentro de la
     *                 federación
     */
    public es.pode.auditoria.negocio.servicios.NivelAgregacionVO[] informeNivelAgregacionFederado(es.pode.auditoria.negocio.servicios.ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;

    /**
     * método que devuelve los odes publicados en cada idioma para
     * cada
     *                 nodo
     */
    public es.pode.auditoria.negocio.servicios.InformeOdesIdiomaFederadoVO[] informeOdesIdiomaFederado(es.pode.auditoria.negocio.servicios.ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;

    /**
     * método que devuelve los odes publicados en cada idioma
     */
    public es.pode.auditoria.negocio.servicios.InformeOdesIdiomaFederadoVO informeOdesIdiomaLocal(es.pode.auditoria.negocio.servicios.ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;

    /**
     * método que obtiene el número de odes publicados en cada nodo
     */
    public es.pode.auditoria.negocio.servicios.InformeOdesPublicadosFederadaVO[] informeOdesPublicadosFederado(es.pode.auditoria.negocio.servicios.ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;

    /**
     * Método de generación del informe de nivel de agregación local
     */
    public es.pode.auditoria.negocio.servicios.NivelAgregacionVO nivelAgregacionFederadoLocal(es.pode.auditoria.negocio.servicios.ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;

    /**

     */
    public es.pode.auditoria.negocio.servicios.InformeOdesPublicadosFederadaVO odesPublicadosFederadoLocal(es.pode.auditoria.negocio.servicios.ParametrosInformeFederadoVO parametros) throws java.rmi.RemoteException;
}
