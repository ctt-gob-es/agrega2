/**
 * SrvValidadorVDEXServiceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.validador.negocio.servicio;

public interface SrvValidadorVDEXServiceService extends javax.xml.rpc.Service {

/**
 * El servicio contiene metodos para las siguientes tareas:
 *             A) obtenerValidacionTesauro:
 *             recibe como parámetro la ruta al xml que se quiere validar.
 * 
 *             realiza la validacion contra esquemas requerida y devuelve
 * un VO
 *             ValidaVdexVO con el resultado de la validacion.
 *             valida contra el esquema imsvdex_v1p0_thesaurus.xsd
 *             B) obtenerValidacionTaxonomia::
 *             recibe como parámetro la ruta al xml que se quiere validar.
 * 
 *             realiza la validacion contra esquemas requerida y devuelve
 * un VO
 *             ValidaVdexVO con el resultado de la validacion.
 *             valida contra el esquema imsvdex_v1p0_hierarchical.xsd
 */
    public java.lang.String getSrvValidadorVDEXServiceAddress();

    public es.pode.validador.negocio.servicio.SrvValidadorVDEXService getSrvValidadorVDEXService() throws javax.xml.rpc.ServiceException;

    public es.pode.validador.negocio.servicio.SrvValidadorVDEXService getSrvValidadorVDEXService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
