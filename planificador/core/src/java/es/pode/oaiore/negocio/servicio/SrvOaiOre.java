/**
 * SrvOaiOre.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package es.pode.oaiore.negocio.servicio;

public interface SrvOaiOre extends java.rmi.Remote {

    /**
     * Entrega el atom solicitado como un String
     */
    public java.lang.String entregarAtom(es.pode.oaiore.negocio.servicio.TipoAtomOre tipo) throws java.rmi.RemoteException;

    /**
     * Genera un Atom representando información de OAI-ORE
     */
    public void generarOreAtom(java.lang.Integer tipo) throws java.rmi.RemoteException;
}
