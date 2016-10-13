/**
 * SrvNoticiasServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.noticias.servicio;

public interface SrvNoticiasServiceService extends javax.xml.rpc.Service {

/**
 * Servicio Web que ofrece la funcionalidad necesaria para la
 *             gestion y visualizacion de contenidos del portal (Noticias
 * y
 *             Preguntas Frecuentes o FAQs).
 */
    public java.lang.String getSrvNoticiasServiceAddress();

    public es.pode.contenidos.negocio.noticias.servicio.SrvNoticiasService getSrvNoticiasService() throws javax.xml.rpc.ServiceException;

    public es.pode.contenidos.negocio.noticias.servicio.SrvNoticiasService getSrvNoticiasService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
