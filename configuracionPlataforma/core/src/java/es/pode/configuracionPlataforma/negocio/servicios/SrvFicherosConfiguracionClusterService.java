/**
 * SrvFicherosConfiguracionService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.configuracionPlataforma.negocio.servicios;

import es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO;

public interface SrvFicherosConfiguracionClusterService extends es.pode.configuracionPlataforma.negocio.servicios.SrvFicherosConfiguracionService,java.rmi.Remote {

    /**

     */
    public boolean escribirFicheroLocal(java.lang.String nombreFichero, es.pode.configuracionPlataforma.negocio.dominio.PropiedadVO[] propiedades);
    public boolean crearBackupFicherosInstanciaJboss(java.lang.String homeInstanciaJboss, java.lang.String instanciaJboss);
    public boolean restaurarBackupFicherosInstanciaJboss(java.lang.String homeInstanciaJboss, java.lang.String instanciaJboss);
}
