
package es.pode.soporte.seguridad.servicios;

public interface SrvIdentidadFederadaServiceService extends javax.xml.rpc.Service {

/**
 * @return String

 */
    public java.lang.String getSrvIdentidadFederadaServiceAddress();

    public es.pode.soporte.seguridad.servicios.SrvIdentidadFederadaService getSrvIdentidadFederadaService() throws javax.xml.rpc.ServiceException;

    public es.pode.soporte.seguridad.servicios.SrvIdentidadFederadaService getSrvIdentidadFederadaService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
