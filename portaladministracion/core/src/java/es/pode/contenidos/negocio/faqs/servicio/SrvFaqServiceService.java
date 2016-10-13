/**
 * SrvFaqServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.contenidos.negocio.faqs.servicio;

public interface SrvFaqServiceService extends javax.xml.rpc.Service {

/**
 * Servicio que ofrece los metodos de negocio para la gestión de
 *             preguntas frecuentes (FAQs).
 */
    public java.lang.String getSrvFaqServiceAddress();

    public es.pode.contenidos.negocio.faqs.servicio.SrvFaqService getSrvFaqService() throws javax.xml.rpc.ServiceException;

    public es.pode.contenidos.negocio.faqs.servicio.SrvFaqService getSrvFaqService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
